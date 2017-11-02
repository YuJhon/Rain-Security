package com.jhon.rain.security.core.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <p>功能描述</br> 默认的用户接口实现类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName DefaultUserDetailsService
 * @date 2017/11/2 20:18
 */
@Slf4j
public class DefaultUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("请配置UserDetailsService接口实现");
		throw new UsernameNotFoundException(username);
	}
}
