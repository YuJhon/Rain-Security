package com.jhon.rain.web.controller;

import com.jhon.rain.dto.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

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
public class UserController {

	/*
	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@PostMapping("/register")
	public void register(User user, HttpServletRequest request) {
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
	}
	*/

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
