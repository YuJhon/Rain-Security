package com.jhon.rain.rbac.service.impl;

import com.jhon.rain.rbac.domain.RainResourceDO;
import com.jhon.rain.rbac.domain.RainUserDO;
import com.jhon.rain.rbac.dto.RainResourceVO;
import com.jhon.rain.rbac.repository.RainResourceRepository;
import com.jhon.rain.rbac.repository.RainUserRepository;
import com.jhon.rain.rbac.service.RainResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> 资源业务逻辑实现类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainResourceServiceImpl
 * @date 2017/11/2 21:43
 */
@Service
public class RainResourceServiceImpl implements RainResourceService {

	@Autowired
	private RainResourceRepository resourceRepository;

	@Autowired
	private RainUserRepository userRepository;


	@Override
	public RainResourceVO getTree(Long userId) {
		RainUserDO user = userRepository.findOne(userId);
		return resourceRepository.findByName("根节点").toTree(user);
	}

	@Override
	public RainResourceVO getInfo(Long id) {
		RainResourceDO resourceDO = resourceRepository.findOne(id);
		RainResourceVO resourceVO = new RainResourceVO();
		BeanUtils.copyProperties(resourceDO, resourceVO);
		return resourceVO;
	}

	@Override
	@Transactional(readOnly = false)
	public RainResourceVO create(RainResourceVO resourceInfo) {
		RainResourceDO parent = resourceRepository.findOne(resourceInfo.getParentId());
		if (parent == null) {
			parent = resourceRepository.findByName("根节点");
		}
		RainResourceDO resourceDO = new RainResourceDO();
		BeanUtils.copyProperties(resourceInfo, resourceDO);
		parent.addChild(resourceDO);
		resourceInfo.setId(resourceRepository.save(resourceDO).getId());
		return resourceInfo;
	}

	@Override
	@Transactional(readOnly = false)
	public RainResourceVO update(RainResourceVO resourceInfo) {
		RainResourceDO resourceDO = resourceRepository.findOne(resourceInfo.getId());
		BeanUtils.copyProperties(resourceInfo, resourceDO);
		return resourceInfo;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		resourceRepository.delete(id);
	}

	@Override
	public Long move(Long id, boolean up) {
		RainResourceDO resourceDO = resourceRepository.findOne(id);
		int index = resourceDO.getSort();
		List<RainResourceDO> childs = new ArrayList<>();
		for (int i = 0; i < childs.size(); i++) {
			RainResourceDO current = childs.get(i);
			if (current.getId().equals(id)) {
				if (up) {
					if (i != 0) {
						RainResourceDO pre = childs.get(i - 1);
						resourceDO.setSort(pre.getSort());
						pre.setSort(index);
						resourceRepository.save(pre);
					}
				} else {
					if (i != childs.size()) {
						RainResourceDO next = childs.get(i + 1);
						resourceDO.setSort(next.getSort());
						next.setSort(index);
						resourceRepository.save(next);
					}
				}
			}
		}
		resourceRepository.save(resourceDO);
		return resourceDO.getParent().getId();
	}
}
