package com.jhon.rain.web.config;

import com.jhon.rain.web.filter.RainConsumeTimeFilter;
import com.jhon.rain.web.interceptor.RainConsumeTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> web配置</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WebConfig
 * @date 2017/10/17 11:37
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/*
	@Autowired
	private RainConsumeTimeInterceptor timeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}


	@Bean
	public FilterRegistrationBean timeFilter() {

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		RainConsumeTimeFilter timeFilter = new RainConsumeTimeFilter();
		registrationBean.setFilter(timeFilter);

		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);

		return registrationBean;
	}
	*/

}
