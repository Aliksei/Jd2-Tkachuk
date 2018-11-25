package com.itacademy.service.converter;

import com.itacademy.database.entity.embedded.Credentials;
import com.itacademy.database.entity.role.Admin;
import com.itacademy.database.entity.role.Resident;
import com.itacademy.database.entity.role.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(User user) {
        Credentials credentials = user.getCredentials();
        String userRole = null;
        if (user instanceof Resident) {
            userRole = "RESIDENT";
        } else if (user instanceof Admin) {
            userRole = "ADMIN";
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(credentials.getLogin())
                .password("{noop}" + credentials.getPassword())
                .authorities(userRole)
                .build();
    }
}
