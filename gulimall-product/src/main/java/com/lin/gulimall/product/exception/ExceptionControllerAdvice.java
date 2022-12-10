package com.lin.gulimall.product.exception;

import com.lin.common.exception.BizCodeEnum;
import com.lin.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "com.lin.gulimall.product.controller")
public class ExceptionControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题: {}，异常类型：{}",e.getMessage(),e.getClass());
       BindingResult bindingResult= e.getBindingResult();
       Map<String,String> map=new HashMap<>();
       bindingResult.getFieldErrors().forEach(item->{
           String fieldName= item.getField();
           String fieldError=item.getDefaultMessage();
           map.put(fieldName,fieldError);
       });
       return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(), BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data",map);
    }
//    @ExceptionHandler(value=Throwable.class)
//    public R handleException(Throwable e){
//        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
//    }
}
