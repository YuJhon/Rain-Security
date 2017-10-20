package com.jhon.rain.security.core.validate.code.base;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>功能描述</br> 校验码的生成器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BaseValidateCodeGenerator
 * @date 2017/10/20 9:43
 */
public interface BaseValidateCodeGenerator {

	/**
	 * <pre>生成校验码</pre>
	 *
	 * @param request request对象
	 * @return
	 */
	BaseValidateCode generate(ServletWebRequest request);
}
