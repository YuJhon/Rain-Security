package com.jhon.rain.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> 自定义的用户信息查询接口 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName CustomerUserService
 * @date 2017/10/18 22:06
 */
@Component
@Slf4j
public class CustomerUserService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/** 根据用户名，查询用户信息 TODO DB Operation **/
		log.info("登陆用户名{}",username);
		/**　判断用户是否冻结　,注意：此处可以使用自定义的User类去实现UserDetails接口，然后实现相应的四个方法的判断逻辑 **/
		String enPassword = passwordEncoder.encode("123456");
		log.info("登陆的用户密码是：{}",enPassword);
		return new User(username,enPassword,
						true,true,true,true,
						AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
}
