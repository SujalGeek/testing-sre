package com.ai.integration.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.Objects;

@Service
public class AiIntegrationService {

    private static final Logger logger = LoggerFactory.getLogger(AiIntegrationService.class);

    /**
     * Processes AI inference requests with defensive error handling to prevent 
     * core_errors_total spikes.
     * 
     * @param payload The input data for the AI model.
     * @return An Optional containing the result, or empty if processing failed.
     */
    public Optional<String> processInference(String payload) {
        try {
            // Fix: Added null and empty checks to prevent NullPointerExceptions 
            // and invalid state processing which often trigger service alerts.
            if (payload == null || payload.trim().isEmpty()) {
                logger.warn("Received empty or null payload for AI inference.");
                return Optional.empty();
            }

            // Simulated downstream interaction logic
            String result = performExternalInference(payload);
            
            return Optional.ofNullable(result);

        } catch (Exception e) {
            // Fix: Detailed logging to capture the exception cause, 
            // preventing silent failures that lead to cascading service errors.
            logger.error("Critical error detected during AI inference processing: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    private String performExternalInference(String data) {
        // Placeholder for integration logic. 
        // In a real scenario, this would involve a RestTemplate or WebClient call.
        Objects.requireNonNull(data, "Data cannot be null for inference");
        return "Processed: " + data.hashCode();
    }
}