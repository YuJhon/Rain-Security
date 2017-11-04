package com.jhon.rain.rbac.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>功能描述</br> RBAC 业务相关接口 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RbacService
 * @date 2017/11/3 8:59
 */
public interface RbacService {
	/**
	 * <pre>是否有权限</pre>
	 *
	 * @param request
	 * @param authentication
	 * @return
	 */
	boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
