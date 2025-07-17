package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      UnitConvertorTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          UnitConverterScreen(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun UnitConverterScreen(modifier: Modifier = Modifier, unitConverterViewModel: UnitConverterViewModel = viewModel(factory = UnitConverterViewModel.Factory)) {

  Column(
    modifier = modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Unit Converter")
    Spacer(modifier = Modifier.height(16.dp))

    // Category Selection
    Box {
      Button(onClick = { unitConverterViewModel.toggleCategoryExpanded() }) {
        Text(text = unitConverterViewModel.selectedCategory.name)
      }
      DropdownMenu(
        expanded = unitConverterViewModel.isCategoryExpanded,
        onDismissRequest = { unitConverterViewModel.toggleCategoryExpanded() }
      ) {
        UnitCategory.entries.forEach { category ->
          DropdownMenuItem(
            text = { Text(category.name) },
            onClick = {
              unitConverterViewModel.setSelectedCategory(category)
            }
          )
        }
      }
    }
    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
      value = unitConverterViewModel.inputValue,
      onValueChange = { unitConverterViewModel.setInputValue(it) },
      label = { Text("Enter Value") }
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row {
      Box {
        Button(
          onClick = { unitConverterViewModel.toggleInputExpanded() },
          modifier = Modifier.testTag("input_unit_dropdown")
        ) {
          Text(text = unitConverterViewModel.inputUnit.name)
        }
        DropdownMenu(
          expanded = unitConverterViewModel.isInputExpanded,
          onDismissRequest = { unitConverterViewModel.toggleInputExpanded() }
        ) {
          unitConverterViewModel.unitsForCategory.forEach { unit ->
            DropdownMenuItem(
              text = { Text(unit.name) },
              onClick = {
                unitConverterViewModel.setInputUnit(unit)
              },
              modifier = Modifier.testTag("input_unit_option_${unit.name.lowercase()}")
            )
          }
        }
      }
      Spacer(modifier = Modifier.width(16.dp))
      Box {
        Button(
          onClick = { unitConverterViewModel.toggleOutputExpanded() },
          modifier = Modifier.testTag("output_unit_dropdown")
        ) {
          Text(text = unitConverterViewModel.outputUnit.name)
        }
        DropdownMenu(
          expanded = unitConverterViewModel.isOutputExpanded,
          onDismissRequest = { unitConverterViewModel.toggleOutputExpanded() }
        ) {
          unitConverterViewModel.unitsForCategory.forEach { unit ->
            DropdownMenuItem(
              text = { Text(unit.name) },
              onClick = {
                unitConverterViewModel.setOutputUnit(unit)
              },
              modifier = Modifier.testTag("output_unit_option_${unit.name.lowercase()}")
            )
          }
        }
      }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = { unitConverterViewModel.convertUnits() }) {
      Text(text = "Convert")
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Result: ${unitConverterViewModel.outputValue}")
  }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterScreenPreview() {
  UnitConvertorTheme {
    UnitConverterScreen()
  }
}
