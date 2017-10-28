package com.jhon.rain.security.app;

import com.jhon.rain.security.core.social.RainSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> RainSpringSocialConfigurer Bean初始化后都要经过下面重写的两个方法 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SpringSocialConfigurerPostProcessor
 * @date 2017/10/28 21:19
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (StringUtils.equals(beanName,"rainSocailSecurityConfig")){
			RainSpringSocialConfigurer configurer = (RainSpringSocialConfigurer) bean;
			configurer.signupUrl("/social/signUp");
			return configurer;
		}
		return bean;
	}
}
