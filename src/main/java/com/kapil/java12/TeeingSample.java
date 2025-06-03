package com.kapil.java12;

import com.kapil.dto.Student;
import com.kapil.dto.Subject;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeeingSample {
    public static void main(String[] args) {
        // initialize the student objects
        Student student1 = new Student(1L, "Amar" , "Kumar");
        Student student2 = new Student(2L, "Ankit" , "Kumar");
        Student student3 = new Student(3L, "Manas" , "Kumar");

        List<Subject> student1Subjects = List.of(new Subject("Physics", 90), new Subject("Chemistry", 88), new Subject("Maths", 92));
        List<Subject> student2Subjects = List.of(new Subject("Physics", 77), new Subject("Chemistry", 88), new Subject("Maths", 66));
        List<Subject> student3Subjects = List.of(new Subject("Physics", 89), new Subject("Chemistry", 90), new Subject("Maths", 89));

        student1.setSubjects(student1Subjects);
        student2.setSubjects(student2Subjects);
        student3.setSubjects(student3Subjects);

        List<Student> studentList = List.of(student1, student2, student3);

        // let's say we want to find the total marks and highest marks of each individual student.
        // if we use plain vanilla Streams API, we would have to run 2 iteration by creating 2 streams.
        // using Teeing we can do it at 1 go.
        Map<String, String> studentMarksMap = new HashMap<>();
        studentList.stream().forEach(student -> {
            student.getSubjects().stream().collect(
                    Collectors.teeing(
                            Collectors.summingInt(Subject::getMarks),
                            Collectors.maxBy(Comparator.comparing(Subject::getMarks)),
                            (collector1, collector2) -> {
                                studentMarksMap.put(student.getFirstName() +   " " +student.getLastName(), "Total marks : " + collector1 + "( Highest : " + collector2.get().getMarks() + ")");
                                return studentMarksMap;
                            }
                    )
            );
        });
        System.out.println(studentMarksMap);
    }
}
