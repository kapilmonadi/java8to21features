package com.kapil.java17;

public sealed class OnlineEducation implements Education
        permits FreeOnlineEducation, PaidOnlineEducation {
    @Override
    public void conductClass() {
        System.out.println("Hello there from Online Education");
    }
}
