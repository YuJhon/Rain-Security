package com.jhon.rain.handler;

import com.jhon.rain.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>功能描述</br> 异常拦截处理</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ExceptionHandler
 * @date 2017/10/16 23:02
 */
@ControllerAdvice
public class RainbowExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handlerUserNotExistException(UserNotExistException ex) {
		Map<String, Object> respMap = new HashMap<>();
		respMap.put("id", ex.getId());
		respMap.put("msg", ex.getMsg());
		respMap.put("detailMsg", ex.getMessage());
		return respMap;
	}
}
