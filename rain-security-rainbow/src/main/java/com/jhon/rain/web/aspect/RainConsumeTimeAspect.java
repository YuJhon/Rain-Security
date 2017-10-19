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
/*@Aspect
@Component*/
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
