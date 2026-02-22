package com.gamechane.backend.security.user.persistence;

import com.gamechane.backend.security.user.domain.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class RolesConverter implements AttributeConverter<List<Role>, String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(List<Role> roles) {
        StringBuilder databaseColumn = new StringBuilder();

        if (roles != null && !roles.isEmpty()) {
            int rolesAdded = 0;

            for (Role role : roles) {
                databaseColumn.append(databaseColumn.toString().concat(role.name()));
                rolesAdded += 1;

                if (rolesAdded < roles.size()) {
                    databaseColumn.append(SEPARATOR);
                }
            }
        }

        return databaseColumn.toString();
    }

    @Override
    public List<Role> convertToEntityAttribute(String dbRoles) {
        List<Role> roles = new ArrayList<>();

        if (dbRoles != null && !dbRoles.isEmpty()) {
            String[] rolesArray = dbRoles.split(SEPARATOR);

            for (String role : rolesArray) {
                roles.add(Role.valueOf(role));
            }
        }

        return roles;
    }
}
