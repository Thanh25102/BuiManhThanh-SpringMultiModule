package com.buimanhthanh.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncoderTest {

    @Test
    public void testEncoderPassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "Thanh25102";
        String encodedPass = passwordEncoder.encode(rawPassword);

        //$2a$10$BsIaTbc6oHE/wQeHxGR1feL3aI46Yut0f7cd5vRwI3AhkAUTe0ksC
        assertTrue(passwordEncoder.matches(rawPassword,encodedPass));
    }
}
