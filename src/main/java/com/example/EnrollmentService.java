public void enroll(Student student) {
    if (student == null) {
        throw new IllegalArgumentException("Student object cannot be null.");
    }
    Long id = student.getId();
    repository.save(id);
}