#### RESTful API 的拦截

##### 方式一 Filter：
* Filter代码示例
```java
package com.jhon.rain.web.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * <p>功能描述</br> 接口消耗时间过滤器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainConsumeTimeFilter
 * @date 2017/10/17 11:34
 */
public class RainConsumeTimeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init time filter");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		long start = new Date().getTime();
		filterChain.doFilter(servletRequest, servletResponse);
		System.out.println("Time Filter 耗时：" + (new Date().getTime() - start));
	}

	@Override
	public void destroy() {
		System.out.println("destory time filter");
	}
}

```

* Filter的配置
```java
package com.jhon.rain.web.config;

import com.jhon.rain.web.filter.RainConsumeTimeFilter;
import com.jhon.rain.web.interceptor.RainConsumeTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> web配置</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WebConfig
 * @date 2017/10/17 11:37
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	
	@Bean
	public FilterRegistrationBean timeFilter() {

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		RainConsumeTimeFilter timeFilter = new RainConsumeTimeFilter();
		registrationBean.setFilter(timeFilter);

		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);

		return registrationBean;
	}


}
```

##### 方式二 Interceptor：
* 示例代码
```java
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
```
* 配置代码
```java
package com.jhon.rain.web.config;

import com.jhon.rain.web.filter.RainConsumeTimeFilter;
import com.jhon.rain.web.interceptor.RainConsumeTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br> web配置</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WebConfig
 * @date 2017/10/17 11:37
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


	@Autowired
	private RainConsumeTimeInterceptor timeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}
	
	/*
	@Bean
	public FilterRegistrationBean timeFilter() {

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		RainConsumeTimeFilter timeFilter = new RainConsumeTimeFilter();
		registrationBean.setFilter(timeFilter);

		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);

		return registrationBean;
	}
	*/

}
```

##### 方式三 ControllerAdvice：
*代码示例
```java
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
```

##### 方式四 Aspect:
```java
package com.jhon.rain.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>功能描述</br> 消耗时间的切面:能够获取到参数的值 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainConsumeTimeAspect
 * @date 2017/10/18 9:02
 */
@Aspect
@Component
public class RainConsumeTimeAspect {

	@Around("execution(* com.jhon.rain.web.controller.RestExampleController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("Time Aspect Start!");

		Object[] args = pjp.getArgs();

		for (Object obj : args) {
			System.out.println("Arg Is " + obj);
		}

		long startTime = new Date().getTime();

		Object obj = pjp.proceed();

		System.out.println("Time Aspect Statistics Consume Time : " + (new Date().getTime() - startTime));

		System.out.println("Time Aspect End!");

		return obj;
	}
}
```

##### 四种拦截方式的执行顺序

![Restful API 的拦截方式执行顺序](../photos/RestFul%20API%20拦截顺序.png)