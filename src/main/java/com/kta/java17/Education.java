package com.kta.java17;

public sealed interface Education permits OfflineEducation, OnlineEducation {
    void conductClass();
}
