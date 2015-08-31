package beehive_web;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import beehive_web.base.Json;

@Aspect
@Component
@EnableAutoConfiguration
public class Aop {
    @Around("execution(public String controllers.WebBaseController.page*(..))")
    public String pageAspect(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        HttpSession session = (HttpSession) args[0];
        
        if(session == null || session.getAttribute("isLogin") == null) {
            return "login";
        }
        if((boolean)session.getAttribute("isLogin") == false) {
            return "login";
        }
        return (String) pjp.proceed(args);
    }
    
    @Around("execution(public String controllers.action.*.*(..))")
    public String actionAspect(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        HttpSession session = (HttpSession) args[0];
        if(session != null && session.getAttribute("isLogin") != null && (boolean)session.getAttribute("isLogin")) {
            return (String) pjp.proceed(args);
        }
        return Json.format(255, "请先登录");
    }
}
