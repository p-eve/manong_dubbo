package me.manong.app.config;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import me.manong.app.utils.NetUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogAsp {

    private static final Logger logger = LoggerFactory.getLogger(LogAsp.class);

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 开始时间
        LocalDateTime beginTime = LocalDateTime.now();
        // 执行方法
        Object result = point.proceed();
        // 结束时间
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(beginTime, endTime);
        // 操作时长
        long millis = duration.toMillis();
        // 保存日志
        saveLog(point,millis);
        return result;
    }

    private void  saveLog(ProceedingJoinPoint point, long millis){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        // 获得注解
        ApiOperation ap = method.getAnnotation(ApiOperation.class);
        if (ap!=null) {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

            HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
            String username=null;
            if(session.getAttribute("username")!=null)
            {
                username=session.getAttribute("username").toString();
            }

            String value = ap.value();

            String ip= NetUtils.getClientIpAddress(request);

            String url=request.getRequestURI();

            String params=new Gson().toJson(request.getParameterMap());
            //String params=Arrays.stream(point.getArgs()).map(item->item.toString()).collect(Collectors.joining("，"));
            logger.info("获得操作内容: {} ,操作人：{} ,请求url:{} ,操作参数：{} ,操作IP：{} ,耗时：{}毫秒", value,username,url,params,ip,millis);

        }
    }
}
