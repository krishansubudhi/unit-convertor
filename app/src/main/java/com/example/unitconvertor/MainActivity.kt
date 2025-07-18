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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding // Import imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions // Import KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions // Import KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType // Import KeyboardType
import androidx.compose.ui.text.input.ImeAction // Import ImeAction
import androidx.compose.ui.platform.LocalSoftwareKeyboardController // Import LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController // Import SoftwareKeyboardController

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
  val keyboardController = LocalSoftwareKeyboardController.current // Obtain KeyboardController

  Column(
    modifier = modifier.fillMaxSize().padding(32.dp).imePadding(), // Added imePadding()
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Unit Converter",
      style = MaterialTheme.typography.headlineLarge,
      modifier = Modifier.padding(bottom = 24.dp)
    )

    // Category Selection
    Box(modifier = Modifier.fillMaxWidth()) {
      FilledTonalButton(
        onClick = { unitConverterViewModel.toggleCategoryExpanded() },
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(text = unitConverterViewModel.selectedCategory.name, fontSize = 20.sp)
      }
      DropdownMenu(
        expanded = unitConverterViewModel.isCategoryExpanded,
        onDismissRequest = { unitConverterViewModel.toggleCategoryExpanded() },
        modifier = Modifier.fillMaxWidth()
      ) {
        UnitCategory.entries.forEach { category ->
          DropdownMenuItem(
            text = { Text(category.name, fontSize = 20.sp) },
            onClick = {
              unitConverterViewModel.setSelectedCategory(category)
            }
          )
        }
      }
    }
    Spacer(modifier = Modifier.height(24.dp))

    OutlinedTextField(
      value = unitConverterViewModel.inputValue,
      onValueChange = { unitConverterViewModel.setInputValue(it) },
      label = { Text("Enter Value", fontSize = 20.sp) },
      textStyle = TextStyle(fontSize = 20.sp),
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done), // Set numeric keyboard and IME action
      keyboardActions = KeyboardActions(onDone = { // Handle enter key
        unitConverterViewModel.convertUnits()
        keyboardController?.hide()
      })
    )
    Spacer(modifier = Modifier.height(24.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      Box(modifier = Modifier.weight(1f)) {
        FilledTonalButton(
          onClick = { unitConverterViewModel.toggleInputExpanded() },
          modifier = Modifier.fillMaxWidth().testTag("input_unit_dropdown")
        ) {
          Text(text = unitConverterViewModel.inputUnit.name, fontSize = 20.sp)
        }
        DropdownMenu(
          expanded = unitConverterViewModel.isInputExpanded,
          onDismissRequest = { unitConverterViewModel.toggleInputExpanded() },
          modifier = Modifier.fillMaxWidth()
        ) {
          unitConverterViewModel.unitsForCategory.forEach { unit ->
            DropdownMenuItem(
              text = { Text(unit.name, fontSize = 20.sp) },
              onClick = {
                unitConverterViewModel.setInputUnit(unit)
              },
              modifier = Modifier.testTag("input_unit_option_${unit.name.lowercase()}")
            )
          }
        }
      }
      Spacer(modifier = Modifier.width(16.dp))
      Button(
        onClick = { unitConverterViewModel.swapUnits() },
        modifier = Modifier.testTag("swap_units_button")
      ) {
        Icon(
          imageVector = Icons.Default.SwapHoriz,
          contentDescription = "Swap Units",
          modifier = Modifier.size(24.dp)
        )
      }
      Spacer(modifier = Modifier.width(16.dp))
      Box(modifier = Modifier.weight(1f)) {
        FilledTonalButton(
          onClick = { unitConverterViewModel.toggleOutputExpanded() },
          modifier = Modifier.fillMaxWidth().testTag("output_unit_dropdown")
        ) {
          Text(text = unitConverterViewModel.outputUnit.name, fontSize = 20.sp)
        }
        DropdownMenu(
          expanded = unitConverterViewModel.isOutputExpanded,
          onDismissRequest = { unitConverterViewModel.toggleOutputExpanded() },
          modifier = Modifier.fillMaxWidth()
        ) {
          unitConverterViewModel.unitsForCategory.forEach { unit ->
            DropdownMenuItem(
              text = { Text(unit.name, fontSize = 20.sp) },
              onClick = {
                unitConverterViewModel.setOutputUnit(unit)
              },
              modifier = Modifier.testTag("output_unit_option_${unit.name.lowercase()}")
            )
          }
        }
      }
    }
    Spacer(modifier = Modifier.height(24.dp))
    ElevatedButton(
      onClick = { unitConverterViewModel.convertUnits() },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = "Convert", fontSize = 24.sp)
    }
    Spacer(modifier = Modifier.height(24.dp))
    Text(
      text = "Result: ${unitConverterViewModel.outputValue}",
      style = MaterialTheme.typography.headlineMedium
    )
  }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterScreenPreview() {
  UnitConvertorTheme {
    UnitConverterScreen()
  }
}
