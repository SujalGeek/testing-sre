public User getUserDetails(String userId) {
        User user = userRepository.findById(userId).get(); // Prone to NoSuchElementException if user not found
        return user;
    }