package com.jhon.rain.security.core.authorize;

import com.jhon.rain.security.core.constants.RainSecurityConstants;
import com.jhon.rain.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> 公共的默认实现配置 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainAuthorizeConfigProvider
 * @date 2017/10/29 16:13
 */
@Component
@Order(Integer.MIN_VALUE)
public class RainAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configure) {
		configure.antMatchers(
						/** 默认未授权处理接口地址 **/
						RainSecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						/** 登录的页面【默认是系统默认的，可以自定义配置】 **/
						securityProperties.getBrowser().getLoginPage(),
						/** 默认手机验证码接口处理地址 **/
						RainSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
						/** 生成验证码的接口地址 **/
						RainSecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
						securityProperties.getBrowser().getSignUpUrl(),
						/** session失效跳转的链接地址 **/
						securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".json",
						securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html",
						/** 自定义退出地址 **/
						securityProperties.getBrowser().getSignOutUrl())
						.permitAll();

	}
}
