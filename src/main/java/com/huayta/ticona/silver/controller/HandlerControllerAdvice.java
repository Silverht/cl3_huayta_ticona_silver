package com.huayta.ticona.silver.controller;

import java.util.NoSuchElementException;

import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.huayta.ticona.silver.controller.advice.ResponseError;
import com.huayta.ticona.silver.controller.advice.ResponseErrorGeneral;
import com.huayta.ticona.silver.controller.advice.Violation;

@ControllerAdvice(basePackages = {"com.huayta.ticona.silver"})
public class HandlerControllerAdvice implements ResponseBodyAdvice<Object>  {
		
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResponseError> onArgumntValidationException(MethodArgumentNotValidException ex) {       
        ResponseError responseError=new ResponseError();
        responseError.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        ex.getBindingResult().getFieldErrors().forEach((error) -> {     
            responseError.getViolations().add(new Violation(error.getField(), error.getDefaultMessage()));
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
    
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public ResponseEntity<Object>onDataIntegrityViolationException(DataIntegrityViolationException ex){
		ResponseErrorGeneral error=new ResponseErrorGeneral();
		error.setCodigo(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Ocurrió un error interno");
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseBody
	public ResponseEntity<Object>onNoSuchElementException(NoSuchElementException ex){
		ResponseErrorGeneral error=new ResponseErrorGeneral();
		error.setCodigo(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(JpaObjectRetrievalFailureException.class)
	@ResponseBody
	public ResponseEntity<Object>onJpaObjectRetrievalFailureException(JpaObjectRetrievalFailureException ex){
		ResponseErrorGeneral error=new ResponseErrorGeneral();
		error.setCodigo(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		return body;
	}
}
