package com.jhon.rain.security.core.validate.code.filter;

import com.jhon.rain.security.core.constants.RainSecurityConstants;
import com.jhon.rain.security.core.enums.ValidateCodeTypeEnum;
import com.jhon.rain.security.core.properties.SecurityProperties;
import com.jhon.rain.security.core.validate.code.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>功能描述</br> 验证码的校验的过滤器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ValidateCodeFilter
 * @date 2017/10/20 11:24
 */
@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

	/**
	 * 验证码校验失败处理器
	 */
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	/**
	 * 系统属性配置类
	 */
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * url匹配工具
	 */
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	/**
	 * 存放需要校验的url和对应的类型
	 */
	private Map<String, ValidateCodeTypeEnum> urlMap = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		urlMap.put(RainSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeTypeEnum.IMAGE);
		addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeTypeEnum.IMAGE);

		/** 短信验证码 TODO **/
		urlMap.put(RainSecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeTypeEnum.SMS);
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain filterChain)
					throws ServletException, IOException {

		ValidateCodeTypeEnum type = getValidateCodeType(request);
		if (type != null) {
			log.info("校验请求({})中的验证码，验证码类型为{}",request.getRequestURI(),type);
			try{
			    // TODO  验证码的校验

			}catch(ValidateCodeException e){
			    e.printStackTrace();
					authenticationFailureHandler.onAuthenticationFailure(request, response, e);
					return;
			}
		}

		filterChain.doFilter(request, response);
	}


	/**
	 * <pre>将系统中需要校验的url根据校验类型放入map中</pre>
	 *
	 * @param urlStr
	 * @param type
	 */
	protected void addUrlToMap(String urlStr, ValidateCodeTypeEnum type) {
		if (StringUtils.isNotBlank(urlStr)) {
			String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlStr, ",");
			for (String url : urls) {
				urlMap.put(url, type);
			}
		}
	}

	/**
	 * <pre>获取校验码类型，如果当前请求不需要校验，则返回null</pre>
	 *
	 * @param request
	 * @return
	 */
	private ValidateCodeTypeEnum getValidateCodeType(HttpServletRequest request) {
		ValidateCodeTypeEnum result = null;
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
			Set<String> urls = urlMap.keySet();
			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					result = urlMap.get(url);
					break;
				}
			}
		}
		return result;
	}


	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}
}
