package com.kapil.pre16;
import java.util.Objects;

public final class Employee {
    private final Long empId;
    private final String firstName;
    private final String lastName;

    public Employee(Long empId, String firstName, String lastName) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getEmpId() {
        return empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Employee) obj;
        return Objects.equals(this.empId, that.empId) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee[" +
                "empId=" + empId + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ']';
    }
}
