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
        // Fixed: Removed the intentional ArithmeticException to prevent HTTP 500
        // errorCounter incremented only if logic requires tracking operational hits, 
        // but removed the division by zero that triggered the Prometheus alert.
        
        return "Service is healthy and functioning correctly.";
    }
}