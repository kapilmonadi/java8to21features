package com.kapil.java21;

public class PatternMatchingForSwitchSample {
    public static void main(String[] args) {
        Employee employee1 = new PermanentEmployee();
        Employee employee2 = new ContractualEmployee();
    }

    private static abstract class Employee {
        abstract String getDescription();
    }

    private static class PermanentEmployee extends Employee {
        @Override
        String getDescription() {
            return "Permanent";
        }

        String getPermanentBenefits(){
            return "PermanentBenefits";
        }
    }


    private static class ContractualEmployee extends Employee {
        @Override
        String getDescription() {
            return "Contractual";
        }

        String getContractualBenefits(){
            return "ContractualBenefits";
        }
    }
}
