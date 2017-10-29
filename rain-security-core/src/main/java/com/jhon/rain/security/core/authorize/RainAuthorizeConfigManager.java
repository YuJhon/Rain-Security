package com.jhon.rain.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainAuthorizeConfigManager
 * @date 2017/10/29 16:17
 */
@Component
public class RainAuthorizeConfigManager implements AuthorizeConfigManager{

	@Autowired
	private Set<AuthorizeConfigProvider> authorizeConfigProviders;

	@Override
	public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configure) {
		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
			authorizeConfigProvider.configure(configure);
		}
		configure.anyRequest().authenticated();
	}
}
