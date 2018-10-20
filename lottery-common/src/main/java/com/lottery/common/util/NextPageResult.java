package com.lottery.common.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhangjin on 16/11/2.
 */
@SuppressWarnings("rawtypes")
public class NextPageResult {

    private NextPageResult() {
    }

    public static NextPageResult create() {

        return new NextPageResult();
    }

    public NextPageResult next(Integer page) {
//        this.next = page;
        this.setNext(page);
        return this;
    }

    public NextPageResult size(Integer size) {
        this.setSize(size);
        return this;
    }


    public <T> NextPageResult data(List<T> list, Integer page, Integer size) {
        if ( Objects.nonNull(page) && Objects.nonNull(size) && list != null && list.size() > size) {
            list.remove(list.size() - 1);
            this.list = list;
            this.next(page + 1);
            this.size(size);
            this.data(list);
        } else {
            this.list = list;
            this.next(0);
            this.size(list.size());
            this.data(list);
        }
        return this;
    }


    //单独交易记录用到的分页数据处理 因为第2页以后 多查了一位数据 判断是否需要处理日期
    public <T> NextPageResult dataSpecial(List<T> list, Integer page, Integer size) {
        if(page==1){
            if (list != null && list.size() > size) {
                list.remove(list.size() - 1);
                this.list = list;
                this.next(page + 1);
                this.size(size);
                this.data(list);
            } else {
                this.list = list;
                this.next(0);
                this.size(list.size());
                this.data(list);
            }
        }else{
            if (list != null && list.size() > (size+1)) {
                list.remove(list.size() - 1);
                this.list = list;
                this.next(page + 1);
                this.size(size);
                this.data(list);
            } else {
                this.list = list;
                this.next(0);
                this.size(list.size());
                this.data(list);
            }
        }

        return this;
    }


    public <T> NextPageResult data(List<T> list) {
        this.list = list;
        return this;
    }

    /**
     * 自动全属性拷贝
     **/
    public <E> NextPageResult copy(Class<E> clazz, String... ignores) {
        List<E> result = new ArrayList<E>();
        try {
            if (null == this.list || this.list.isEmpty()) {
                return this;
            }
            for (Object object : this.list) {
                E e = clazz.newInstance();
                BeanUtils.copyProperties(object, e, ignores);
                result.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.size() == this.list.size()) {
            this.list = result;
        }
        return this;
    }

    /**
     * {@link NextPageResult.copy(List<O> data,ParamsCopyHandler<O,N> handler)}替代
     *
     * @param handler
     * @return
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public <O, N> NextPageResult copy(ParamsCopyHandler<O, N> handler) {
        List<N> result = new ArrayList<N>();
        try {
            if (null == this.list || this.list.isEmpty()) {
                return this;
            }
            Class<N> clazz = getGenericityClass(handler);
            for (Object object : this.list) {
                N e = clazz.newInstance();
                e = handler.handle((O) object);
                result.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.size() == this.list.size()) {
            this.list = result;
        }
        return this;
    }

    /**
     * 自定义属性拷贝
     **/
    public <O, N> NextPageResult copy(List<O> data, ParamsCopyHandler<O, N> handler) throws Exception {
        List<N> result = new ArrayList<N>();
        try {
            Class<N> clazz = getGenericityClass(handler);
            for (O old : data) {
                N e = clazz.newInstance();
                e = handler.handle(old);
                result.add(e);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (result.size() == data.size()) {
            this.list = result;
        }
        return this;
    }

    /**
     * 填充字段
     **/
    @SuppressWarnings("unchecked")
    public <O> NextPageResult fillFields(ParamsFillHandler<O> handler) throws Exception {
        List<O> result = new ArrayList<O>();
        try {
            for (Object old : list) {
                O o = handler.handle((O) old);
                result.add(o);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (result.size() == list.size()) {
            this.list = result;
        }

        return this;
    }


    @SuppressWarnings("unchecked")
    private <N, O> Class<N> getGenericityClass(ParamsCopyHandler<O, N> handler) {
        Type[] types = handler.getClass().getGenericInterfaces();
        ParameterizedType type = (ParameterizedType) types[0];
        Class<N> clazz = (Class<N>) type.getActualTypeArguments()[1];
        return clazz;
    }

    @SuppressWarnings("unchecked")
    public <T> NextPageResult add(T obj) {
        if (null == list) {
            list = new ArrayList<T>();
        }
        list.add(obj);
        return this;
    }

    Integer next;
    Integer size;
    List list = new ArrayList(0);

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

}

