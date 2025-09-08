package com.emin.nereye.enumeration;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Byte> {

    @Override
    public Byte convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return (byte) role.getValue();
    }

    @Override
    public Role convertToEntityAttribute(Byte dbData) {
        if (dbData == null) {
            return null;
        }
        return Role.fromValue(dbData.intValue());
    }
}
