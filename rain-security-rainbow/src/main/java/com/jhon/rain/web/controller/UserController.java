package com.jhon.rain.web.controller;

import com.jhon.rain.dto.User;
import com.jhon.rain.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * <p>功能描述</br> 用户类型的控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName UserController
 * @date 2017/10/15 16:54
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

//	@Autowired
//	private AppSignUpUtils appSignUpUtils;

	@Autowired
	private SecurityProperties securityProperties;


	@PostMapping("/register")
	public String register(User user, HttpServletRequest request) {
		String userId = user.getUsername();
		/** 将用户信息和社交信息进行绑定的操作 **/
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
		/** app端社交注册的接口 **/
		//appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
		return "redirect:/index.html";
	}

	/**
	 * 获取登录用户的认证信息
	 *
	 * @param user
	 * @return
	 */
	@GetMapping("/me")
	public Object getCurrentUserAuthentication(Authentication user, HttpServletRequest request)
					throws UnsupportedEncodingException {
		String header = request.getHeader("Authorization");
		String token = StringUtils.substringAfter(header, "bearer");
		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth().getJwtSignInKey().getBytes("UTF-8"))
						.parseClaimsJws(token).getBody();
		String company = (String) claims.get("company");
		log.info("compay:{}", company);
		return claims;
	}

	@GetMapping("/meDetail")
	public Object getCurrentUserDetails(@AuthenticationPrincipal
					                                    org.springframework.security.core.userdetails.User userDetails) {
		return userDetails;
	}

	@PostMapping
	@ApiOperation(value = "创建用户")
	public User create(@RequestBody User user) {

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}


	@PutMapping("/{id:\\d+}")
	public User update(@RequestBody User user, BindingResult errors) {
		return user;
	}

}
