package com.kapil.concurrency;

import java.time.Duration;
import java.util.concurrent.*;

public class CallableSample {

    private static Callable<Employee> callable = CallableSample::getEmployee;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Sleep for 2 seconds and then return the employee
        Callable<Employee> callableWithSleep = () -> {
          Thread.sleep(Duration.ofSeconds(2));
          return getEmployee();
        };
        // Let's create a callable
        // that will return an Employee once it is executed
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            Future<Employee> future = executorService.submit(callable);
            System.out.println(future.get());

            Future<Employee> futureForCallableWithSleep = executorService.submit(callableWithSleep);
            System.out.println(futureForCallableWithSleep.get());
        }
        Thread.sleep(Duration.ofSeconds(2));
        System.out.println("Main thread completed !");
    }

    /**
     * Return a hardcoded value for now
     * This can come from a DB call or some other service call
     * @return Employee
     */
    private static Employee getEmployee() {
        return new Employee("Mohan", 20);
    }

    private static class Employee {
        String name;
        int age;

        Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return "Employee Name : " + name + ", Employee Age : " + age;
        }
    }
}
