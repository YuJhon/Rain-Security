package com.jhon.rain.rbac.service.impl;

import com.jhon.rain.rbac.domain.RainUserDO;
import com.jhon.rain.rbac.service.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * <p>功能描述</br> Rbac的实现类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RbacServiceImpl
 * @date 2017/11/3 9:01
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();

		boolean hasPermission = false;
		if (principal instanceof RainUserDO) {
			/** 如果用户名是admin,就永远返回true **/
			if (StringUtils.pathEquals(((RainUserDO) principal).getUsername(), "admin")) {
				hasPermission = true;
			} else {
				/** 读取用户所拥有的所有权限的所有url **/
				Set<String> urls = ((RainUserDO) principal).getUrls();
				for (String url : urls) {
					if (antPathMatcher.match(url, request.getRequestURI())) {
						hasPermission = true;
						break;
					}
				}
			}
		}
		return hasPermission;
	}
}
