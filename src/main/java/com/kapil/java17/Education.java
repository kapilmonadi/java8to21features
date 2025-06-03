package com.kapil.java17;

public sealed interface Education permits OfflineEducation, OnlineEducation {
    void conductClass();
}
