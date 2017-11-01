package com.jhon.rain.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RBACService
 * @date 2017/10/30 8:54
 */
public interface RBACService {

	boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
