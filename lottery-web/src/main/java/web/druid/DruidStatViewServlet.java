package web.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @Prject: bqmart-sunshine
 * @Package: cn.bqmart.web.druid
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/11/14 14:47
 */
@WebServlet(urlPatterns = "/druid/*",
    initParams = {
            @WebInitParam(name = "loginUsername", value = "bqmart"),
            @WebInitParam(name = "loginPassword", value = "beiquan8780"),
            @WebInitParam(name = "resetEnable", value = "false")
    }
)
public class DruidStatViewServlet extends StatViewServlet {

    private static final long serialVersionUID = 4931831777483212819L;
}
