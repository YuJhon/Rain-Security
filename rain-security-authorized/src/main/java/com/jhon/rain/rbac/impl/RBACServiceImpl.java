package com.jhon.rain.rbac.impl;

import com.jhon.rain.rbac.RBACService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RBACServiceImpl
 * @date 2017/10/30 8:55
 */
@Component("rbacService")
public class RBACServiceImpl implements RBACService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		boolean hasPermisson = false;
		if (principal instanceof UserDetailsService) {
			String username = ((UserDetails) principal).getUsername();
			// TODO 读取数据库获取用户的角色和权限的url
			Set<String> urls = new HashSet<>();
			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					hasPermisson = true;
					break;
				}
			}
		}
		return hasPermisson;
	}
}
