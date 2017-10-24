package com.jhon.rain.security.core.authentication.mobile;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>功能描述</br> 短信验证码的Provider  </p>
 * {@link org.springframework.security.authentication.AuthenticationManager}
 * @author jiangy19
 * @version v1.0
 * @FileName SmsCodeAuthenticationProvider
 * @date 2017/10/24 22:03
 */
@Data
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
		/** 通过手机号获取用户信息 **/
		UserDetails user = userDetailsService.loadUserByUsername((String)smsCodeAuthenticationToken.getPrincipal());
		if (user == null){
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		/** 重新组装信息 **/
		SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(user,user.getAuthorities());
		authenticationToken.setDetails(smsCodeAuthenticationToken.getDetails());
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
