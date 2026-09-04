//package test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.Test;
//
//public class BillCalculationTest {
//
//
//    @Test
//    public void testTreatmentTotal() {
//
//        double treatmentOne = 3000;
//        double treatmentTwo = 5000;
//
//        double expectedTotal = 8000;
//
//        double actualTotal =
//                treatmentOne + treatmentTwo;
//
//        assertEquals(
//                expectedTotal,
//                actualTotal
//        );
//    }
//
//
//    @Test
//    public void testGrandTotal() {
//
//        double treatmentTotal = 8000;
//
//        double consultationFee = 1000;
//
//        double expectedTotal = 9000;
//
//        double actualTotal =
//                treatmentTotal
//                + consultationFee;
//
//        assertEquals(
//                expectedTotal,
//                actualTotal
//        );
//    }
//}