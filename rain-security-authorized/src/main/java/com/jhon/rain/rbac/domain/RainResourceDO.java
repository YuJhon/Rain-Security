package com.jhon.rain.rbac.domain;

import com.jhon.rain.rbac.dto.RainResourceVO;
import com.jhon.rain.rbac.enums.RainResourceTypeEnum;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>功能描述</br> 资源对象 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainResourceDO
 * @date 2017/11/1 16:20
 */
@Data
@Entity
@Table(name = "t_rain_resource")
public class RainResourceDO {

	/**
	 * 数据库主键
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 资源名称：比如：xx菜单，xx按钮
	 */
	private String name;

	/**
	 * 资源链接
	 */
	private String link;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 序号
	 */
	private int sort;

	/**
	 * 资源类型
	 */
	@Enumerated(EnumType.STRING)
	private RainResourceTypeEnum type;

	/**
	 * 实际需要控制权限的url
	 */
	@ElementCollection
	@CollectionTable(name="t_rain_resource_url", joinColumns=@JoinColumn(name="resource_id"))
	@Column(name="url")
	private Set<String> urls;

	/**
	 * 父资源
	 */
	@ManyToOne
	private RainResourceDO parent;

	/**
	 * 子资源
	 */
	@OneToMany(mappedBy = "parent")
	@OrderBy("sort ASC")
	private List<RainResourceDO> childs = new ArrayList<>();

	/**
	 * 创建时间
	 */
	@Column(name = "created_time")
	private Date createdTime;

	/**
	 * <pre>转换成树形结构数据 </pre>
	 * @param user
	 * @return
	 */
	public RainResourceVO toTree(RainUserDO user) {
		RainResourceVO result = new RainResourceVO();
		BeanUtils.copyProperties(this, result);
		Set<Long> resourceIds = user.getAllResourceIds();

		List<RainResourceVO> children = new ArrayList<>();
		for (RainResourceDO child : getChilds()) {
			if (StringUtils.equals(user.getUsername(), "admin") ||
							resourceIds.contains(child.getId())) {
				children.add(child.toTree(user));
			}
		}
		result.setChildren(children);
		return result;
	}


	/**
	 * <pre>添加子资源</pre>
	 *
	 * @param child
	 */
	public void addChild(RainResourceDO child) {
		childs.add(child);
		child.setParent(this);
	}

}
