package com.jhon.rain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>功能描述</br> 启动程序 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainbowApplication
 * @date 2017/10/15 15:35
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class RainbowApplication {

	public static void main(String[] args) {
		SpringApplication.run(RainbowApplication.class, args);
	}

}
