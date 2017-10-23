package com.jhon.rain.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhon.rain.security.core.enums.LoginResponseTypeEnum;
import com.jhon.rain.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>功能描述</br> 失败处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainAuthenticationFailureHandler
 * @date 2017/10/19 22:08
 */
@Component("rainAuthenticationFailureHandler")
@Slf4j
public class RainAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	                                    AuthenticationException exception)
					throws IOException, ServletException {

		log.info("登录失败");

		if (LoginResponseTypeEnum.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
		}else{
			super.onAuthenticationFailure(request, response, exception);
		}
	}
}
