package com.kapil.dto;

import java.util.Objects;

public class Subject {
    private String subjectName;
    private Integer marks;

    private Subject() {}

    public Subject(String subjectName, Integer marks) {
        this.subjectName = subjectName;
        this.marks = marks;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return subjectName.equals(subject.subjectName) &&
                marks.equals(subject.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName, marks);
    }
}
