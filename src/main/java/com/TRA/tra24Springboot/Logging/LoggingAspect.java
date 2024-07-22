package com.TRA.tra24Springboot.Logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut(value = "execution(* com.tra.school.Controllers.InventoryController.*(..)")
    public void pointCutDefinitionSchool(){}

    @Before(value = "pointCutDefinitionSchool")
    public void logBefore(ProceedingJoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "pointCutDefinitionSchool", returning = "result")
    public void logAfterReturning(ProceedingJoinPoint joinPoint, Object result) {
        System.out.println("After method: " + joinPoint.getSignature().getName() + ", Result: " + result);
    }

    @Around(value = "pointCutDefinitionSchool")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        logger.info("method invoked " + className + " : " + methodName + "()" + "arguments : "
                + mapper.writeValueAsString(array));
        Object object = pjp.proceed();
        logger.info(className + " : " + methodName + "()" + "Response : "
                + mapper.writeValueAsString(object));
        return object;
    }


}