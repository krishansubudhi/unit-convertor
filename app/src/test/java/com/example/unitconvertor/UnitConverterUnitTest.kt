package com.example.unitconvertor

import org.junit.Assert.assertEquals
import org.junit.Test

class UnitConverterUnitTest {

    @Test
    fun testMetersToCentimeters() {
        val inputUnit = Unit("Meters", 1.0, UnitCategory.Length)
        val outputUnit = Unit("Centimeters", 0.01, UnitCategory.Length)
        val result = convertUnits("1.0", inputUnit, outputUnit)
        assertEquals("100.00", result)
    }

    @Test
    fun testFeetToMeters() {
        val inputUnit = Unit("Feet", 0.3048, UnitCategory.Length)
        val outputUnit = Unit("Meters", 1.0, UnitCategory.Length)
        val result = convertUnits("10.0", inputUnit, outputUnit)
        assertEquals("3.05", result)
    }

    @Test
    fun testMilesToKilometers() {
        val inputUnit = Unit("Miles", 1609.34, UnitCategory.Length)
        val outputUnit = Unit("Kilometers", 1000.0, UnitCategory.Length)
        val result = convertUnits("5.0", inputUnit, outputUnit)
        assertEquals("8.05", result)
    }

    @Test
    fun testCentimetersToInches() {
        val inputUnit = Unit("Centimeters", 0.01, UnitCategory.Length)
        val outputUnit = Unit("Inches", 0.0254, UnitCategory.Length)
        val result = convertUnits("2.54", inputUnit, outputUnit)
        assertEquals("1.00", result)
    }

    @Test
    fun testYardsToFeet() {
        val inputUnit = Unit("Yards", 0.9144, UnitCategory.Length)
        val outputUnit = Unit("Feet", 0.3048, UnitCategory.Length)
        val result = convertUnits("1.0", inputUnit, outputUnit)
        assertEquals("3.00", result)
    }

    @Test
    fun testZeroInput() {
        val inputUnit = Unit("Meters", 1.0, UnitCategory.Length)
        val outputUnit = Unit("Feet", 0.3048, UnitCategory.Length)
        val result = convertUnits("0.0", inputUnit, outputUnit)
        assertEquals("0.00", result)
    }

    @Test
    fun testInvalidInput() {
        val inputUnit = Unit("Meters", 1.0, UnitCategory.Length)
        val outputUnit = Unit("Feet", 0.3048, UnitCategory.Length)
        val result = convertUnits("abc", inputUnit, outputUnit)
        assertEquals("0.00", result)
    }
}