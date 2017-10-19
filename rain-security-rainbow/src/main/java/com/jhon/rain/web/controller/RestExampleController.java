package com.jhon.rain.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jhon.rain.dto.User;
import com.jhon.rain.dto.UserQueryCondition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>功能描述</br> Restful API 模式的控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RestExampleController
 * @date 2017/10/16 14:18
 */
@RestController
public class RestExampleController {

	@GetMapping("/rest")
	@JsonView(User.UserSimpleView.class)
	public List<User> list(@RequestParam(name = "username", required = false) String username) {
		System.out.println("username=" + username);
		List<User> users = new ArrayList<>();
		users.add(new User("Jhon1", "123456", new Date()));
		users.add(new User("Jhon2", "123456", new Date()));
		users.add(new User("Jhon3", "123456", new Date()));
		return users;
	}

	@GetMapping("/rest2")
	public List<User> list2(UserQueryCondition condition, @PageableDefault(size = 10, page = 1, sort = "age,desc") Pageable pageable) {
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getSort());
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	@GetMapping("rest/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	@ApiOperation(value = "用户查询服务")
	public User getUserInfo(@ApiParam("用户id") @PathVariable(name = "id") String id) {
		User user = new User();
		user.setUsername("Rain");
		user.setPassword("1231231");
		user.setBirthday(new Date());
		return user;
	}

	@PostMapping("/rest")
	public User create(@Valid @RequestBody User user) {
		System.out.println("" + user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@PutMapping("rest/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println(user.getBirthday());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());

		user.setId("2");
		return user;
	}

	@DeleteMapping("rest/{id:\\d+}")
	public void delete(@PathVariable(name = "id") String id) {
		System.out.println("id=" + id);
	}

}
