package com.jhon.rain.authorize;

import com.jhon.rain.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> 自定义配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainbowAuthorizeConfigProvider
 * @date 2017/10/29 16:24
 */
@Component
public class RainbowAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Override
	public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configure) {
		configure.antMatchers(HttpMethod.GET,"/user*//*").hasRole("ADMIN")
						.antMatchers("/resources/rainbow.html").hasRole("SUPER_ADMIN")
						.antMatchers("/user/register","/social/signUp");
	}
}
