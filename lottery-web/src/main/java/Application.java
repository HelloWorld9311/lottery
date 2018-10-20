import com.bruce.tool.common.util.SpringBeanUtils;
import com.lottery.security.annotation.EnableApiSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Prject: bqmart-sunshine
 * @Package: cn.bqmart
 * @Description: TODO
 * @author: wuyujia
 * @Date: 2017/10/28 23:17
 */
@EnableAsync
@EnableApiSecurity
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {
        "com.bruce.tool",
        "com.lottery"
})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        SpringBeanUtils.setApplicationContext(context);
    }
}
