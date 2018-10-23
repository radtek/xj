package com.gafis.xj.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
	
	@Pointcut("execution(public * com.gafis.xj.controller.*.*index(..)) && @annotation(TestAnnotation)&& args(str1,str2)")//
	public void testAspect(String str1,String str2) {  
        
    } 
	
	 
	
	@Pointcut("execution(public * com.gafis.xj.controller.*.*Enable(..))")
	public void enableAspect(){
		
	}
	
	/*@AfterReturning("testAspect()")  
	public Object testLog(JoinPoint pj){
		Object returnObj = null;
		System.out.println(""+pj.getSignature().getName());
		System.out.println("-----list日志----");
		try {
			returnObj = pj.getArgs();
			System.out.println(returnObj);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return returnObj;
	}*/

	
	/*@AfterReturning("execution(public * com.gafis.xj.controller.MainController.*(..)) && @annotation(TestAnnotation)")  
	public Object testLog(JoinPoint pj){
		Object returnObj = null;
		System.out.println(""+pj.getSignature().getName());
		System.out.println("-----list日志----");
		try {
			returnObj = pj.getArgs();
			System.out.println(returnObj);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return returnObj;
	}*/
	
	
	/*@After("execution(public * com.gafis.xj.controller.MainController.*(..)) && @annotation(TestAnnotation)")  
	public Object testLog(JoinPoint pj){
		Object returnObj = null;
		System.out.println(""+pj.getSignature().getName());
		System.out.println("-----日志----");
		try {
			returnObj = pj.getArgs();
			System.out.println(returnObj);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return returnObj;
	}*/
	
	/*@Before("execution(public * com.gafis.xj.controller.MainController.*(..)) && @annotation(TestAnnotation)")  
	public Object testLog(JoinPoint pj){
		Object returnObj = null;
		System.out.println(""+pj.getSignature().getName());
		System.out.println("-----日志----");
		try {
			returnObj = pj.getArgs();
			System.out.println(returnObj);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return returnObj;
	}*/
	
	@AfterReturning( value="testAspect(str1,str2)"  )  
	public Object testLog(JoinPoint joinPoint,String str1,String str2 ){
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(""+joinPoint.getSignature().getName());
		System.out.println("-----日志----");
		Object returnObj=null;
		try {
			returnObj = joinPoint.getArgs();
			System.out.println(returnObj);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	
	 
	
	
	
}
