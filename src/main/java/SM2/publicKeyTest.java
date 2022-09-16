package SM2;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.util.encoders.Hex;

@State(Scope.Thread)
public class publicKeyTest {
    KeyPair keyPair;
    ECGenParameterSpec sm2Spec;
    KeyPairGenerator kpg;
    ECGenParameterSpec sm2Spec_bc;
    KeyPairGenerator kpg_bc;
    @Setup
    public void prepare() throws Exception {
        ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "SunEC");
        kpg.initialize(sm2Spec);
        keyPair = kpg.genKeyPair();

        sm2Spec = new ECGenParameterSpec("sm2p256v1");
        kpg = KeyPairGenerator.getInstance("EC", "SunEC");
        sm2Spec_bc = new ECGenParameterSpec("sm2p256v1");
        kpg_bc = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
    }

   @Benchmark
   public void sm2p256v1() throws Exception {
       SM2.convertPublicKey(keyPair.getPublic());
   }

    @Benchmark
    public void BCSm2p256v1() throws Exception {
        kpg_bc.initialize(sm2Spec_bc);
        KeyPair keyPair = kpg_bc.generateKeyPair();

        BCECPrivateKey privateKey = (BCECPrivateKey) keyPair.getPrivate();
        BCECPublicKey publicKey = (BCECPublicKey) keyPair.getPublic();
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
