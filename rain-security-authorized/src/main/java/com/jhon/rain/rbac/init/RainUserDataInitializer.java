package com.jhon.rain.rbac.init;

import com.jhon.rain.rbac.domain.RainResourceDO;
import com.jhon.rain.rbac.domain.RainRoleDO;
import com.jhon.rain.rbac.domain.RainUserDO;
import com.jhon.rain.rbac.domain.RainUserRoleDO;
import com.jhon.rain.rbac.enums.RainResourceTypeEnum;
import com.jhon.rain.rbac.repository.RainResourceRepository;
import com.jhon.rain.rbac.repository.RainRoleRepository;
import com.jhon.rain.rbac.repository.RainUserRepository;
import com.jhon.rain.rbac.repository.RainUserRoleRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>功能描述</br> TODO</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainUserDataInitializer
 * @date 2017/11/4 15:00
 */
@Component
public class RainUserDataInitializer extends AbstractDataInitializer {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RainRoleRepository roleRepository;

	@Autowired
	private RainUserRepository userRepository;

	@Autowired
	private RainUserRoleRepository userRoleRepository;

	@Autowired
	private RainResourceRepository resourceRepository;


	@Override
	public Integer getIndex() {
		return Integer.MIN_VALUE;
	}

	@Override
	protected void doInit() throws Exception {
		initResource();
		RainRoleDO role = initRole();
		initAdminUser(role);
	}

	/**
	 * <pre>用户的初始化，并且和角色关联 </pre>
	 *
	 * @param role
	 */
	protected void initAdminUser(RainRoleDO role) {
		RainUserDO user = new RainUserDO();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("123456"));
		userRepository.save(user);

		RainUserRoleDO userRole = new RainUserRoleDO();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleRepository.save(userRole);
	}

	/**
	 * <pre>初始化角色</pre>
	 *
	 * @return
	 */
	protected RainRoleDO initRole() {
		RainRoleDO roleDO = new RainRoleDO();
		roleDO.setName("超级管理员");
		roleRepository.save(roleDO);
		return roleDO;
	}

	/**
	 * <pre>初始化资源</pre>
	 */
	protected void initResource() {
		RainResourceDO root = createRoot("根节点");
		createResource("首页", "", "home", root);
		RainResourceDO menu1 = createResource("平台管理", "", "desktop", root);
		createResource("角色管理", "role", "", menu1);
		createResource("管理员管理", "admin", "", menu1);
	}

	/**
	 * <pre>初始化跟资源 </pre>
	 *
	 * @param name 资源的名称
	 * @return
	 */
	protected RainResourceDO createRoot(String name) {
		RainResourceDO node = new RainResourceDO();
		node.setName(name);
		resourceRepository.save(node);
		return node;
	}

	/**
	 * <pre>创建资源</pre>
	 *
	 * @param name
	 * @param link
	 * @param iconName
	 * @param parent
	 * @return
	 */
	protected RainResourceDO createResource(String name, String link, String iconName, RainResourceDO parent) {
		RainResourceDO node = new RainResourceDO();
		node.setName(name);
		node.setIcon(iconName);
		node.setParent(parent);
		node.setType(RainResourceTypeEnum.MENU);
		if (StringUtils.isNotBlank(link)) {
			node.setLink(link + "Manager");
			Set<String> urls = new HashSet<>();
			urls.add(link + "Manager");
			urls.add("/" + link + "/*");
			node.setUrls(urls);
		}
		resourceRepository.save(node);
		return node;
	}

	@Override
	protected boolean isNeedInit() {
		return userRepository.count() == 0;
	}
}
