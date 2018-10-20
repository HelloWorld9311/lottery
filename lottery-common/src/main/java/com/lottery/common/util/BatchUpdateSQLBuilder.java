package com.lottery.common.util;

/**
 * Created by wuyujia on 17/4/5.
 */

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 拼接批量更新语句工具类
 *  1. 只支持javaBean, 不支持其他任何java类, 否则报错
 *  2. 更新支持批量更新, 全量更新, 要注意数据库是否支持非空数据插入
 *  3. 新增了忽略更新字段
 *  4. 所有填写字段均与数据库定义字段一致
 *  5. 数据库设计时, 严禁使用数字
 */
@SuppressWarnings("all")
public class BatchUpdateSQLBuilder<T> {

    /**
     * 需要插入的表名
     */
    private String tableName;

    /**
     * 直接指定主键名称
     */
    private String primaryKey;

    /**
     * 主键反射字段
     */
    private Field primaryField;

    /**
     * 忽略不更新字段
     */
    private String[] ignoreFields;

    /**
     * 记录最长字段对象中包含的属性名称
     */
    private LinkedList<Field> fields = new LinkedList<>();

    /**
     * 全量更新标志
     * true: 非空值更新
     * false: 全量更新
     */
    private boolean selective = true;

    /**
     * 待插入的数据
     */
    private LinkedList<T> dataList = new LinkedList<>();

    /**
     * 主键数据值集合
     */
    private Set<Object> primaryValueList = new HashSet<>();

    /**
     * 括号开
     */
    private static final String QUOTE_OPEN = " (";

    /**
     * 括号关
     */
    private static final String QUOTE_CLOSE = ") ";

    private static final String UPDATE = "UPDATE ";

    private static final String SET = " SET ";

    private static final String EQUAL = " = ";

    private static final String WHEN = " WHEN ";

    private static final String CASE = " CASE ";

    private static final String THEN = " THEN ";

    private static final String ELSE = " ELSE ";

    private static final String END = " END ";

    private static final String WHERE = " WHERE ";

    private static final String IN = " IN ";

    /**
     * 逗号连接符
     */
    private static final String COMMA = ",";

    /**
     * 引号
     */
    private static final String QUOTATION_MARKS = "'";

    /**
     * 数据库字段引号
     */
    private static final String QUOTATION_MARKS_OPEN = "`";

    /**
     * 数据库字段引号
     */
    private static final String QUOTATION_MARKS_CLOSE = "`";

    /**
     * 允许的数据类型
     */
    private static final Set<Class> allowDataClassType;

    static {
        allowDataClassType = new HashSet<>();
        allowDataClassType.add(Integer.class);
        allowDataClassType.add(Byte.class);
        allowDataClassType.add(Short.class);
        allowDataClassType.add(Long.class);
        allowDataClassType.add(Character.class);
        allowDataClassType.add(Float.class);
        allowDataClassType.add(Double.class);
        allowDataClassType.add(String.class);
        allowDataClassType.add(BigDecimal.class);
    }

    /**
     * 判断字段是否为允许的数据类型
     *
     * @param field
     * @return
     */
    private boolean isAllowClassType(Field field) {
        return allowDataClassType.contains(field.getType());
    }

