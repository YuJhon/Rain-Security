package com.jhon.rain.security.core.social.qq.connect;

import com.jhon.rain.security.core.social.qq.api.QQ;
import com.jhon.rain.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * <p>功能描述</br> QQ Adapter </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName QQAdapter
 * @date 2017/10/25 14:56
 */
public class QQAdapter implements ApiAdapter<QQ> {

	@Override
	public boolean test(QQ api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues connectionValues) {

		QQUserInfo userInfo = api.getUserInfo();

		connectionValues.setDisplayName(userInfo.getNickname());
		connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
		connectionValues.setProfileUrl(null);
		connectionValues.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		/** do nothing **/
	}
}
