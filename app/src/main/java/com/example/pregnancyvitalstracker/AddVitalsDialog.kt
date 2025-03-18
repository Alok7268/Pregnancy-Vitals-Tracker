package com.example.pregnancyvitalstracker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.time.LocalDateTime

@Composable
fun AddVitalsDialog(onDismissRequest: () -> Unit, onVitalsAdded: (VitalsLog) -> Unit) {
    var sysBP by remember { mutableStateOf("") }
    var diaBP by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var babyKicks by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Add Vitals",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF592992)
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = sysBP,
                        onValueChange = { sysBP = it },
                        label = { Text("Sys BP") },
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = diaBP,
                        onValueChange = { diaBP = it },
                        label = { Text("Dia BP") },
                        modifier = Modifier.weight(1f)
                    )
                }


                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight ( in kg )") },
                    modifier = Modifier.fillMaxWidth()

                )

                OutlinedTextField(
                    value = babyKicks,
                    onValueChange = { babyKicks = it },
                    label = { Text("Baby Kicks") },
                    modifier = Modifier.fillMaxWidth()
                )


                Button(
                    onClick = {
                        // Validation (Basic, improve as needed)
                        if (sysBP.isNotEmpty() && diaBP.isNotEmpty() && weight.isNotEmpty() && babyKicks.isNotEmpty()) {
                            try {
                                // For heartRate, since it's not in your UI, using a default value for now:
                                val heartRateDefault = 0  // Replace with an actual value or make it optional
                                val newVitals = VitalsLog(
                                    bloodPressureSys = sysBP.toInt(),
                                    bloodPressureDia = diaBP.toInt(),
                                    heartRate = heartRateDefault,
                                    weight = weight.toInt(),
                                    babyKicks = babyKicks.toInt(),
                                    timestamp = LocalDateTime.now().toString()
                                )
                                onVitalsAdded(newVitals)
                            } catch (e: NumberFormatException) {
                                println("Error: Invalid number format")
                            }
                        } else {
                            println("Error: All fields must be filled.")
                        }

                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0))

                ) {
                    Text("Submit", color = Color.White)

                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAddVitalsDialog() {
    AddVitalsDialog(onDismissRequest = {}, onVitalsAdded = {})
}