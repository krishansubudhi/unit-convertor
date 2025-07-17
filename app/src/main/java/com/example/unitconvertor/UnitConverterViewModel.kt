package com.example.unitconvertor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

enum class UnitCategory {
    Length,
    Weight,
    Volume
}

data class Unit(val name: String, val conversionFactor: Double, val category: UnitCategory)

class UnitConverterViewModel : ViewModel() {

    private val _inputValue = mutableStateOf("")
    val inputValue: String
        get() = _inputValue.value

    private val _outputValue = mutableStateOf("")
    val outputValue: String
        get() = _outputValue.value

    private val _selectedCategory = mutableStateOf(UnitCategory.Length)
    val selectedCategory: UnitCategory
        get() = _selectedCategory.value

    private val _isCategoryExpanded = mutableStateOf(false)
    val isCategoryExpanded: Boolean
        get() = _isCategoryExpanded.value

    val allUnits = listOf(
        Unit("Meters", 1.0, UnitCategory.Length),
        Unit("Centimeters", 0.01, UnitCategory.Length),
        Unit("Feet", 0.3048, UnitCategory.Length),
        Unit("Miles", 1609.34, UnitCategory.Length),
        Unit("Kilometers", 1000.0, UnitCategory.Length),
        Unit("Inches", 0.0254, UnitCategory.Length),
        Unit("Yards", 0.9144, UnitCategory.Length),
        Unit("Kilograms", 1.0, UnitCategory.Weight),
        Unit("Grams", 0.001, UnitCategory.Weight),
        Unit("Pounds", 0.453592, UnitCategory.Weight),
        Unit("Ounces", 0.0283495, UnitCategory.Weight),
        Unit("Litres", 1.0, UnitCategory.Volume),
        Unit("Millilitres", 0.001, UnitCategory.Volume),
        Unit("Gallons", 3.78541, UnitCategory.Volume)
    )

    private val _inputUnit = mutableStateOf(allUnits.firstOrNull { it.category == UnitCategory.Length } ?: Unit("Meters", 1.0, UnitCategory.Length))
    val inputUnit: Unit
        get() = _inputUnit.value

    private val _outputUnit = mutableStateOf(allUnits.firstOrNull { it.category == UnitCategory.Length } ?: Unit("Meters", 1.0, UnitCategory.Length))
    val outputUnit: Unit
        get() = _outputUnit.value

    private val _isInputExpanded = mutableStateOf(false)
    val isInputExpanded: Boolean
        get() = _isInputExpanded.value

    private val _isOutputExpanded = mutableStateOf(false)
    val isOutputExpanded: Boolean
        get() = _isOutputExpanded.value

    fun setInputValue(value: String) {
        _inputValue.value = value
        if (_inputValue.value.toDoubleOrNull() == null && _inputValue.value.isNotEmpty()) {
            _outputValue.value = "Invalid Input"
        } else {
            convertUnits()
        }
    }

    fun setSelectedCategory(category: UnitCategory) {
        _selectedCategory.value = category
        _inputUnit.value = allUnits.firstOrNull { it.category == category } ?: Unit("Meters", 1.0, UnitCategory.Length)
        _outputUnit.value = allUnits.firstOrNull { it.category == category } ?: Unit("Meters", 1.0, UnitCategory.Length)
        _outputValue.value = ""
        _isCategoryExpanded.value = false
    }

    fun setInputUnit(unit: Unit) {
        _inputUnit.value = unit
        _isInputExpanded.value = false
        convertUnits()
    }

    fun setOutputUnit(unit: Unit) {
        _outputUnit.value = unit
        _isOutputExpanded.value = false
        convertUnits()
    }

    fun toggleInputExpanded() {
        _isInputExpanded.value = !_isInputExpanded.value
    }

    fun toggleOutputExpanded() {
        _isOutputExpanded.value = !_isOutputExpanded.value
    }

    fun toggleCategoryExpanded() {
        _isCategoryExpanded.value = !_isCategoryExpanded.value
    }

    fun convertUnits() {
        val inputDouble = _inputValue.value.toDoubleOrNull()
        if (inputDouble == null) {
            _outputValue.value = "Invalid Input"
            return
        }
        val valueInMeters = inputDouble * _inputUnit.value.conversionFactor
        val convertedValue = valueInMeters / _outputUnit.value.conversionFactor
        _outputValue.value = String.format("%.2f", convertedValue)
    }

    val unitsForCategory: List<Unit>
        get() = allUnits.filter { it.category == _selectedCategory.value }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UnitConverterViewModel()
            }
        }
    }
}
