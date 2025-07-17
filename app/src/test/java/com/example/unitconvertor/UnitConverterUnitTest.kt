package com.example.unitconvertor

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UnitConverterUnitTest {

    private lateinit var viewModel: UnitConverterViewModel

    @Before
    fun setup() {
        viewModel = UnitConverterViewModel()
    }

    @Test
    fun testMetersToCentimeters() {
        viewModel.setInputValue("1.0")
        viewModel.setInputUnit(Unit("Meters", 1.0, UnitCategory.Length))
        viewModel.setOutputUnit(Unit("Centimeters", 0.01, UnitCategory.Length))
        viewModel.convertUnits()
        assertEquals("100.00", viewModel.outputValue)
    }

    @Test
    fun testFeetToMeters() {
        viewModel.setInputValue("10.0")
        viewModel.setInputUnit(Unit("Feet", 0.3048, UnitCategory.Length))
        viewModel.setOutputUnit(Unit("Meters", 1.0, UnitCategory.Length))
        viewModel.convertUnits()
        assertEquals("3.05", viewModel.outputValue)
    }

    @Test
    fun testMilesToKilometers() {
        viewModel.setInputValue("5.0")
        viewModel.setInputUnit(Unit("Miles", 1609.34, UnitCategory.Length))
        viewModel.setOutputUnit(Unit("Kilometers", 1000.0, UnitCategory.Length))
        viewModel.convertUnits()
        assertEquals("8.05", viewModel.outputValue)
    }

    @Test
    fun testCentimetersToInches() {
        viewModel.setInputValue("2.54")
        viewModel.setInputUnit(Unit("Centimeters", 0.01, UnitCategory.Length))
        viewModel.setOutputUnit(Unit("Inches", 0.0254, UnitCategory.Length))
        viewModel.convertUnits()
        assertEquals("1.00", viewModel.outputValue)
    }

    @Test
    fun testYardsToFeet() {
        viewModel.setInputValue("1.0")
        viewModel.setInputUnit(Unit("Yards", 0.9144, UnitCategory.Length))
        viewModel.setOutputUnit(Unit("Feet", 0.3048, UnitCategory.Length))
        viewModel.convertUnits()
        assertEquals("3.00", viewModel.outputValue)
    }

    @Test
    fun testZeroInput() {
        viewModel.setInputValue("0.0")
        viewModel.setInputUnit(Unit("Meters", 1.0, UnitCategory.Length))
        viewModel.setOutputUnit(Unit("Feet", 0.3048, UnitCategory.Length))
        viewModel.convertUnits()
        assertEquals("0.00", viewModel.outputValue)
    }

    @Test
    fun testInvalidInput() {
        viewModel.setInputValue("abc")
        viewModel.setInputUnit(Unit("Meters", 1.0, UnitCategory.Length))
        viewModel.setOutputUnit(Unit("Feet", 0.3048, UnitCategory.Length))
        viewModel.convertUnits()
        assertEquals("Invalid Input", viewModel.outputValue)
    }
}