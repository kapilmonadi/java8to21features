package com.kapil.records;

public record Employee(Long id, String firstName, String lastName) {
    public String toString(){
        return "Id: "+id + ", First Name: "+ firstName + ", Last name: "+ lastName;
    }
}
