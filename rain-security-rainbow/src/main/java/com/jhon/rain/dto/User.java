package com.jhon.rain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.jhon.rain.validator.RainValidator;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>功能描述</br> 用户信息 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName User
 * @date 2017/10/15 16:58
 */
//@Data
public class User implements Serializable{

	public interface UserSimpleView {};

	public interface UserDetailView extends UserSimpleView {};

	private String id;

	/**
	 * 用户名称
	 */
	@RainValidator(message = "用户名自定义测试")
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	private String password;

	/**
	 * 生日
	 */
	@Past(message = "生日必须是过去的时间")
	private Date birthday;

	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public User(){}

	public User(String username, String password, Date birthday) {
		this.username = username;
		this.password = password;
		this.birthday = birthday;
	}
}
