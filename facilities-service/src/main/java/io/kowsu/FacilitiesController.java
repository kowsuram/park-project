package io.kowsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/status")
public class FacilitiesController {
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/health/check")
	public String facilities(HttpServletRequest httpServletRequest) {
		return "Facilities are working." + environment.getProperty("local.server.port");
	}

}
