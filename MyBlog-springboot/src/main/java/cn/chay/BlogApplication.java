package cn.chay;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Created by xiaoni on 2020/2/15.
 */
@SpringBootApplication
@ComponentScan
@tk.mybatis.spring.annotation.MapperScan("cn.chay.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
