package com.jhon.rain.rbac.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <p>功能描述</br> 用户表 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainUserDO
 * @date 2017/11/1 15:42
 */
@Data
@Entity
@Table(name = "t_rain_user")
public class RainUserDO implements UserDetails {

	private static final long serialVersionUID = -6433971566820484589L;
	/**
	 * 数据库主键
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_time")
	private Date createdTime;

	/**
	 * 用户所拥有的角色
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<RainUserRoleDO> roles = new HashSet<>();

	/**
	 * 用户有权访问的所有url,不持久化到数据库
	 */
	@Transient
	private Set<String> urls = new HashSet<>();

	/**
	 * 用户所拥有的资源Id,不持久化到数据库
	 */
	@Transient
	private Set<Long> resourceIds = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * 获取所有资源Id
	 * @return
	 */
	public Set<Long> getAllResourceIds() {
		init(resourceIds);
		forEachResource(resource -> resourceIds.add(resource.getId()));
		return resourceIds;
	}

	/**
	 * 获取所有的资源
	 * @return
	 */
	public Set<String> getUrls(){
		init(urls);
		forEachResource(resource -> urls.addAll(resource.getUrls()));
		return urls;
	}

	/**
	 * <pre>初始化</pre>
	 * @param data 数据
	 */
	public void init(Set<?> data) {
		if (CollectionUtils.isEmpty(data)) {
			if (data == null) {
				data = new HashSet<>();
			}
		}
	}

	/**
	 * <pre>遍历每一个资源</pre>
	 * @param consumer
	 */
	private void forEachResource(Consumer<RainResourceDO> consumer){
		for (RainUserRoleDO role:roles) {
			for (RainRoleResourceDO resource:role.getRole().getResources()) {
				consumer.accept(resource.getResource());
			}
		}
	}
}
