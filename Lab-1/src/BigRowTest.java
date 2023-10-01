import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BigRowTest {

    @org.junit.jupiter.api.Test
    void TestMethod1() {
        BigRow BR = new BigRow(BigInteger.valueOf(1), BigDecimal.valueOf(1));
        assertTrue(Math.abs(BR.Calculate(1).doubleValue() - Math.sin(1) / 1) < 0.1d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod2() {
        BigRow BR = new BigRow(BigInteger.valueOf(1), BigDecimal.valueOf(15));
        assertTrue(Math.abs(BR.Calculate(1).doubleValue() - Math.sin(15) / 15) < 0.1d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod3() {
        BigRow BR = new BigRow(BigInteger.valueOf(1), BigDecimal.valueOf(100));
        assertTrue(Math.abs(BR.Calculate(1).doubleValue() - Math.sin(100) / 100) < 0.1d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod4() {
        BigRow BR = new BigRow(BigInteger.valueOf(10), BigDecimal.valueOf(10));
        assertTrue(Math.abs(BR.Calculate(10).doubleValue() - Math.sin(10) / 10) < 1e-10d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod5() {
        BigRow BR = new BigRow(BigInteger.valueOf(10), BigDecimal.valueOf(1));
        assertTrue(Math.abs(BR.Calculate(10).doubleValue() - Math.sin(1) / 1) < 1e-10d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod6() {
        BigRow BR = new BigRow(BigInteger.valueOf(13), BigDecimal.valueOf(-3));
        assertTrue(Math.abs(BR.Calculate(13).doubleValue() - Math.sin(-3) / -3) < 1e-13d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod7() {
        BigRow BR = new BigRow(BigInteger.valueOf(13), BigDecimal.valueOf(0.1555555d));
        assertTrue(Math.abs(BR.Calculate(13).doubleValue() - Math.sin(0.1555555d) / 0.1555555d) < 1e-13d);
    }
}