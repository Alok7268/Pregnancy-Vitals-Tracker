package com.example.pregnancyvitalstracker

import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.time.LocalDateTime

@Composable
fun AddVitalsDialog(onDismissRequest: () -> Unit, onVitalsAdded: (VitalsLog) -> Unit) {
    var bloodPressureSys by remember { mutableStateOf("") }
    var bloodPressureDia by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var babyKicks by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Add Vitals", style = MaterialTheme.typography.titleLarge)

                OutlinedTextField(
                    value = bloodPressureSys,
                    onValueChange = { bloodPressureSys = it },
                    label = { Text("Systolic Blood Pressure") }
                )
                OutlinedTextField(
                    value = bloodPressureDia,
                    onValueChange = { bloodPressureDia = it },
                    label = { Text("Diastolic Blood Pressure") }
                )
                OutlinedTextField(
                    value = heartRate,
                    onValueChange = { heartRate = it },
                    label = { Text("Heart Rate") }
                )
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight") }
                )
                OutlinedTextField(
                    value = babyKicks,
                    onValueChange = { babyKicks = it },
                    label = { Text("Baby Kicks") }
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel")
                    }
                    Button(onClick = {
                        // Validation (Basic, improve as needed)
                        if (bloodPressureSys.isNotEmpty() && bloodPressureDia.isNotEmpty() && heartRate.isNotEmpty() && weight.isNotEmpty() && babyKicks.isNotEmpty()) {
                            try {
                                val newVitals = VitalsLog(
                                    bloodPressureSys = bloodPressureSys.toInt(),
                                    bloodPressureDia = bloodPressureDia.toInt(),
                                    heartRate = heartRate.toInt(),
                                    weight = weight.toInt(),
                                    babyKicks = babyKicks.toInt(),
                                    timestamp = LocalDateTime.now()
                                        .toString()  // Or format as needed
                                )
                                onVitalsAdded(newVitals)
                            } catch (e: NumberFormatException) {
                                //Handle error
                                println("Error: Invalid number format")
                            }

                        } else {
                            // Handle validation error, show a Toast or something
                        }
                    }) {
                        Text("Add")
                    }
                }
            }
        }
    }
}