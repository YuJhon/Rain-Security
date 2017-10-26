package com.jhon.rain.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * <p>功能描述</br> </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainSpringSocialConfigurer
 * @date 2017/10/25 16:21
 */
public class RainSpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;

	public RainSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter)super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}
}
