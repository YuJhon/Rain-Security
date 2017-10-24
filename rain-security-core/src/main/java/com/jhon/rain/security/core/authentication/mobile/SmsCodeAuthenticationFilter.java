package com.jhon.rain.security.core.authentication.mobile;

import com.jhon.rain.security.core.constants.RainSecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>功能描述</br> 短信验证码的授权过滤器
 * 模拟{@link org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter}实现
 * </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SmsCodeAuthenticationFilter
 * @date 2017/10/24 21:55
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private String mobileParameter = RainSecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

	private boolean postOnly = true;

	public SmsCodeAuthenticationFilter() {
		super(new AntPathRequestMatcher(RainSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if(this.postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		} else {
			String mobile = this.obtainMobile(request);
			if(mobile == null) {
				mobile = "";
			}
			mobile = mobile.trim();

			SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
			this.setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		}
	}

	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(this.mobileParameter);
	}

	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
	}

	public void setMobileParameter(String mobileParameter) {
		Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
		this.mobileParameter = mobileParameter;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getMobileParameter() {
		return this.mobileParameter;
	}

}
