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
            shape = MaterialTheme.shapes.medium // Optional: Adds a rounded corner to the card
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp), // Adjust spacing as needed
                horizontalAlignment = Alignment.CenterHorizontally // Center the content
            ) {

                Text(
                    text = "Add Vitals",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF592992) // Set the color to match the image
                    ),
                    modifier = Modifier.align(Alignment.Start) // Align to the left
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between Sys BP and Dia BP fields
                ) {
                    OutlinedTextField(
                        value = sysBP,
                        onValueChange = { sysBP = it },
                        label = { Text("Sys BP") },
                        modifier = Modifier.weight(1f)  // Take equal width
                    )

                    OutlinedTextField(
                        value = diaBP,
                        onValueChange = { diaBP = it },
                        label = { Text("Dia BP") },
                        modifier = Modifier.weight(1f)  // Take equal width
                    )
                }


                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight ( in kg )") },
                    modifier = Modifier.fillMaxWidth()   // Fill width

                )

                OutlinedTextField(
                    value = babyKicks,
                    onValueChange = { babyKicks = it },
                    label = { Text("Baby Kicks") },
                    modifier = Modifier.fillMaxWidth()    // Fill width
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
                                // Handle error
                                println("Error: Invalid number format")
                                // You should display an error message to the user here!
                            }
                        } else {
                            // Handle validation error
                            println("Error: All fields must be filled.")
                            // You should display an error message to the user here!
                        }

                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(200.dp),  // Adjust width as needed
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0))
                    //fillMaxWidth() // Make the button fill the width

                ) {
                    Text("Submit", color = Color.White) // Set text color to white for better visibility

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