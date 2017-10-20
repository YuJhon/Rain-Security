package com.jhon.rain.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhon.rain.security.core.enums.LoginResponseTypeEnum;
import com.jhon.rain.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>功能描述</br> 登录成功的处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainAuthenticationSuccessHandler
 * @date 2017/10/19 21:35
 */
@Component("rainAuthenticationSuccessHandler")
@Slf4j
public class RainAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	                                    Authentication authentication)
					throws IOException, ServletException {

		log.info("登录成功");


		if (LoginResponseTypeEnum.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			/** 将信息打印到页面 **/
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
