package yunona.cloud_storage_backend.exception.advice;

import lombok.extern.slf4j.Slf4j;
import yunona.cloud_storage_backend.exception.FileNotFoundException;
import yunona.cloud_storage_backend.exception.IncorrectDataEntry;
import yunona.cloud_storage_backend.exception.InternalServerError;
import yunona.cloud_storage_backend.exception.UserNotFoundException;
import yunona.cloud_storage_backend.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ErrorMessage> fileNotFoundExceptionHandler(FileNotFoundException exc) {
        ErrorMessage msg = new ErrorMessage();
        msg.setMessage(exc.getMessage());
        msg.setId(exc.getId());
        log.error("(ERROR) Ошибка: {}, id: {}", exc.getMessage(), exc.getId());
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> fileNotFoundExceptionHandler(UserNotFoundException exc) {
        ErrorMessage msg = new ErrorMessage();
        msg.setMessage(exc.getMessage());
        msg.setId(exc.getId());
        log.error("(ERROR) Ошибка: {}, id: {}", exc.getMessage(), exc.getId());
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectDataEntry.class)
    public ResponseEntity<ErrorMessage> fileNotFoundExceptionHandler(IncorrectDataEntry exc) {
        ErrorMessage msg = new ErrorMessage();
        msg.setMessage(exc.getMessage());
        msg.setId(exc.getId());
        log.error("(ERROR) Ошибка: {}, id: {}", exc.getMessage(), exc.getId());
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErrorMessage> internalServerErrorHandler(InternalServerError exc) {
        ErrorMessage msg = new ErrorMessage();
        msg.setMessage(exc.getMessage());
        msg.setId(exc.getId());
        log.error("(ERROR) Ошибка: {}, id: {}", exc.getMessage(), exc.getId());
        return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
