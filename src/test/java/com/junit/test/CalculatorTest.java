package com.junit.test;

import com.junit.Calculator;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    public static void databaseSetup() {
        InfluxDB influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086");
        influxDB.deleteDatabase("testDB");
        influxDB.createDatabase("testDB");
        influxDB.close();
    }

    @BeforeEach
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Simple multiplication should work")
    public void testMultiply() {
        assertEquals(20, calculator.multiply(4,5),
                "Regular multiplication should work");
    }

    @RepeatedTest(5)
    @DisplayName("Ensure correct handling of zero")
    public void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(0,5), "Multiple with zero should be zero");
        assertEquals(0, calculator.multiply(5,0), "Multiple with zero should be zero");
    }
}
