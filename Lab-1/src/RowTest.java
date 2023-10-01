import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {

    @org.junit.jupiter.api.Test
    void TestMethod1() {
        Row R = new Row(1, 1d);
        assertTrue(Math.abs(R.Calculate() - Math.sin(1) / 1) < 0.1d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod2() {
        Row R = new Row(1, 4d);
        assertTrue(Math.abs(R.Calculate() - Math.sin(4) / 4) < 0.1d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod3() {
        Row R = new Row(4, 4d);
        assertTrue(Math.abs(R.Calculate() - Math.sin(4) / 4) < 1e-4d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod4() {
        Row R = new Row(6, 10d);
        assertTrue(Math.abs(R.Calculate() - Math.sin(10) / 10) < 1e-6d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod5() {
        Row R = new Row(5, -1d);
        assertTrue(Math.abs(R.Calculate() - Math.sin(-1) / -1) < 1e-5d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod6() {
        Row R = new Row(5, 0.1d);
        assertTrue(Math.abs(R.Calculate() - Math.sin(0.1d) / 0.1d) < 1e-5d);
    }
    @org.junit.jupiter.api.Test
    void TestMethod7() {
        Row R = new Row(6, 0.005d);
        assertTrue(Math.abs(R.Calculate() - Math.sin(0.005d) / 0.005d) < 1e-6d);
    }
}