public int calculateGrade(int totalMarks, int subjectCount) {
    if (subjectCount == 0) {
        return 0;
    }
    return totalMarks / subjectCount;
}