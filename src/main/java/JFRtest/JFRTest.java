package JFRtest;

public class JFRTest {
    public static void main(String args[]) throws Exception {
        TestMethodr1 testMethodr1 = new TestMethodr1();
//        TestMethodk1 testMethodk1 = new TestMethodk1();
//        testMethodk1.init();
        testMethodr1.init();
//        testMethodk1.secp256k1Sign();
//        testMethodk1.secp256k1Verify();
        testMethodr1.secp256r1Sign();
        testMethodr1.secp256r1Verify();
    }
}
