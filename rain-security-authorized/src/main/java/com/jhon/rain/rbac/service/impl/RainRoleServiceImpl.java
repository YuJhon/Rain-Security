package com.jhon.rain.rbac.service.impl;

import com.jhon.rain.rbac.dto.RainRoleVO;
import com.jhon.rain.rbac.repository.RainRoleRepository;
import com.jhon.rain.rbac.service.RainRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	@Override
	@Transactional(readOnly = false)
	public RainRoleVO create(RainRoleVO roleInfo) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public RainRoleVO update(RainRoleVO roleInfo) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {

	}

	@Override
	public RainRoleVO getInfo(Long id) {
		return null;
	}

	@Override
	public List<RainRoleVO> findAll() {
		return null;
	}

	@Override
	public String[] getRoleResoures(Long id) {
		return new String[0];
	}

	@Override
	@Transactional(readOnly = false)
	public void setRoleResources(Long id, String ids) {

	}
}
