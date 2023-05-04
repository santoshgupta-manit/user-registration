package com.ibm.assessment.user.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ibm.assessment.user.enums.StatusEnum;
import com.ibm.assessment.user.model.ServiceMessage;
import com.ibm.assessment.user.model.ServiceResponse;
import com.ibm.assessment.user.service.MessageProvider;



@ControllerAdvice
public class APIGlobalExceptionHandler {

	private final MessageProvider messageProvider;

	public APIGlobalExceptionHandler(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}

	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ServiceResponse> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		Set<FieldError> fieldErrorsSet = Set.copyOf(fieldErrors);
		String errorMessage = fieldErrorsSet.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining(", "));

		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setMessage(getServiceMessage(StatusEnum.STATUS_400, errorMessage));
		return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<ExceptionMessage> handleBadRequestException(Exception exception, WebRequest request) {

		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setMessage(getServiceMessage(StatusEnum.STATUS_400, exception.getMessage()));
		return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException exception) {
		String message = messageProvider.getMessage("json.invalidFormat");
		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setMessage(getServiceMessage(StatusEnum.STATUS_400, message));
		return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionMessage> handleHttpMethodTypeException(Exception exception) {

		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setMessage(getServiceMessage(StatusEnum.STATUS_405, exception.getMessage()));
		return new ResponseEntity<>(exceptionMessage, HttpStatus.METHOD_NOT_ALLOWED);

	}

	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class, HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<String> handleHttpMediaTypeException(Exception exception, WebRequest request) {
		HttpStatus status = null;
		String message = "";
		if (exception instanceof HttpMediaTypeNotSupportedException) {
			status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
			message = StatusEnum.STATUS_415.getDescription();
		} else if (exception instanceof HttpMediaTypeNotAcceptableException) {
			status = HttpStatus.NOT_ACCEPTABLE;
			message = StatusEnum.STATUS_415.getDescription();
		}
		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setMessage(getServiceMessage(StatusEnum.STATUS_406, exception.getMessage()));
		return new ResponseEntity<>(message, status);

	}

	@ExceptionHandler({RuntimeException.class })
	public ResponseEntity<ExceptionMessage> handleGlobalException(Exception exception, WebRequest request) {
		ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setMessage(getServiceMessage(StatusEnum.STATUS_500, exception.getMessage()));
		return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private ServiceMessage getServiceMessage(StatusEnum statusEnum, String description) {
		String type = statusEnum.getHttpStatus().getReasonPhrase();
		HttpStatus code = statusEnum.getHttpStatus();
		return new ServiceMessage(type, code, description);
	}

}
