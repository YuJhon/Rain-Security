package com.jhon.rain.rbac.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>功能描述</br> 用户和资源表</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainRoleResourceDO
 * @date 2017/11/1 16:21
 */
@Data
@Entity
@Table(name = "t_rain_role_resource")
public class RainRoleResourceDO {

	/**
	 * 数据库主键
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 角色
	 */
	@ManyToOne
	private RainRoleDO role;

	/**
	 * 资源
	 */
	@ManyToOne
	private RainResourceDO resource;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column
	private Date createdTime;
}
