package com.example.ai_integration_service.controller;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class SreTestController {

    private final Counter assignmentErrorCounter;

    public SreTestController() {
        this.assignmentErrorCounter = Counter.builder("core_errors")
                .description("Total number of java errors in Assignment Service")
                .tag("service", "ai-integration-service")
                .register(Metrics.globalRegistry);
    }

    @GetMapping("/cause-error")
    public String triggerNpe() {
        // Incrementing error count for monitoring, but ensuring the method handles the null scenario safely
        assignmentErrorCounter.increment();
        
        // Fixed: Added null check to prevent NullPointerException
        String data = null;
        return "Length of data is: " + (data != null ? data.length() : "data is null");
    }
}