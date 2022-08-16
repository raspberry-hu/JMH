package JMHtest;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

@State(Scope.Thread)
public class TestMethodr1 {
    PrivateKey secp256r1privateKey;
    PublicKey secp256r1publicKey;
    byte[] signature;
    static byte[] message;
    static byte[] signatureMessage256r1;


    @Setup
    public void init() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("EC", "SunEC");
        ECGenParameterSpec ecsp = new ECGenParameterSpec("secp256r1");
        kg.initialize(ecsp);
        KeyPair kp = kg.genKeyPair();
        secp256r1privateKey = kp.getPrivate();
        secp256r1publicKey = kp.getPublic();
        message = Files.readAllBytes(Paths.get("resources/128B.txt"));
        signatureMessage256r1 = message;
        sign(signatureMessage256r1, secp256r1privateKey);
    }

    public void sign(byte[] msg, PrivateKey privatekey) throws Exception {
        // Select the signature algorithm.
        Signature s = Signature.getInstance("SHA256withECDSA", "SunEC");
        s.initSign(privatekey);
        // Compute the signature.
        s.update(msg);

        signature = s.sign();
    }

    public void verify(byte[] msg, PublicKey publicKey) throws Exception {
        // Verify the signature.
        Signature s = Signature.getInstance("SHA256withECDSA", "SunEC");
        s.initVerify(publicKey);
        s.update(msg);

        if (!s.verify(signature)) {
            throw new RuntimeException("Invalid signature");
        }
    }

    @Benchmark
    public void secp256r1Sign() throws Exception {
        sign(message, secp256r1privateKey);
    }

    @Benchmark
    public void secp256r1Verify() throws  Exception {
        verify(signatureMessage256r1, secp256r1publicKey);
    }

    public static void main(String args[]) throws Exception{
        Options opt = new OptionsBuilder()
                .include(TestMethodr1.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                .build();
        new Runner(opt).run();
    }

}
