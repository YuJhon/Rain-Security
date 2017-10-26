package com.jhon.rain.security.core.social.weixin.connect;

import com.jhon.rain.security.core.social.weixin.api.Weixin;
import com.jhon.rain.security.core.social.weixin.api.WeixinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * <p>功能描述</br> 接口适配数据的转换 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WeixinAdapter
 * @date 2017/10/26 11:02
 */
public class WeixinAdapter implements ApiAdapter<Weixin> {

	private String openId;

	public WeixinAdapter(){}

	public WeixinAdapter(String openId){
		this.openId = openId;
	}

	@Override
	public boolean test(Weixin weixin) {
		return true;
	}

	@Override
	public void setConnectionValues(Weixin api, ConnectionValues values) {
		WeixinUserInfo profile = api.getUserInfo(openId);
		values.setProviderUserId(profile.getOpenid());
		values.setDisplayName(profile.getNickname());
		values.setImageUrl(profile.getHeadimgurl());
	}

	@Override
	public UserProfile fetchUserProfile(Weixin api) {
		return null;
	}

	@Override
	public void updateStatus(Weixin api, String message) {

	}
}
