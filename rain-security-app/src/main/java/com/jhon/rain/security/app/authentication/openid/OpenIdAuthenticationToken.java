package com.jhon.rain.security.app.authentication.openid;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * <p>功能描述</br> OpenId登录验证的Token</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OpenIdAuthenticationToken
 * @date 2017/10/28 18:34
 */
public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {


	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	// ~ Instance fields
	// ================================================================================================

	private final Object principal; // openId
	private String providerId; // qq or weixin

	// ~ Constructors
	// ===================================================================================================

	/**
	 * This constructor can be safely used by any code that wishes to create a
	 * <code>UsernamePasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
	 * will return <code>false</code>.
	 *
	 */
	public OpenIdAuthenticationToken(String openId, String providerId) {
		super(null);
		this.principal = openId;
		this.providerId = providerId;
		setAuthenticated(false);
	}

	/**
	 * This constructor should only be used by <code>AuthenticationManager</code> or
	 * <code>AuthenticationProvider</code> implementations that are satisfied with
	 * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
	 * authentication token.
	 *
	 * @param openId
	 * @param authorities
	 */
	public OpenIdAuthenticationToken(Object openId,
	                                           Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = openId;
		super.setAuthenticated(true); // must use super, as we override
	}

	// ~ Methods
	// ========================================================================================================


	public String getProviderId() {
		return providerId;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	public Object getPrincipal() {
		return this.principal;
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
							"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
	}
}
