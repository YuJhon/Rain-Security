package com.jhon.rain.security.browser.repository.impl;

import com.jhon.rain.security.core.enums.ValidateCodeTypeEnum;
import com.jhon.rain.security.core.validate.code.base.BaseValidateCode;
import com.jhon.rain.security.core.validate.code.repository.ValidateCodeRepository;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>功能描述</br> 浏览器验证码的处理 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SessionValidateCodeRepository
 * @date 2017/10/28 17:05
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

	/**
	 * 操作Session的工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Override
	public void save(ServletWebRequest request, BaseValidateCode code, ValidateCodeTypeEnum validateCodeType) {
		sessionStrategy.setAttribute(request,getSessionKey(request,validateCodeType),code);
	}

	@Override
	public BaseValidateCode get(ServletWebRequest request, ValidateCodeTypeEnum validateCodeType) {
		return (BaseValidateCode)sessionStrategy.getAttribute(request,getSessionKey(request,validateCodeType));
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeTypeEnum validateCodeType) {
		sessionStrategy.removeAttribute(request, getSessionKey(request,validateCodeType));
	}

	/**
	 * 获取session中的信息
	 *
	 * @param request
	 * @return
	 */
	private String getSessionKey(ServletWebRequest request,ValidateCodeTypeEnum validateCodeType) {
		return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
	}
}
