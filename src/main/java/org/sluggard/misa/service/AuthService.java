x`package org.sluggard.misa.service;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiagezhiye.xcx.entity.Admin;
import com.jiagezhiye.xcx.util.HttpResult;

@Aspect
@Component
public class AuthService {
	
	
	@Autowired
	private HttpSession session;

	@Around(value = "@annotation(com.jiagezhiye.xcx.manage.auth.AuthRequired)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null) {
			return HttpResult.newResult(10405, "not login");
		}else {
			return joinPoint.proceed();
		}
	}
	
//    private Method getMethod(JoinPoint joinPoint) {
//        //获取参数的类型
//        Method method = null;
//        try {
//            Signature signature = joinPoint.getSignature();
//            MethodSignature msig = null;
//            if (!(signature instanceof MethodSignature)) {
//                throw new IllegalArgumentException("该注解只能用于方法");
//            }
//            msig = (MethodSignature) signature;
//            method = joinPoint.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes());
//        } catch (NoSuchMethodException e) {
//            log.error("annotation no sucheMehtod", e);
//        } catch (SecurityException e) {
//            log.error("annotation SecurityException", e);
//        }
//        return method;
//    }

}
