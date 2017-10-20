package com.jhon.rain.security.core.validate.code.filter;

import com.jhon.rain.security.core.constants.RainSecurityConstants;
import com.jhon.rain.security.core.validate.code.exception.ValidateCodeException;
import com.jhon.rain.security.core.validate.code.image.ImageCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>功能描述</br> 验证码的校验的过滤器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeFilter
 * @date 2017/10/20 11:24
 */
@Component("validateCodeFilter")
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

	private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

	private AuthenticationFailureHandler authenticationFailureHandler;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain filterChain)
					throws ServletException, IOException {

		if (StringUtils.equals(RainSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, request.getRequestURI())
						&& StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {

			try {
				/** 验证码的校验 TODO **/
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * 验证码的校验
	 *
	 * @param request
	 */
	private void validate(ServletWebRequest request) {
		/** 获取属性 **/
		ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, SESSION_KEY);

		String codeInRequest;
		try {
			/** imageCode是Form表单中的属性名称 **/
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码的值不能为空");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException("验证码不存在");
		}

		if (codeInSession.isExpired()) {
			sessionStrategy.removeAttribute(request, SESSION_KEY);
			throw new ValidateCodeException("验证码已过期");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码不匹配");
		}

		sessionStrategy.removeAttribute(request, SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}
}
