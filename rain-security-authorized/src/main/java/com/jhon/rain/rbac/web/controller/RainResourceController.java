package com.jhon.rain.rbac.web.controller;

import com.jhon.rain.rbac.domain.RainUserDO;
import com.jhon.rain.rbac.dto.RainResourceVO;
import com.jhon.rain.rbac.service.RainResourceService;
import com.jhon.rain.rbac.web.controller.support.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * <p>功能描述</br> 资源控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainResourceController
 * @date 2017/11/4 13:20
 */
@RestController
@RequestMapping("/resource")
public class RainResourceController {

	@Autowired
	private RainResourceService resourceService;

	/**
	 * <pre>获取资源树</pre>
	 *
	 * @param user
	 * @return
	 */
	@GetMapping
	public RainResourceVO getTree(@AuthenticationPrincipal RainUserDO user) {
		return resourceService.getTree(user.getId());
	}

	/**
	 * <pre>通过id获取资源</pre>
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public RainResourceVO getInfo(@PathVariable Long id) {
		return resourceService.getInfo(id);
	}

	/**
	 * <pre>创建资源</pre>
	 *
	 * @param resourceInfo
	 * @return
	 */
	@PostMapping
	public RainResourceVO create(@RequestBody RainResourceVO resourceInfo) {
		return resourceService.create(resourceInfo);
	}

	/**
	 * <pre>更新资源</pre>
	 *
	 * @param resourceInfo
	 * @return
	 */
	@PutMapping("/{id}")
	public RainResourceVO update(@RequestBody RainResourceVO resourceInfo) {
		return resourceService.update(resourceInfo);
	}

	/**
	 * <pre>删除资源</pre>
	 *
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		resourceService.delete(id);
	}

	/**
	 * <pre>资源上调</pre>
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("/{id}/up")
	public SimpleResponse moveUp(@PathVariable Long id) {
		return new SimpleResponse(resourceService.move(id, true));
	}

	/**
	 * <pre>资源下调</pre>
	 *
	 * @param id
	 * @return
	 */
	@PostMapping("/{id}/down")
	public SimpleResponse moveDown(@PathVariable Long id) {
		return new SimpleResponse(resourceService.move(id, false));
	}
}
