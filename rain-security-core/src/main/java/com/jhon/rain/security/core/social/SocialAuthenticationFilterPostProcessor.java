package com.jhon.rain.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * <p>功能描述</br> 社交授权过滤器的后处理器</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SocialAuthenticationFilterPostProcessor
 * @date 2017/10/28 20:49
 */
public interface SocialAuthenticationFilterPostProcessor {
	/**
	 * <pre>处理逻辑</pre>
	 * @param socialAuthenticationFilter
	 */
	void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