    /**
     * 设置更新表名, 主键
     *
     * @param tableName
     * @param primaryKey
     * @return
     */
    public BatchUpdateSQLBuilder setUpdateTable(String tableName, String primaryKey) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        return this;
    }


    /**
     * 设置是否全量更新
     *
     * @param selective
     * @return
     */
    public BatchUpdateSQLBuilder setSelective(boolean selective) {
        this.selective = selective;
        return this;
    }

    /**
     * 设置忽略不更新字段
     *
     * @param ignoreFields
     * @return
     */
    public BatchUpdateSQLBuilder setIgnoreFields(String... ignoreFields) {
        this.ignoreFields = ignoreFields;
        return this;
    }

    /**
     * 插入数据
     *
     * @param obj
     * @return
     */
    public BatchUpdateSQLBuilder setData(T obj) {
        if (!selective) {
            // 如果是全量更新, 则将对象字段全部加入到字段表中
            if (fields.size() == 0) {
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    // 查找主键字段
                    if (humpToUnderline(declaredField.getName()).equalsIgnoreCase(primaryKey)) {
                        primaryField = declaredField;
                    }
                }
                fields.addAll(Arrays.asList(declaredFields));
            }
            // 收集主键值
            try {
                primaryValueList.add(primaryField.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            // 加入一条数据到数据集中
            this.dataList.add(obj);
            return this;
        }
        // 如果不是全量更新, 则动态增长有值的字段
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // 查找主键字段
            if (primaryField == null) {
                if (humpToUnderline(declaredField.getName()).equalsIgnoreCase(primaryKey)) {
                    primaryField = declaredField;
                }
            }
            // 收集有值字段, 动态扩展待更新字段
            if (fieldHasValue(obj, declaredField)) {
                if (!fields.contains(declaredField)) {
                    fields.add(declaredField);
                }
            }
        }
        // 收集主键值
        try {
            primaryValueList.add(primaryField.get(obj));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 加入一条数据到数据集中
        this.dataList.add(obj);
        return this;
    }

    /**
     * 获取更新语句
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // 必要参数检查
        this.paramCheck();
        try {
            sb.append(UPDATE).append(tableName).append(SET);
            for (Field field : fields) {
                field.setAccessible(true);
                // 获取该字段更新取值
                sb.append(getFieldString(field));
                sb.append(COMMA);
            }
            sb.replace(sb.lastIndexOf(COMMA), sb.lastIndexOf(COMMA) + 1, "");
            sb.append(getWhereString());
        } catch (IllegalAccessError e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 获取字段拼接的字符串
     *
     * @return
     */
    private String getFieldString(Field field) throws IllegalAccessException {
        // 拼接字段更新值
        StringBuilder sb = new StringBuilder();
        String fieldString = humpToUnderline(field.getName());
        // `field` = CASE
        sb.append(QUOTATION_MARKS_OPEN)
                .append(fieldString)
                .append(QUOTATION_MARKS_CLOSE)
                .append(EQUAL).append(CASE);
        for (T obj : dataList) {
            // WHEN (`primaryKey` = value)
            sb.append(WHEN);
            sb.append(QUOTE_OPEN)
                    .append(QUOTATION_MARKS_OPEN)
                    .append(humpToUnderline(primaryField.getName()))
                    .append(QUOTATION_MARKS_CLOSE)
                    .append(EQUAL);
            Object primary = primaryField.get(obj);
            if (primary == null) {
                throw new NullPointerException("更新主键值为空");
            }
            if (primary instanceof String || primary instanceof Character) {
                primary = primary.toString().replaceAll("\\\\", "");
                primary = primary.toString().replaceAll("'", "\\\\\'");
                primary = primary.toString().replaceAll("\"", "\\\\\"");
                sb.append(QUOTATION_MARKS).append(primary).append(QUOTATION_MARKS);
            } else {
                sb.append(primary);
            }
            sb.append(QUOTE_CLOSE);
            // THEN value
            sb.append(THEN);
            Object value = field.get(obj);
            if (value == null) {
                // 如果遇到了空值
                if (selective) {
                    // 非空值更新, 该字段等于其本身
                    sb.append(QUOTATION_MARKS_OPEN).append(fieldString).append(QUOTATION_MARKS_CLOSE);
                } else {
                    // 全量
                    sb.append(value);
                }
            } else if (value instanceof String || value instanceof Character) {
                value = value.toString().replaceAll("\\\\", "");
                value = value.toString().replaceAll("'", "\\\\\'");
                value = value.toString().replaceAll("\"", "\\\\\"");
                sb.append(QUOTATION_MARKS).append(value).append(QUOTATION_MARKS);
            } else {
                sb.append(value);
            }
        }
//        sb.append(ELSE);
//        sb.append(QUOTATION_MARKS_OPEN).append(fieldString).append(QUOTATION_MARKS_CLOSE);
        sb.append(END);
        return sb.toString();
    }

    /**
     * 获取条件语句
     *
     * @return
     */
    private String getWhereString() {
        StringBuilder sb = new StringBuilder();
        // 追加条件语句, 主键ID IN ();
        sb.append(WHERE)
                .append(QUOTATION_MARKS_OPEN).append(humpToUnderline(primaryField.getName())).append(QUOTATION_MARKS_CLOSE)
                .append(IN).append(QUOTE_OPEN);
        for (Object value : primaryValueList) {
            // 遍历主键值
            // 如果是String , 加上单引号
            if (value instanceof String || value instanceof Character) {
                value = value.toString().replaceAll("\\\\", "");
                value = value.toString().replaceAll("'", "\\\\\'");
                value = value.toString().replaceAll("\"", "\\\\\"");
                sb.append(QUOTATION_MARKS).append(value).append(QUOTATION_MARKS);
            } else {
                sb.append(value);
            }
            sb.append(COMMA);
        }
        sb.replace(sb.lastIndexOf(COMMA), sb.lastIndexOf(COMMA) + 1, "");
        sb.append(QUOTE_CLOSE);
        return sb.toString();
    }

    /**
     * 必要参数校验
     */
    private void paramCheck() {
        if (tableName == null || tableName.isEmpty()) {
            throw new RuntimeException("插入表名为空");
        }
        if (dataList.isEmpty()) {
            throw new RuntimeException("带插入数据集为空");
        }
        if (fields.size() == 0) {
            throw new RuntimeException("带插入字段为空");
        }
        if (ignoreFields != null && ignoreFields.length > 0) {
            // 过滤忽略字段
            HashSet<String> ignores = new HashSet<>();
            ignores.addAll(Arrays.asList(ignoreFields));
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                if (ignores.contains(humpToUnderline(field.getName()))) {
                    fields.remove(field);
                    i--;
                }
            }
        }
    }

    /**
     * 判断字段是否有值
     *
     * @param obj
     * @param field
     * @return
     */
    private boolean fieldHasValue(T obj, Field field) {
        Object value;
        if (!isAllowClassType(field)) {
            throw new RuntimeException("不允许的字段类型");
        }
        try {
            field.setAccessible(true);
            // 获取字段值, 判断是否为空
            value = field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("获取字段值错误");
        }
        if (Objects.nonNull(value)) {
            return true;
        }
        return false;
    }

    /**
     * 驼峰转下划线
     *
     * @param humpString
     * @return
     */
    @SuppressWarnings("all")
    private static String humpToUnderline(String humpString) {
        if (humpString == null || humpString.isEmpty()) {
            return "";
        }
        String prefix = "";
        if (humpString.charAt(0) == '_') {
            humpString = humpString.substring(1, humpString.length());
            prefix = "_";
        }
        String regexStr = "[A-Z0-9]";
        Matcher matcher = Pattern.compile(regexStr).matcher(humpString);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String g = matcher.group();
            matcher.appendReplacement(sb, "_" + g.toLowerCase());
        }
        matcher.appendTail(sb);
        if (sb.charAt(0) == '_') {
            sb.delete(0, 1);
        }
        sb.insert(0, prefix);
        return sb.toString();
    }
}
