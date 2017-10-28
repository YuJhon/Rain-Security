package com.jhon.rain.security.core.validate.code.base;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>功能描述</br> 验证码的处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeProcessor
 * @date 2017/10/20 9:44
 */
public interface ValidateCodeProcessor {

	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

	/**
	 * <pre>创建验证码</pre>
	 *
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * <pre>检验验证码</pre>
	 *
	 * @param request
	 */
	void validate(ServletWebRequest request);
}
