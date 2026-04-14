package com.example.performance_service.controller;


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
                .tag("service", "performance-service") // Tag zaroori hai taaki Prometheus ko pata chale ye Quiz ka hai
                .register(Metrics.globalRegistry);
    }

    @GetMapping("/cause-error")
    public String causeError() {
        // Fixed: Removed the intentional ArithmeticException (Divide by zero)
        // to resolve the runtime error while keeping the controller functional.
        return "Service is running correctly. No errors triggered.";
    }
}