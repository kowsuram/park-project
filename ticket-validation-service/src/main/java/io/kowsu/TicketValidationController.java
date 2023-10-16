package io.kowsu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/status")
@RefreshScope
public class TicketValidationController {
	
	@Value("${custom.key}")
	private String customValue;
	
	
	@GetMapping("/health/check")
	public String facilities() {
		return "Ticket Validations are working." + customValue;
	}

}
