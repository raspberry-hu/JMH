package JFRtest;

public class JFRTest {
    public static void main(String args[]) throws Exception {
//        JFRTestMethodr1 jfrtestMethodr1 = new JFRTestMethodr1();
        JFRTestMethodk1 testMethodk1 = new JFRTestMethodk1();
        testMethodk1.init();
//        jfrtestMethodr1.init();
        testMethodk1.secp256k1Sign();
        testMethodk1.secp256k1Verify();
//        jfrtestMethodr1.secp256r1Sign();
//        jfrtestMethodr1.secp256r1Verify();
    }
}
