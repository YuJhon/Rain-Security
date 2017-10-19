package com.jhon.rain.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>功能描述</br> 拦截器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainConsumeTimeInterceptor
 * @date 2017/10/17 16:25
 */
@Component
public class RainConsumeTimeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
		System.out.println("\nPreHandle");

		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());

		httpServletRequest.setAttribute("startTime", new Date().getTime());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("\nPostHandle");
		Long start = (Long) httpServletRequest.getAttribute("startTime");
		System.out.println("Rain Interceptor 耗时:"+ (new Date().getTime() - start));
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) throws Exception {
		System.out.println("\nAfterCompletion");
		Long start = (Long) httpServletRequest.getAttribute("startTime");
		System.out.println("Rain Interceptor 耗时:"+ (new Date().getTime() - start));
		System.out.println("Ex Is "+ ex);
	}
}
