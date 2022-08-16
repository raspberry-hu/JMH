package JMHtest;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHTest {
    public static void main(String args[]) throws Exception{
        Options opt = new OptionsBuilder()
                .include(TestMethodr1.class.getSimpleName())
                .include(TestMethodk1.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                .build();
        new Runner(opt).run();
    }
}
