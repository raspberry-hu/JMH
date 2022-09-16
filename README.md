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
