package com.jhon.rain.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * <p>功能描述</br> 权限模块的接口抽取 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AuthorizeConfigProvider
 * @date 2017/10/29 16:11
 */
public interface AuthorizeConfigProvider {
	/**
	 * <pre>自定义配置</pre>
	 *
	 * @param configure
	 */
	void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configure);
}
