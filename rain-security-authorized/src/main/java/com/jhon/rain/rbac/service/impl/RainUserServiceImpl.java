package com.jhon.rain.rbac.service.impl;

import com.jhon.rain.rbac.domain.RainUserDO;
import com.jhon.rain.rbac.domain.RainUserRoleDO;
import com.jhon.rain.rbac.dto.RainUserCondition;
import com.jhon.rain.rbac.dto.RainUserVO;
import com.jhon.rain.rbac.repository.RainRoleRepository;
import com.jhon.rain.rbac.repository.RainUserRepository;
import com.jhon.rain.rbac.repository.RainUserRoleRepository;
import com.jhon.rain.rbac.repository.spec.RainUserSpec;
import com.jhon.rain.rbac.repository.support.QueryResultConverter;
import com.jhon.rain.rbac.service.RainUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>功能描述</br> 用户接口实现类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainUserServiceImpl
 * @date 2017/11/2 21:44
 */
@Service
public class RainUserServiceImpl implements RainUserService {

	@Autowired
	private RainUserRepository userRepository;

	@Autowired
	private RainRoleRepository roleRepository;

	@Autowired
	private RainUserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = false)
	public RainUserVO create(RainUserVO userInfo) {
		RainUserDO userDO = new RainUserDO();
		BeanUtils.copyProperties(userInfo, userDO);
		userDO.setPassword(passwordEncoder.encode("123456"));
		userInfo.setId(userRepository.save(userDO).getId());

		/** 创建和角色关联关系 **/
		createUserRole(userInfo, userDO);
		return userInfo;
	}

	@Override
	@Transactional(readOnly = false)
	public RainUserVO update(RainUserVO userInfo) {
		RainUserDO userDO = userRepository.findOne(userInfo.getId());
		BeanUtils.copyProperties(userInfo, userDO);
		userRepository.save(userDO);
		/** 创建和角色关联关系 **/
		createUserRole(userInfo, userDO);
		return userInfo;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {

	}

	/**
	 * <pre>创建用户的角色关系</pre>
	 *
	 * @param userInfo
	 * @param userDO
	 */
	private void createUserRole(RainUserVO userInfo, RainUserDO userDO) {
		/** 如果存在，就先删除，然后创建 **/
		if (CollectionUtils.isNotEmpty(userDO.getRoles())) {
			userRoleRepository.delete(userDO.getRoles());
		}
		RainUserRoleDO userRoleDO = new RainUserRoleDO();
		userRoleDO.setRole(roleRepository.getOne(userInfo.getRoleId()));
		userRoleDO.setUser(userDO);
		userRoleRepository.save(userRoleDO);
	}

	@Override
	public RainUserVO getInfo(Long id) {
		RainUserDO userDO = userRepository.findOne(id);
		RainUserVO userInfo = new RainUserVO();
		BeanUtils.copyProperties(userDO, userInfo);
		return userInfo;
	}

	@Override
	public Page<RainUserVO> query(RainUserCondition condition, Pageable pageable) {
		Page<RainUserDO> users = userRepository.findAll(new RainUserSpec(condition), pageable);
		return QueryResultConverter.convert(users, RainUserVO.class, pageable);
	}
}
