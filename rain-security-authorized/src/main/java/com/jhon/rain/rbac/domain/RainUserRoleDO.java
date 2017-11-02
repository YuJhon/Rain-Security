package com.jhon.rain.rbac.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>功能描述</br> 用户和角色的关系 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainUserRoleDO
 * @date 2017/11/1 16:21
 */
@Data
@Entity
@Table(name = "t_rain_user_role")
public class RainUserRoleDO {

	/**
	 * 数据库表主键
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
	 * 用户
	 */
	@ManyToOne
	private RainUserDO user;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name = "created_time")
	private Date createdTime;
}
