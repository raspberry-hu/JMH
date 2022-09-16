# [任务一](https://github.com/openjdk/jdk/pull/9792)
# 任务二
## JMH

```
Benchmark                              Mode  Cnt     Score    Error  Units
JMHtest.TestMethodk1.secp256k1Sign    thrpt    5  1420.482 ± 67.404  ops/s
JMHtest.TestMethodk1.secp256k1Verify  thrpt    5   877.615 ± 14.036  ops/s
JMHtest.TestMethodr1.secp256r1Sign    thrpt    5  1898.284 ± 49.139  ops/s
JMHtest.TestMethodr1.secp256r1Verify  thrpt    5   913.742 ± 77.081  ops/s
```

JMH demo在JMHtest中

JFR demo在JFRtest中

## 总火焰图
![flamegraph](https://user-images.githubusercontent.com/76903172/184899800-74a249e7-e193-4894-9e8b-0baae1626de6.png)
## secp256r1火焰图（idea Async Profiler生成）
![flamegraph1](https://user-images.githubusercontent.com/76903172/184899767-33e19702-ac7d-4b9a-b73e-626e13cc1430.png)
## secp256k1火焰图（idea Async Profiler生成）
## ![flamegraph3](https://user-images.githubusercontent.com/76903172/184900746-3258a76f-babd-4e3c-85c4-8d5e7836e675.png)

# 任务三
首先需要对原本不支持SM2曲线的OpenJDK进行适配工作首先是根据类似的曲线（P256和O256）找到对应的修改点，首先是在src/java.base/share/classes/sun/security/util/CurveDB.java中添加SM2曲线的[推荐参数](https://www.oscca.gov.cn/sca/xxgk/2010-12/17/1002386/files/b965ce832cc34bc191cb1cde446b860d.pdf) 之后进行编译，发现OpenJDK在新版本中由Java纯原生的方式来实现ECC，所以需要对报错的地方进行修改，单纯添加曲线参数不能完全添加曲线，因此在对应处添加其他参数make/jdk/src/classes/build/tools/intpoly/FieldGen.java等文件中，详情可见

并在研读过程中发现[ECDSA](https://github.com/openjdk/jdk/blob/a41b12f430b8d6ebbb634c0a6a077ed13c68bcb7/src/jdk.crypto.ec/share/classes/sun/security/ec/ECDSAOperations.java#L258)的相关验证问题，询问导师后了解到，该组件基本用户TLS中，在TLS中已经做了相关校验。

之后参考[BC库](https://www.cnblogs.com/dashou/p/14656458.html)中的相关实现，使用添加到JDK本身中的SM2来生成keypair，并使用BC库来进行验证，并使用JMH和JFR对性能进行测试和分析：
