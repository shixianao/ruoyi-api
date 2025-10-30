package com.erp.framework.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import com.erp.framework.config.properties.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

/**
 * Swagger 文档配置
 *
 * @author Lion Li
 */
@Configuration
@EnableKnife4j
public class SwaggerConfig {

	@Autowired
	private SwaggerProperties swaggerProperties;
	
	@Bean
	public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
	    return new BeanPostProcessor() {
	 
	        @Override
	        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	            if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
	                customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
	            }
	            return bean;
	        }
	 
	        private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
	            List<T> copy = mappings.stream()
	                    .filter(mapping -> mapping.getPatternParser() == null)
	                    .collect(Collectors.toList());
	            mappings.clear();
	            mappings.addAll(copy);
	        }
	 
	        @SuppressWarnings("unchecked")
	        private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
	            try {
	                Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
	                field.setAccessible(true);
	                return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
	            } catch (IllegalArgumentException | IllegalAccessException e) {
	                throw new IllegalStateException(e);
	            }
	        }
	    };
	}
	
	/**
	 * 创建API
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30)
			.enable(swaggerProperties.getEnabled())
			// 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
			.apiInfo(apiInfo())
			.groupName("1.全部接口")
			// 设置哪些接口暴露给Swagger展示
			.select()
			// 扫描所有有注解的api，用这种方式更灵活
			.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
			// 扫描指定包中的swagger注解
			// .apis(RequestHandlerSelectors.basePackage("com.erp.project.tool.swagger"))
			// 扫描所有 .apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build()
			/* 设置安全模式，swagger可以设置访问token */
			.securitySchemes(securitySchemes())
			.securityContexts(securityContexts())
			.pathMapping(swaggerProperties.getPathMapping());
	}
	@Bean
	public Docket hqRestApi() {
		return new Docket(DocumentationType.OAS_30)
			.enable(swaggerProperties.getEnabled())
			.apiInfo(apiInfo())
			.groupName("2.工作流接口")
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.erp.module.bpm"))
			.paths(PathSelectors.any())
			.build()
			.securitySchemes(securitySchemes())
			.securityContexts(securityContexts())
			.pathMapping(swaggerProperties.getPathMapping());
	}

	/**
	 * 安全模式，这里指定token通过Authorization头请求头传递
	 */
	private List<SecurityScheme> securitySchemes() {
		List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
		apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
		return apiKeyList;
	}

	/**
	 * 安全上下文
	 */
	private List<SecurityContext> securityContexts() {
		List<SecurityContext> securityContexts = new ArrayList<>();
		securityContexts.add(
			SecurityContext.builder()
				.securityReferences(defaultAuth())
				.operationSelector(o -> o.requestMappingPattern().matches("/.*"))
				.build());
		return securityContexts;
	}

	/**
	 * 默认的安全上引用
	 */
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> securityReferences = new ArrayList<>();
		securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
		return securityReferences;
	}

	/**
	 * 添加摘要信息
	 */
	private ApiInfo apiInfo() {
		// 用ApiInfoBuilder进行定制
		SwaggerProperties.Contact contact = swaggerProperties.getContact();
		return new ApiInfoBuilder()
			// 设置标题
			.title(swaggerProperties.getTitle())
			// 描述
			.description(swaggerProperties.getDescription())
			// 作者信息
			.contact(new Contact(contact.getName(), contact.getUrl(), contact.getEmail()))
			// 版本
			.version(swaggerProperties.getVersion())
			.build();
	}
}
