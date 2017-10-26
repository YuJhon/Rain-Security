package com.jhon.rain.security.core.validate.code.base;

import com.jhon.rain.security.core.enums.ValidateCodeTypeEnum;
import com.jhon.rain.security.core.validate.code.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * <p>功能描述</br> 验证码处理的抽象类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AbstractValidateCodeProcessor
 * @date 2017/10/23 20:10
 */
public abstract class AbstractValidateCodeProcessor<C extends BaseValidateCode> implements ValidateCodeProcessor {

	/**
	 * 操作Session的工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	/**
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	@Override
	public void create(ServletWebRequest request) throws Exception {
		/** 1.生成验证码 **/
		C validateCode = generate(request);
		/** 2.保存到session **/
		save(request, validateCode);
		/** 3.发送验证码 **/
		send(request, validateCode);
	}

	/**
	 * <pre>发送验证码 ：抽象类，子类自己实现 </pre>
	 *
	 * @param request 请求对象
	 * @param validateCode 验证码
	 * @throws Exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

	/**
	 * <pre>保存验证码属性到session中 </pre>
	 *
	 * @param request 请求对象
	 * @param validateCode 验证码
	 */
	private void save(ServletWebRequest request, C validateCode) {

		BaseValidateCode baseValidateCode = new BaseValidateCode(validateCode.getCode(),validateCode.getExpireTime());

		sessionStrategy.setAttribute(request, getSessionKey(request), baseValidateCode);
	}

	@Override
	public void validate(ServletWebRequest request) {
		/** 获取属性 **/
		ValidateCodeTypeEnum processorType = getValidateCodeType(request);
		String sessionKey = getSessionKey(request);
		C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

		/** 在http request中携带的验证码的值 **/
		String codeInRequest;
		try {
			/** processorType.getParamNameOnValidate() 是Form表单中的属性名称 **/
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
							processorType.getParamNameOnValidate());

		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码的值不能为空");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException(processorType + "验证码不存在");
		}

		if (codeInSession.isExpired()) {
			sessionStrategy.removeAttribute(request, sessionKey);
			throw new ValidateCodeException(processorType + "验证码已过期");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(processorType + "验证码不匹配");
		}
		/** 校验完之后，清空session中的值 **/
		sessionStrategy.removeAttribute(request, sessionKey);
	}

	/**
	 * 获取session中的信息
	 *
	 * @param request
	 * @return
	 */
	private String getSessionKey(ServletWebRequest request) {
		return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
	}

	/**
	 * <pre>根据请求的url获取校验码的类型</pre>
	 *
	 * @param request
	 * @return
	 */
	private ValidateCodeTypeEnum getValidateCodeType(ServletWebRequest request) {
		/** 根据调用类类名来判断是那种类型 **/
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
		return ValidateCodeTypeEnum.valueOf(type.toUpperCase());
	}

	/**
	 * <pre>生成验证码</pre>
	 *
	 * @param request
	 * @return
	 */
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType(request).toString().toLowerCase();
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器{" + generatorName + "}不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

}
