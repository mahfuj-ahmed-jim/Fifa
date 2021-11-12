package com.ai.fifa.Constant;

public class ConstantUser {

    private static String firstName = null;
    private static String lastName = null;

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        ConstantUser.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        ConstantUser.lastName = lastName;
    }

}
