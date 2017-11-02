package com.jhon.rain.rbac.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>功能描述</br> 角色对象 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainRoleDO
 * @date 2017/11/1 16:19
 */
@Data
@Entity
@Table(name = "t_rain_role")
public class RainRoleDO {

	/**
	 * 数据库主键
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 角色名称
	 */
	@Column(length = 20, nullable = false)
	private String name;

	/**
	 * 角色拥有权限的资源集合
	 */
	@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
	private Set<RainRoleResourceDO> resources = new HashSet<>();

	/**
	 * 角色的用户集合
	 */
	@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
	private Set<RainUserRoleDO> users = new HashSet<>();

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date createdTime;

}
