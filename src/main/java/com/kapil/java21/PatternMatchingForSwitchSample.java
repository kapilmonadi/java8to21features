package com.kapil.java21;

public class PatternMatchingForSwitchSample {
    public static void main(String[] args) {
        Employee employee1 = new PermanentEmployee();
        Employee employee2 = new ContractualEmployee();

        printEmployeeDetails(employee1);
        printEmployeeDetails(employee2);
        printEmployeeDetails(null);
    }

    private static void printEmployeeDetails(Employee employee){
        switch(employee){
            case null -> System.out.println("employee is null");
            case PermanentEmployee permanentEmployee -> permanentEmployee.printPermanentBenefits();
            case ContractualEmployee contractualEmployee -> contractualEmployee.printContractualBenefits();
            default -> throw new IllegalStateException("Unexpected value: " + employee);
        }
    }

    private static abstract class Employee {
        abstract String getDescription();
    }

    private static class PermanentEmployee extends Employee {
        @Override
        String getDescription() {
            return "Permanent";
        }

        void printPermanentBenefits(){
            System.out.println("Broader Access, Insurance, Party, Outing etc. etc.");
        }
    }


    private static class ContractualEmployee extends Employee {
        @Override
        String getDescription() {
            return "Contractual";
        }

        void printContractualBenefits(){
            System.out.println("Freedom, Flexibility etc. etc.");
        }
    }
}
