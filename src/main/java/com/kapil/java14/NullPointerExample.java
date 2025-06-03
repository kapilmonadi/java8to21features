package com.kapil.java14;

import java.util.List;

public class NullPointerExample {

    static class Person {
        private String name;
        private int age;
        private Address address;

        public Person(String name, int age, Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }

    static class Address {
        private String houseNumber;
        private Locality locality;

        public Address(String houseNumber, Locality locality) {
            this.houseNumber = houseNumber;
            this.locality = locality;
        }
    }

    static class Locality {
        private String zipCode;
        private String landMark;

        public Locality(String zipCode, String landMark) {
            this.zipCode = zipCode;
            this.landMark = landMark;
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("Ramesh", 22, new Address("D125", new Locality("400028", "Shivaji Park")));
        Person person2 = new Person("Suresh", 22, new Address("D126", null));

        List<Person> personList = List.of(person1, person2);
        personList.stream().forEach(person -> System.out.println(person.name  + " : " + person.address.locality.zipCode));
    }
}
