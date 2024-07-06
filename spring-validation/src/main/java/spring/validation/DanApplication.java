package spring.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestAttributes;
import spring.validation.domain.exception.ValidCustomException;

import java.util.Map;

@SpringBootApplication
public class DanApplication {

	@Bean
	public ErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes() {

			@Override
			public Map<String, Object> getErrorAttributes(
					RequestAttributes requestAttributes,
					boolean includeStackTrace) {
				Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
				Throwable error = getError(requestAttributes);
				if (error instanceof ValidCustomException) {
					errorAttributes.put("errors", ((ValidCustomException)error).getErrors());
				}
				return errorAttributes;
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DanApplication.class, args);
	}

}
