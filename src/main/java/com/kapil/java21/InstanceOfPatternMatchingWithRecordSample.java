package com.kapil.java21;

public class InstanceOfPatternMatchingWithRecordSample {

    public static void main(String[] args) {

        Employee employee1 = new PermanentEmployee("F1", "VP");
        Employee employee2 = new ContractualEmployee();
        printPermanentEmployeeDetailsWithPatternMatching(employee1);
        printPermanentEmployeeDetailsWithPatternMatching(employee2);

        printEmployeeDetailsWithPatternMatchingAndRecords(employee1);
        printEmployeeDetailsWithPatternMatchingAndRecords(employee2);
    }

    private static void printPermanentEmployeeDetailsWithPatternMatching(Employee employee) {
        if (employee instanceof PermanentEmployee permanentEmployee) {
            System.out.println(permanentEmployee.getDescription());
            System.out.println("grade is : " + permanentEmployee.grade);
            System.out.println("designation is : " + permanentEmployee.designation);
        } else if (employee instanceof ContractualEmployee contractualEmployee) {
            System.out.println(employee.getDescription());
        }
    }

    private static void printEmployeeDetailsWithPatternMatchingAndRecords(Employee employee) {
        if (employee instanceof PermanentEmployee(String grade, String designation)) {
            System.out.println(employee.getDescription());
            System.out.println("grade is : " + grade);
            System.out.println("designation is : " + designation);
        } else if (employee instanceof ContractualEmployee()) {
            System.out.println(employee.getDescription());
        }
    }

    private interface Employee {
        String getDescription();
    }

    private static record PermanentEmployee(String grade, String designation) implements Employee {
        @Override
        public String getDescription() {
            return "Permanent";
        }
    }

    private static record ContractualEmployee() implements Employee {
        @Override
        public String getDescription() {
            return "Contractual";
        }
    }
}
