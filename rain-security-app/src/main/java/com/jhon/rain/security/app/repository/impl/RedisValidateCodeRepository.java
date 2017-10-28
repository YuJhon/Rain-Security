package com.jhon.rain.security.app.repository.impl;

import com.jhon.rain.security.core.enums.ValidateCodeTypeEnum;
import com.jhon.rain.security.core.validate.code.base.BaseValidateCode;
import com.jhon.rain.security.core.validate.code.exception.ValidateCodeException;
import com.jhon.rain.security.core.validate.code.repository.ValidateCodeRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * <p>功能描述</br> App验证码的处理逻辑 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RedisValidateCodeRepository
 * @date 2017/10/28 17:14
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	private String deviceId;

	@Override
	public void save(ServletWebRequest request, BaseValidateCode code, ValidateCodeTypeEnum validateCodeType) {
		redisTemplate.opsForValue().set(buildKey(request, validateCodeType), code, 30, TimeUnit.MINUTES);
	}


	@Override
	public BaseValidateCode get(ServletWebRequest request, ValidateCodeTypeEnum validateCodeType) {
		Object value = redisTemplate.opsForValue().get(buildKey(request, validateCodeType));
		if (value == null) {
			return null;
		}
		return (BaseValidateCode) value;
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeTypeEnum validateCodeType) {
		redisTemplate.delete(buildKey(request, validateCodeType));
	}

	/**
	 * <pre>生成key的策略</pre>
	 *
	 * @param request
	 * @param validateCodeType 验证码类型
	 * @return
	 */
	private Object buildKey(ServletWebRequest request, ValidateCodeTypeEnum validateCodeType) {
		String deviceId = request.getHeader("deviceId");
		if (StringUtils.isBlank(deviceId)) {
			throw new ValidateCodeException("请在请求头中携带deviceId参数");
		}
		return "code:" + validateCodeType.toString().toLowerCase() + ":" + deviceId;
	}
}
