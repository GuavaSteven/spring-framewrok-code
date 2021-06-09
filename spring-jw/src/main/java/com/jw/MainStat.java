package com.jw;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author cjw
 * @Description:
 * @date 2021/5/10 18:09
 */
@Configuration
@ComponentScan("com.jw")
public class MainStat {
	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext(MainStat.class);
		context.getBean(UserSrvice.class).sayHi();

	}
	@Bean
	public UserSrvice userSrvice(){
		return new UserSrvice();
	}

}
