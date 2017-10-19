package com.jhon.rain.security.browser.support;

import lombok.Data;

/**
 * <p>功能描述</br> 用户信息 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SocialUserInfo
 * @date 2017/10/19 11:55
 */
@Data
public class SocialUserInfo {

	private String providerId;

	private String providerUserId;

	private String nickname;

	private String heading;

}
