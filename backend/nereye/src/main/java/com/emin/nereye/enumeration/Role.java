package com.emin.nereye.enumeration;

public enum Role {
    USER(0),
    ADMIN(1);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public static Role fromValue(int value) {
        for (Role role : Role.values()) {
            if (role.value == value) {
                return role;
            }
        }
        return USER; // Default to USER if invalid value
    }

    public int getValue() {
        return value;
    }
}
