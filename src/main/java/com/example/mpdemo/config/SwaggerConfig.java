package com.example.mpdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/** 是否开启swagger */
	@Value("${swagger.enabled}")
	private boolean enabled;

	/** API版本 */
	@Value("${swagger.version}")
	private String version;

	/**
	 * 创建Swagger
	 */
	@Bean
	public Docket createSwagger() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				// 是否启用Swagger
				.enable(enabled)
				// 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
				.apiInfo(apiInfo())
				// 设置哪些接口暴露给Swagger展示
				.select()
				// 扫描所有有注解的api，用这种方式更灵活
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				// 扫描指定包中的swagger注解.apis(RequestHandlerSelectors.basePackage("com.example.mpdemo.web"))
				// 扫描所有.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
		return docket;
	}

	/**
	 * 添加摘要信息
	 */
	private ApiInfo apiInfo() {
		// 用ApiInfoBuilder进行定制
		 ApiInfo apiInfo = new ApiInfoBuilder()
				// 设置标题
				.title("标题：供应商云平台系统_接口文档")
				// 描述
				.description("描述：用于管理医院和供应商之间的业务关系")
				// 版本
				.version("版本号：" + version).build();
		 return apiInfo;
	}
}
