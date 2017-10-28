package com.jhon.rain.security.core.authentication.open;

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
 * <p>功能描述</br> 使用openId登录的过滤器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OpenIdAuthenticationFilter
 * @date 2017/10/28 18:44
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


	// ~ Static fields/initializers
	// =====================================================================================

	private String openIdParameter = RainSecurityConstants.DEFAULT_PARAMETER_NAME_OPENID;
	private String providerIdParameter = RainSecurityConstants.DEFAULT_PARAMETER_NAME_PROVIDERID;
	private boolean postOnly = true;

	// ~ Constructors
	// ===================================================================================================

	public OpenIdAuthenticationFilter() {
		super(new AntPathRequestMatcher(RainSecurityConstants.DEFAULT_LOGIN_PROCESS_URL_OPENID, "POST"));
	}

	// ~ Methods
	// ========================================================================================================

	public Authentication attemptAuthentication(HttpServletRequest request,
	                                            HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
							"Authentication method not supported: " + request.getMethod());
		}

		String openId = obtainOpenId(request);
		String providerId = obtainProviderId(request);

		if (openId == null) {
			openId = "";
		}

		if (providerId == null) {
			providerId = "";
		}

		openId = openId.trim();

		OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId,providerId);
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * Enables subclasses to override the composition of the password, such as by
	 * including additional values and a separator.
	 * <p>
	 * This might be used for example if a postcode/zipcode was required in addition to
	 * the password. A delimiter such as a pipe (|) should be used to separate the
	 * password and extended value(s). The <code>AuthenticationDao</code> will need to
	 * generate the expected password in a corresponding manner.
	 * </p>
	 *
	 * @param request so that request attributes can be retrieved
	 *
	 * @return the password that will be presented in the <code>Authentication</code>
	 * request token to the <code>AuthenticationManager</code>
	 */
	protected String obtainOpenId(HttpServletRequest request) {
		return request.getParameter(openIdParameter);
	}

	/**
	 * Enables subclasses to override the composition of the username, such as by
	 * including additional values and a separator.
	 *
	 * @param request so that request attributes can be retrieved
	 *
	 * @return the username that will be presented in the <code>Authentication</code>
	 * request token to the <code>AuthenticationManager</code>
	 */
	protected String obtainProviderId(HttpServletRequest request) {
		return request.getParameter(providerIdParameter);
	}

	/**
	 * Provided so that subclasses may configure what is put into the authentication
	 * request's details property.
	 *
	 * @param request that an authentication request is being created for
	 * @param authRequest the authentication request object that should have its details
	 * set
	 */
	protected void setDetails(HttpServletRequest request,
	                          OpenIdAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	/**
	 * Sets the parameter name which will be used to obtain the username from the login
	 * request.
	 *
	 * @param openIdParameter the parameter name. Defaults to "username".
	 */
	public void setOpenIdParameter(String openIdParameter) {
		Assert.hasText(openIdParameter, "OpenID parameter must not be empty or null");
		this.openIdParameter = openIdParameter;
	}

	/**
	 * Sets the parameter name which will be used to obtain the password from the login
	 * request..
	 *
	 * @param providerIdParameter the parameter name. Defaults to "password".
	 */
	public void setProviderIdParameter(String providerIdParameter) {
		Assert.hasText(providerIdParameter, "Password parameter must not be empty or null");
		this.providerIdParameter = providerIdParameter;
	}

	/**
	 * Defines whether only HTTP POST requests will be allowed by this filter. If set to
	 * true, and an authentication request is received which is not a POST request, an
	 * exception will be raised immediately and authentication will not be attempted. The
	 * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
	 * authentication.
	 * <p>
	 * Defaults to <tt>true</tt> but may be overridden by subclasses.
	 */
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getOpenIdParameter() {
		return openIdParameter;
	}

	public final String getProviderIdParameter() {
		return providerIdParameter;
	}
}
