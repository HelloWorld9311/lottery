package web.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @Prject: bqmart-sunshine
 * @Package: cn.bqmart.web.druid
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/11/14 14:48
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
            @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),
        }
)
public class DruidStatFilter extends WebStatFilter {

}
