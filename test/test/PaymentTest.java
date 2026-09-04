//package test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.Test;
//
//public class PaymentTest {
//
//
//    @Test
//    public void testBalanceCalculation() {
//
//        double grandTotal = 9000;
//
//        double payment = 10000;
//
//        double expectedBalance = 1000;
//
//        double actualBalance =
//                payment - grandTotal;
//
//        assertEquals(
//                expectedBalance,
//                actualBalance
//        );
//    }
//
//
//    @Test
//    public void testExactPayment() {
//
//        double grandTotal = 9000;
//
//        double payment = 9000;
//
//        double expectedBalance = 0;
//
//        double actualBalance =
//                payment - grandTotal;
//
//        assertEquals(
//                expectedBalance,
//                actualBalance
//        );
//    }
//
//
//    @Test
//    public void testInsufficientPayment() {
//
//        double grandTotal = 9000;
//
//        double payment = 5000;
//
//        double expectedBalance = -4000;
//
//        double actualBalance =
//                payment - grandTotal;
//
//        assertEquals(
//                expectedBalance,
//                actualBalance
//        );
//    }
//}