package com.gafis.xj.aspect;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.gafis.xj.model.LogInfo;


@Aspect
@Component
public class LogAspect {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Pointcut("execution(public * com.gafis.xj.controller.*.list*(..)) && @annotation(LogAnnotation)")
	public void listAspect() {  
        
    } 
	@Pointcut("execution(public * com.gafis.xj.controller.*.*Enable(..)) && @annotation(LogAnnotation)")
	public void enableAspect(){
		
	}
	
	@Around("listAspect()")
	public Object doListLog(ProceedingJoinPoint pjp){
		Object returnObj = null;
		System.out.println(""+pjp.getSignature().getName());
		System.out.println("-----list日志----");
		try {
			returnObj = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@Around("enableAspect()&&args(pkId)")  
	public Object doEnableLog(ProceedingJoinPoint pjp,String pkId){
		Object returnObj = null;
		try {
			Object obj = pjp.getArgs()[0];
			System.out.println(pkId);
			System.out.println(""+pjp.getSignature().getName());
			LogInfo info=new LogInfo();
			info.setPkId(pkId);
			info.setDate(new Date());
			mongoTemplate.insert(info);
			returnObj = pjp.proceed();
			LogInfo info1=new LogInfo();
			Query query=new Query(Criteria.where("pkId").is("373C99BAF5304F66A15B56F1CB774FBD"));
			info1=mongoTemplate.findOne(query, LogInfo.class);
			System.out.println(info1.getDate());
			List<LogInfo> list=mongoTemplate.findAll(LogInfo.class);
			System.out.println(list.size());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	/*@Before("enableAspect()&&args(pkId)")
	public void doEnableLog(String pkId){
		System.out.println("----in enable----"+pkId);
	}*/
	
}
