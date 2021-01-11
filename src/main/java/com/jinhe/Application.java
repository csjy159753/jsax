package com.jinhe;

import com.jinhe.datasources.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
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
import springfox.documentation.oas.annotations.EnableOpenApi;


@EnableOpenApi
@Import(DynamicDataSourceConfig.class)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = {"com.jinhe.modules.*.dao"})

//开启事务注解
//@Configuration
@EnableTransactionManagement
//@EnableWebMvc
//@EnableScheduling
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
		return springApplicationBuilder.sources(Application.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("启动！！！!15");
	}
}