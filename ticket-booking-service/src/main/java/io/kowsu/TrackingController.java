package io.kowsu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class TrackingController {
	
	
	@GetMapping("/health/check")
	public String facilities() {
		return "Trackings are working.";
	}

}
