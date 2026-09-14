package com.kta.java12;

import com.kta.dto.Student;
import com.kta.dto.Subject;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeeingSample {
    public static void main(String[] args) {
        // initialize the student objects
        List<Student> studentList = getStudentList();
        withoutTeeing(studentList);
        withTeeing(studentList);
    }

    private static List<Student> getStudentList() {
        Student student1 = new Student(1L, "Amar", "K");
        Student student2 = new Student(2L, "Ankit", "P");
        Student student3 = new Student(3L, "Deb", "M");

        List<Subject> student1Subjects = List.of(new Subject("Physics", 90), new Subject("Chemistry", 88), new Subject("Maths", 92));
        List<Subject> student2Subjects = List.of(new Subject("Physics", 77), new Subject("Chemistry", 88), new Subject("Maths", 66));
        List<Subject> student3Subjects = List.of(new Subject("Physics", 89), new Subject("Chemistry", 90), new Subject("Maths", 89));

        student1.setSubjects(student1Subjects);
        student2.setSubjects(student2Subjects);
        student3.setSubjects(student3Subjects);

        return List.of(student1, student2, student3);
    }

    private static void withoutTeeing(List<Student> studentList){
        System.out.println("Below output is without Teeing, using the stream twice !");
        Map<String, Integer> studentMaximumMarks = studentList.stream().collect(Collectors.toMap(student -> student.getFirstName() + " " + student.getLastName(),
                student -> student.getSubjects().stream().max(Comparator.comparing(Subject::getMarks)).map(Subject::getMarks).orElse(0)));

        Map<String, Integer> studentTotalMarks = studentList.stream().collect(Collectors.toMap(student -> student.getFirstName() + " " + student.getLastName(),
                student -> student.getSubjects().stream().mapToInt(Subject::getMarks).sum()));

        System.out.println(studentMaximumMarks);
        System.out.println(studentTotalMarks);
    }

    private static void withTeeing(List<Student> studentList){
        // let's say we want to find the total marks and highest marks of each individual student.
        // if we use plain vanilla Streams API, we would have to run 2 iteration by creating 2 streams.
        // using Teeing we can do it at 1 go.
        // Teeing becomes useful when you need multiple aggregations over the same stream
        System.out.println("Below output is with Teeing, using the stream only once !");
        Map<String, StudentMarksSummary> studentMarksMap;
        studentMarksMap = studentList.stream().collect(Collectors.toMap(student -> student.getFirstName() + " " + student.getLastName(),

                student -> student.getSubjects().stream().collect(Collectors.teeing(

                        // Collector 1: Calculate total marks for each student
                        Collectors.summingInt(Subject::getMarks),

                        // Collector 2: Find the highest marks for each student
                        Collectors.maxBy(Comparator.comparing(Subject::getMarks)),

                        // Merger
                        (totalMarks, highestSubject) -> new StudentMarksSummary(totalMarks, highestSubject.map(Subject::getMarks).orElse(0))))));

        // Print results
        studentMarksMap.forEach((studentName, summary) -> System.out.println(studentName + " -> Total Marks: " + summary.totalMarks() + ", Highest Marks: " + summary.highestMarks()));
    }

    record StudentMarksSummary(int totalMarks, int highestMarks) { }
}
