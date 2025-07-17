package com.example.unitconvertor

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import com.example.unitconvertor.ui.theme.UnitConvertorTheme

@RunWith(AndroidJUnit4::class)
class UnitConverterIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun metersToFeetConversionTest() {
        composeTestRule.setContent {
            UnitConvertorTheme {
                UnitConverterScreen()
            }
        }

        // Input value
        composeTestRule.onNodeWithText("Enter Value").performTextInput("10")

        // Select Feet as output unit
        composeTestRule.onNodeWithTag("output_unit_dropdown").performClick()
        composeTestRule.onNodeWithTag("output_unit_option_feet").performClick()

        // Click Convert button
        composeTestRule.onNodeWithText("Convert").performClick()

        // Verify result
        composeTestRule.onNodeWithText("Result: 32.81").assertExists()
    }

    @Test
    fun feetToMetersConversionTest() {
        composeTestRule.setContent {
            UnitConvertorTheme {
                UnitConverterScreen()
            }
        }

        // Input value
        composeTestRule.onNodeWithText("Enter Value").performTextInput("10")

        // Select Feet as input unit
        composeTestRule.onNodeWithTag("input_unit_dropdown").performClick()
        composeTestRule.onNodeWithTag("input_unit_option_feet").performClick()

        // Select Meters as output unit
        composeTestRule.onNodeWithTag("output_unit_dropdown").performClick()
        composeTestRule.onNodeWithTag("output_unit_option_meters").performClick()

        // Click Convert button
        composeTestRule.onNodeWithText("Convert").performClick()

        // Verify result
        composeTestRule.onNodeWithText("Result: 3.05").assertExists()
    }

    @Test
    fun centimetersToInchesConversionTest() {
        composeTestRule.setContent {
            UnitConvertorTheme {
                UnitConverterScreen()
            }
        }

        // Input value
        composeTestRule.onNodeWithText("Enter Value").performTextInput("25.4")

        // Select Centimeters as input unit
        composeTestRule.onNodeWithTag("input_unit_dropdown").performClick()
        composeTestRule.onNodeWithTag("input_unit_option_centimeters").performClick()

        // Select Inches as output unit
        composeTestRule.onNodeWithTag("output_unit_dropdown").performClick()
        composeTestRule.onNodeWithTag("output_unit_option_inches").performClick()

        // Click Convert button
        composeTestRule.onNodeWithText("Convert").performClick()

        // Verify result
        composeTestRule.onNodeWithText("Result: 10.00").assertExists()
    }

    @Test
    fun inchesToCentimetersConversionTest() {
        composeTestRule.setContent {
            UnitConvertorTheme {
                UnitConverterScreen()
            }
        }

        // Input value
        composeTestRule.onNodeWithText("Enter Value").performTextInput("10")

        // Select Inches as input unit
        composeTestRule.onNodeWithTag("input_unit_dropdown").performClick()
        composeTestRule.onNodeWithTag("input_unit_option_inches").performClick()

        // Select Centimeters as output unit
        composeTestRule.onNodeWithTag("output_unit_dropdown").performClick()
        composeTestRule.onNodeWithTag("output_unit_option_centimeters").performClick()

        // Click Convert button
        composeTestRule.onNodeWithText("Convert").performClick()

        // Verify result
        composeTestRule.onNodeWithText("Result: 25.40").assertExists()
    }

    @Test
    fun zeroInputTest() {
        composeTestRule.setContent {
            UnitConvertorTheme {
                UnitConverterScreen()
            }
        }

        // Input value
        composeTestRule.onNodeWithText("Enter Value").performTextInput("0")

        // Select Feet as output unit
        composeTestRule.onNodeWithTag("output_unit_dropdown").performClick()
        composeTestRule.onNodeWithTag("output_unit_option_feet").performClick()

        // Click Convert button
        composeTestRule.onNodeWithText("Convert").performClick()

        // Verify result
        composeTestRule.onNodeWithText("Result: 0.00").assertExists()
    }

    @Test
    fun decimalInputTest() {
        composeTestRule.setContent {
            UnitConvertorTheme {
                UnitConverterScreen()
            }
        }

        // Input value
        composeTestRule.onNodeWithText("Enter Value").performTextInput("12.34")

        // Select Feet as output unit
        composeTestRule.onNodeWithTag("output_unit_dropdown").performClick()
        composeTestRule.onNodeWithTag("output_unit_option_feet").performClick()

        // Click Convert button
        composeTestRule.onNodeWithText("Convert").performClick()

        // Verify result
        composeTestRule.onNodeWithText("Result: 40.49").assertExists()
    }
}