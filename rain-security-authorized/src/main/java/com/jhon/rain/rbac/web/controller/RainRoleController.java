package com.jhon.rain.rbac.web.controller;

import com.jhon.rain.rbac.dto.RainRoleVO;
import com.jhon.rain.rbac.service.RainRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>功能描述</br> 角色控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainRoleController
 * @date 2017/11/4 13:20
 */
@RestController
@RequestMapping("/role")
public class RainRoleController {

	@Autowired
	private RainRoleService roleService;

	/**
	 * <pre>创建角色</pre>
	 *
	 * @param roleInfo
	 * @return
	 */
	@PostMapping
	public RainRoleVO create(@RequestBody RainRoleVO roleInfo) {
		return roleService.create(roleInfo);
	}

	/**
	 * <pre>更新角色</pre>
	 *
	 * @param roleInfo
	 * @return
	 */
	@PutMapping("/{id}")
	public RainRoleVO update(@RequestBody RainRoleVO roleInfo) {
		return roleService.update(roleInfo);
	}

	/**
	 * <pre>删除角色</pre>
	 *
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		roleService.delete(id);
	}

	/**
	 * <pre>获取指定的角色信息</pre>
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public RainRoleVO getInfo(@PathVariable Long id) {
		return roleService.getInfo(id);
	}

	/**
	 * <pre>查询所有的角色</pre>
	 *
	 * @return
	 */
	@GetMapping
	public List<RainRoleVO> findAll() {
		return roleService.findAll();
	}

	/**
	 * <pre>获取角色对应的资源</pre>
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/resource")
	public String[] getRoleResources(@PathVariable Long id) {
		return roleService.getRoleResources(id);
	}

	/**
	 * <pre>给角色创建资源</pre>
	 *
	 * @param id
	 * @param ids
	 */
	@PostMapping("/{id}/resource")
	public void createRoleResource(@PathVariable Long id, String ids) {
		roleService.setRoleResources(id, ids);
	}
}
