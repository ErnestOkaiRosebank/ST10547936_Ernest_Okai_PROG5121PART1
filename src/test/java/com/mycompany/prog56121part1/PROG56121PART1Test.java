package com.mycompany.prog56121part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PROG56121PART1Test {

    // ===== testCheckUserName =====
    @Test
    public void testCheckUserName() {
        // Test Data: "Kyl_1"
        assertTrue(PROG56121PART1.checkUserName("Kyl_1")); // valid
        assertFalse(PROG56121PART1.checkUserName("user")); // missing _
        assertFalse(PROG56121PART1.checkUserName("long_username")); // too long
    }

    @Test
    public void testCheckUserName_BoundaryAndEdgeCases() {
        assertFalse(PROG56121PART1.checkUserName("abcde")); // right length, no underscore
        assertTrue(PROG56121PART1.checkUserName("_")); // single underscore, valid
    }

    // ===== testCheckPasswordComplexity =====
    @Test
    public void testCheckPasswordComplexity() {
        // Test Data: "Ch&&sec@ke99!"
        assertTrue(PROG56121PART1.checkPasswordComplexity("Ch&&sec@ke99!")); // valid
        assertFalse(PROG56121PART1.checkPasswordComplexity("password")); // no capital, number, special
        assertFalse(PROG56121PART1.checkPasswordComplexity("Password")); // no number, special
        assertFalse(PROG56121PART1.checkPasswordComplexity("Pass1234")); // no special char
    }

    @Test
    public void testCheckPasswordComplexity_BoundaryAndEdgeCases() {
        assertFalse(PROG56121PART1.checkPasswordComplexity("Pas1!")); // has everything but too short
        assertTrue(PROG56121PART1.checkPasswordComplexity("Passw1!x")); // exactly 8 chars, valid
        assertFalse(PROG56121PART1.checkPasswordComplexity("pass123!")); // no capital
    }

    // ===== testCheckCellPhoneNumber =====
    @Test
    public void testCheckCellPhoneNumber() {
        assertTrue(PROG56121PART1.checkCellPhoneNumber("+27123456789")); // valid
        assertFalse(PROG56121PART1.checkCellPhoneNumber("0123456789")); // missing +27
        assertFalse(PROG56121PART1.checkCellPhoneNumber("+2712345678")); // too short
    }

    @Test
    public void testCheckCellPhoneNumber_BoundaryAndEdgeCases() {
        assertFalse(PROG56121PART1.checkCellPhoneNumber("+271234567890")); // too long
        assertFalse(PROG56121PART1.checkCellPhoneNumber("+2712345abc9")); // contains letters
    }

    // ===== testRegisterUser =====
    @Test
    public void testRegisterUser() {
        assertEquals("Username and password successfully captured. User registered!",
                PROG56121PART1.registerUser("user_", "Passw0rd!"));

        assertEquals("Username is not correct. Must have _ and max 5 chars.",
                PROG56121PART1.registerUser("user", "Passw0rd!"));

        assertEquals("Password is not correct. Must have 8+ chars, capital letter, number, and special char.",
                PROG56121PART1.registerUser("user_", "password"));
    }

    @Test
    public void testRegisterUser_BothInvalid_UsernameErrorTakesPriority() {
        assertEquals("Username is not correct. Must have _ and max 5 chars.",
                PROG56121PART1.registerUser("baduser", "password"));
    }

    // ===== testLoginUser =====
    @Test
    public void testLoginUser() {
        String storedUsername = "user_";
        String storedPassword = "Passw0rd!";

        assertTrue(PROG56121PART1.loginUser("user_", "Passw0rd!", storedUsername, storedPassword));
        assertFalse(PROG56121PART1.loginUser("wrong", "Passw0rd!", storedUsername, storedPassword));
        assertFalse(PROG56121PART1.loginUser("user_", "wrong", storedUsername, storedPassword));
    }

    @Test
    public void testLoginUser_CaseSensitive() {
        assertFalse(PROG56121PART1.loginUser("USER_", "Passw0rd!", "user_", "Passw0rd!"));
    }

    // ===== testReturnLoginStatus =====
    @Test
    public void testReturnLoginStatus() {
        assertEquals("Login successful! Welcome back!", PROG56121PART1.returnLoginStatus(true));
        assertEquals("Username or password incorrect, please try again.", PROG56121PART1.returnLoginStatus(false));
    }

    @Test
    public void testLoginSuccessful() {
        assertTrue(PROG56121PART1.loginUser("Kyl_1", "Ch&&sec@ke99!", "Kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        assertFalse(PROG56121PART1.loginUser("wrong", "wrong", "Kyl_1", "Ch&&sec@ke99!"));
    }

    // ===== Integration-style test: register then log in =====
    @Test
    public void testRegisterThenLoginIntegration() {
        String username = "Kyl_1";
        String password = "Ch&&sec@ke99!";

        String registerResult = PROG56121PART1.registerUser(username, password);
        assertEquals("Username and password successfully captured. User registered!", registerResult);

        boolean loginResult = PROG56121PART1.loginUser(username, password, username, password);
        assertTrue(loginResult);
        assertEquals("Login successful! Welcome back!", PROG56121PART1.returnLoginStatus(loginResult));
    }
}