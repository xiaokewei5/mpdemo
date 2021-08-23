package com.example.mpdemo.handler;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JSONArray> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException==>{}",e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        return getJsonArrayResponseEntity(bindingResult);
    }

    private ResponseEntity<JSONArray> getJsonArrayResponseEntity(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        JSONArray array = new JSONArray();
        JSONObject json;
        for (FieldError error : fieldErrors) {
            json = new JSONObject();
            json.set("filed", error.getField());
            json.set("error", error.getDefaultMessage());
            array.add(json);
        }
        log.error("参数错误：" + JSONUtil.parse(array).toStringPretty());
        return ResponseEntity.badRequest().body(array);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<JSONArray> handleBindException(BindException e) {
        log.error("handleBindException==>{}",e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        return getJsonArrayResponseEntity(bindingResult);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException==>{}",e.getMessage());
        return ResponseEntity.badRequest().body("JSON格式错误，" + e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("DuplicateKeyException==>{}",e.getMessage());
        return ResponseEntity.badRequest().body("唯一约束重复，" + e.getCause().getMessage());
    }


    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity handleArithmeticException(ArithmeticException e) {
        log.error("ArithmeticException==>{}",e.getMessage());
        return ResponseEntity.badRequest().body("0不能成为除数," + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        e.printStackTrace();
        log.error("Exception==>{}",e.getMessage());
        return ResponseEntity.badRequest().body("系统繁忙，请稍后再试！" + Optional.ofNullable(e.getMessage()).orElse(""));
    }

}
