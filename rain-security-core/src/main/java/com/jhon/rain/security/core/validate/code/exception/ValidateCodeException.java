package com.jhon.rain.security.core.validate.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>功能描述</br> 验证码异常的定义 ： 继承自Spring Security AuthenticationException </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeException
 * @date 2017/10/20 11:33
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = 7359958698303648342L;

	public ValidateCodeException(String msg) {
		super(msg);
	}
}
