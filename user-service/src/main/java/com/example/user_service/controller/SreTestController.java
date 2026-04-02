@GetMapping("/cause-error")
public String causeError() {
    // Humne error counter badha diya (this counts attempts that *could* lead to error)
    errorCounter.increment();

    try {
        // Asli error trigger kar rahe the, ab ise handle karenge
        int result = 10 / 0;
        return "Result: " + result;
    } catch (ArithmeticException e) {
        // Error gracefully handled, service won't crash due to this specific exception.
        // In a real scenario, this might also log the full stack trace for debugging.
        return "Error: An arithmetic exception occurred: " + e.getMessage() + ". This error was handled.";
    }
}