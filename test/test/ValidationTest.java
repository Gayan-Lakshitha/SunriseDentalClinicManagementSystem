//package test;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.Test;
//
//public class ValidationTest {
//
//    @Test
//    public void testValidContactNumber() {
//
//        String contact = "0771234567";
//
//        boolean result =
//                contact.matches("\\d{10}");
//
//        assertTrue(result);
//    }
//
//
//    @Test
//    public void testInvalidContactNumber() {
//
//        String contact = "077ABC123";
//
//        boolean result =
//                contact.matches("\\d{10}");
//
//        assertFalse(result);
//    }
//
//
//    @Test
//    public void testValidEmail() {
//
//        String email =
//                "patient@gmail.com";
//
//        boolean result =
//                email.matches(
//                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
//                );
//
//        assertTrue(result);
//    }
//
//
//    @Test
//    public void testInvalidEmail() {
//
//        String email =
//                "patient@gmail";
//
//        boolean result =
//                email.matches(
//                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
//                );
//
//        assertFalse(result);
//    }
//
//
//    @Test
//    public void testValidPassword() {
//
//        String password =
//                "password123";
//
//        boolean result =
//                password.length() >= 8;
//
//        assertTrue(result);
//    }
//
//
//    @Test
//    public void testInvalidPassword() {
//
//        String password =
//                "123";
//
//        boolean result =
//                password.length() >= 8;
//
//        assertFalse(result);
//    }
//}