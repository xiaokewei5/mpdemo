package com.example.mpdemo.handler;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JSONArray> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        JSONArray array = new JSONArray();
        JSONObject json;
        for(FieldError error : fieldErrors){
            json = new JSONObject();
            json.set("filed",error.getField());
            json.set("error", error.getDefaultMessage());
            array.add(json);
        }
        return ResponseEntity.badRequest().body(array);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<JSONArray> handleBindException(BindException e) {
        e.printStackTrace();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        JSONArray array = new JSONArray();
        JSONObject json;
        for(FieldError error : fieldErrors){
            json = new JSONObject();
            json.set("filed",error.getField());
            json.set("error", error.getDefaultMessage());
            array.add(json);
        }
        return ResponseEntity.badRequest().body(array);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("JSON格式错误，"+e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("唯一约束重复，"+e.getCause().getMessage());
    }


    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity handleArithmeticException(ArithmeticException e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("0不能成为除数,"+ e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("系统繁忙，请稍后再试");
    }

}
