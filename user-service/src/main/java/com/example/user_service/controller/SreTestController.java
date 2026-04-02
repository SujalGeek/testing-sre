package com.example.user_service.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class SreTestController {

    private final Counter errorCounter;

    public SreTestController(MeterRegistry registry) {
        // Ye wahi metric hai jo Prometheus dekhega: "core_errors_total"
        this.errorCounter = Counter.builder("core_errors")
                .description("Total number of java errors for SRE test")
                .register(registry);
    }

    @GetMapping("/cause-error")
    public String causeError() {
        // Humne error counter badha diya
        errorCounter.increment();
        
        // Asli error trigger kar rahe hain, lekin ab use gracefully handle karenge
        try {
            int result = 10 / 0; 
            return "Result: " + result; // Ye line kabhi execute nahi hogi
        } catch (ArithmeticException e) {
            // Error ko catch karke ek user-friendly message return kar rahe hain
            // Taki application crash na ho aur SRE test ke liye proper response mile
            return "Error: Cannot divide by zero. Original message: " + e.getMessage();
        }
    }
    
    
}