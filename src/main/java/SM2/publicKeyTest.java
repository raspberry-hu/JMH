package SM2;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.security.*;
import java.security.spec.ECGenParameterSpec;


@State(Scope.Thread)
public class publicKeyTest {
    KeyPair keyPair;

    @Setup
    public void prepare() throws Exception {
        ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "SunEC");
        kpg.initialize(sm2Spec);
        keyPair = kpg.genKeyPair();
    }

    @Benchmark
    public void sm2p256v1() throws Exception {
        SM2.getHexPublicKeyUncompressed(keyPair.getPublic());
    }

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(publicKeyTest.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                .build();

        new Runner(opt).run();
    }
}