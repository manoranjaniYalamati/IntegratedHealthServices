package com.sai.model;

public enum Gender {
    MALE, FEMALE;
    public static String getGenderName(Gender gen){
        switch (gen){
            case MALE : return "Man";
            case FEMALE : return "Woman";
            default : return null;
        }
    }
}

