package com.jhon.rain.security.core.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * <p>功能描述</br> 默认的社交的登录接口的实现类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName DefaultSocialUserDetailsService
 * @date 2017/11/2 20:17
 */
@Slf4j
public class DefaultSocialUserDetailsService implements SocialUserDetailsService {

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.warn("请配置SocialUserDetailsService 接口的实现");
		throw new UsernameNotFoundException(userId);
	}
}
