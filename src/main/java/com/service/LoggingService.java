package com.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;



@Aspect
@Service
public class LoggingService {

	Logger log= Logger.getLogger(LoggingService.class);
	
	@Around("execution(* com.controller.ProductController.*(..))")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		
		log.info("Entering method " + pjp.getSignature());
		Object o = pjp.proceed();
		log.info("Exiting method " + pjp.getSignature());
		return o;
	}
	
}
