package com.jhon.rain.security.browser;

import com.jhon.rain.security.browser.logout.RainLogoutSuccessHandler;
import com.jhon.rain.security.browser.session.RainExpiredSessionStrategy;
import com.jhon.rain.security.browser.session.RainInvalidSessionStrategy;
import com.jhon.rain.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * <p>功能描述</br> 浏览器按钮配置bean</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BrowserSecurityBeanConfig
 * @date 2017/10/26 17:58
 */
@Configuration
public class BrowserSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new RainInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}

	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new RainExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}

	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new RainLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
	}
}
