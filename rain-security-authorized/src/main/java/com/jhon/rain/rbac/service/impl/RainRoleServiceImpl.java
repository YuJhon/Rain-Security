package com.jhon.rain.rbac.service.impl;

import com.jhon.rain.rbac.domain.RainRoleDO;
import com.jhon.rain.rbac.domain.RainRoleResourceDO;
import com.jhon.rain.rbac.dto.RainRoleVO;
import com.jhon.rain.rbac.repository.RainResourceRepository;
import com.jhon.rain.rbac.repository.RainRoleRepository;
import com.jhon.rain.rbac.repository.RainRoleResourceRepository;
import com.jhon.rain.rbac.repository.support.QueryResultConverter;
import com.jhon.rain.rbac.service.RainRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>功能描述</br> 角色业务逻辑实现类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainRoleServiceImpl
 * @date 2017/11/2 21:43
 */
@Service
public class RainRoleServiceImpl implements RainRoleService {

	@Autowired
	private RainRoleRepository roleRepository;

	@Autowired
	private RainResourceRepository resourceRepository;

	@Autowired
	private RainRoleResourceRepository roleResourceRepository;

	@Override
	@Transactional(readOnly = false)
	public RainRoleVO create(RainRoleVO roleInfo) {
		RainRoleDO roleDO = new RainRoleDO();
		BeanUtils.copyProperties(roleInfo, roleDO);
		roleInfo.setId(roleRepository.save(roleDO).getId());
		return roleInfo;
	}

	@Override
	@Transactional(readOnly = false)
	public RainRoleVO update(RainRoleVO roleInfo) {
		RainRoleDO rainRoleDO = roleRepository.findOne(roleInfo.getId());
		BeanUtils.copyProperties(roleInfo, rainRoleDO);
		return roleInfo;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {
		RainRoleDO rainRoleDO = roleRepository.findOne(id);
		if (CollectionUtils.isNotEmpty(rainRoleDO.getUsers())) {
			throw new RuntimeException("不能删除有下挂用户的角色");
		}
		roleRepository.delete(id);
	}

	@Override
	public RainRoleVO getInfo(Long id) {
		RainRoleDO rainRoleDO = roleRepository.findOne(id);
		RainRoleVO roleInfo = new RainRoleVO();
		BeanUtils.copyProperties(rainRoleDO, roleInfo);
		return roleInfo;
	}

	@Override
	public List<RainRoleVO> findAll() {
		return QueryResultConverter.convert(roleRepository.findAll(), RainRoleVO.class);
	}

	@Override
	public String[] getRoleResources(Long id) {
		RainRoleDO roleDO = roleRepository.findOne(id);
		Set<String> resourceIds = new HashSet<>();
		for (RainRoleResourceDO resource : roleDO.getResources()) {
			resourceIds.add(resource.getResource().getId().toString());
		}
		return resourceIds.toArray(new String[resourceIds.size()]);
	}

	@Override
	@Transactional(readOnly = false)
	public void setRoleResources(Long roleId, String resourceIds) {
		resourceIds = StringUtils.removeEnd(resourceIds, ",");
		RainRoleDO role = roleRepository.findOne(roleId);
		roleResourceRepository.delete(role.getResources());
		String[] resourceIdArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(resourceIds, ",");
		for (String resourceId : resourceIdArray) {
			RainRoleResourceDO roleResource = new RainRoleResourceDO();
			roleResource.setRole(role);
			roleResource.setResource(resourceRepository.getOne(new Long(resourceId)));
			roleResourceRepository.save(roleResource);
		}

	}
}
