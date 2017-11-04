package com.jhon.rain.rbac.web.controller;

import com.jhon.rain.rbac.dto.RainUserCondition;
import com.jhon.rain.rbac.dto.RainUserVO;
import com.jhon.rain.rbac.service.RainUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * <p>功能描述</br> 用户处理的控制器</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainUserController
 * @date 2017/11/4 13:19
 */
@RestController
@RequestMapping("/admin")
public class RainUserController {

	@Autowired
	private RainUserService userService;

	/**
	 * <pre>获取当前的用户信息</pre>
	 *
	 * @param user
	 * @return
	 */
	@GetMapping("/me")
	public RainUserVO me(@AuthenticationPrincipal UserDetails user) {
		RainUserVO userInfo = new RainUserVO();
		userInfo.setUsername(user.getUsername());
		return userInfo;
	}

	/**
	 * <pre>创建用户</pre>
	 *
	 * @param userInfo 用户信息
	 * @return
	 */
	@PostMapping
	public RainUserVO create(@RequestBody RainUserVO userInfo) {
		return userService.create(userInfo);
	}

	/**
	 * <pre>更新用户</pre>
	 *
	 * @param userInfo 用户信息
	 * @return
	 */
	@PutMapping("/{id}")
	public RainUserVO update(@RequestBody RainUserVO userInfo) {
		return userService.update(userInfo);
	}

	/**
	 * <pre>删除用户</pre>
	 *
	 * @param id 用户id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	/**
	 * <pre>获取用户信息</pre>
	 *
	 * @param id 用户id
	 * @return
	 */
	@GetMapping("/{id}")
	public RainUserVO getInfo(@PathVariable Long id) {
		return userService.getInfo(id);
	}

	/**
	 * <pre>分页查询记录</pre>
	 *
	 * @param condition
	 * @param pageable  分页对象
	 * @return
	 */
	@GetMapping
	public Page<RainUserVO> query(RainUserCondition condition, Pageable pageable) {
		return userService.query(condition, pageable);
	}
}
