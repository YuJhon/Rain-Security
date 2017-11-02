package com.jhon.rain.rbac.authentication;

import com.jhon.rain.rbac.domain.RainUserDO;
import com.jhon.rain.rbac.repository.RainUserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>功能描述</br> 授权接口的的实现 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RbacUserDetailsService
 * @date 2017/11/2 19:39
 */
@Slf4j
@Data
@Component
@Transactional
public class RbacUserDetailsService implements UserDetailsService {

	@Autowired
	private RainUserRepository rainUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("表单登录用户名：{}", username);
		RainUserDO user = rainUserRepository.findByUsername(username);
		user.getUrls();
		return user;
	}
}
