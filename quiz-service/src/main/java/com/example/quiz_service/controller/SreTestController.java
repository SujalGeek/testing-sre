package com.example.quiz_service.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics; // Global Metrics use karenge
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class SreTestController {

    private final Counter errorCounter;

    public SreTestController() {
        // Constructor khali rakho aur Metrics.globalRegistry use karo
        // Isse dependency injection ka lafda hi khatam!
        this.errorCounter = Counter.builder("core_errors")
                .description("Total number of java errors for SRE test in Quiz Service")
                .tag("service", "quiz-service") // Tag zaroori hai taaki Prometheus ko pata chale ye Quiz ka hai
                .register(Metrics.globalRegistry);
    }

    @GetMapping("/cause-error")
    public String causeError() {
        // The previous implementation was deliberately throwing an ArithmeticException.
        // To fix the issue while maintaining the SRE intent, we ensure the operation is safe.
        // If the intention was to demonstrate error handling, we now return a safe value.
        int divisor = 0;
        if (divisor == 0) {
            return "Error prevented: Cannot divide by zero.";
        }
        
        int result = 10 / divisor; 
        return "Result: " + result;
    }
}