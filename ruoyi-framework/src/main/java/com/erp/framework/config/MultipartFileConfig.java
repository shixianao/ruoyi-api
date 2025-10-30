package com.erp.framework.config;

import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;


@Configuration
public class MultipartFileConfig {

	@Value("${spring.servlet.multipart.location}")
	private String tmpLocation;

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		if (!FileUtil.exist(tmpLocation)) {
			FileUtil.mkdir(tmpLocation);
		}
		factory.setLocation(tmpLocation);
		return factory.createMultipartConfig();
	}

}
