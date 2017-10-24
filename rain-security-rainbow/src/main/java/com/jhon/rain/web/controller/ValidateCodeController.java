package com.jhon.rain.web.controller;

import com.jhon.rain.security.core.constants.RainSecurityConstants;
import com.jhon.rain.security.core.validate.code.base.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>功能描述</br> 验证码的控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeController
 * @date 2017/10/20 10:12
 */
@RestController
public class ValidateCodeController {

	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	/**
	 * <pre>创建验证码，根据验证码类型不同，调用不同的
	 * {@link com.jhon.rain.security.core.validate.code.base.ValidateCodeProcessor}接口实现
	 * </pre>
	 *
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param type     验证码类型（image/sms）
	 * @throws Exception
	 */
	@GetMapping(RainSecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
					throws Exception {

		validateCodeProcessorHolder
						.findValidateCodeProcessor(type)
						.create(new ServletWebRequest(request, response));
	}
}
