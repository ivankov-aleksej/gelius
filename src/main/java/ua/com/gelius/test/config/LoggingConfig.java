package ua.com.gelius.test.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * ua.com.gelius.test.controller.ModelRestController.*(..)) ")
    public void callModelRestController() {
        //NOP
    }

    @Before("callModelRestController()")
    public void beforeCallRestaurantControllerMethod(JoinPoint jp) {
        logging(jp);
    }

    private void logging(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        logger.info("{}, args=[{}]", jp, args);
    }

}
