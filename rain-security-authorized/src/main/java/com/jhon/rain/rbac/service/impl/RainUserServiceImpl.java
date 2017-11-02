package com.jhon.rain.rbac.service.impl;

import com.jhon.rain.rbac.dto.RainUserCondition;
import com.jhon.rain.rbac.dto.RainUserVO;
import com.jhon.rain.rbac.repository.RainUserRepository;
import com.jhon.rain.rbac.service.RainUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	@Transactional(readOnly = false)
	public RainUserVO create(RainUserVO userInfo) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public RainUserVO update(RainUserVO userInfo) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long id) {

	}

	@Override
	public RainUserVO getInfo(Long id) {
		return null;
	}

	@Override
	public Page<RainUserVO> query(RainUserCondition condition, Pageable pageable) {
		return null;
	}
}
