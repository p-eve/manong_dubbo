package me.manong.app.config;

import me.manong.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class BaseExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        logger.error(String.valueOf(e.getLocalizedMessage()));

        if (e instanceof BindException){
            BindException ex = (BindException) e;
            return  Result.error("参数校验异常："+ex.getBindingResult().getAllErrors().stream().map(item->item.getDefaultMessage()).collect(Collectors.joining("，")));
        }
        return Result.error(e.getMessage());
    }

}
