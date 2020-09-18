package com.jinhe;
import com.jinhe.datasources.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Import(DynamicDataSourceConfig.class )
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = { "com.jinhe.modules.*.dao"})

//开启事务注解
//@Configuration
@EnableTransactionManagement
//@EnableWebMvc
//@EnableScheduling
public class Application  { //extends SpringBootServletInitializer
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Application.class);
//	}
}
