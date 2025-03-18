package com.example.pregnancyvitalstracker


import androidx.compose.foundation.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TrackPregnancyScreen() {
    var vitalsLogs by remember { mutableStateOf(listOf<VitalsLog>()) }
    var showAddVitalsDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddVitalsDialog = true },
                containerColor = Color(0xFF9C27B0),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Vitals")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFF0F0F0))
        ) {
            Text(
                "Track My Pregnancy",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF592992)
                ),
                modifier = Modifier.padding(16.dp)
            )
            VitalsList(vitalsLogs = vitalsLogs)
        }

        if (showAddVitalsDialog) {
            AddVitalsDialog(
                onDismissRequest = { showAddVitalsDialog = false },
                onVitalsAdded = { newVitals ->
                    vitalsLogs = vitalsLogs + newVitals
                    showAddVitalsDialog = false
                }
            )
        }
    }
}

@Composable
fun VitalsList(vitalsLogs: List<VitalsLog>) {
    LazyColumn {
        items(vitalsLogs) { log ->
            VitalsLogItem(log)
        }
    }
}

@Composable
fun VitalsLogItem(log: VitalsLog) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF9F0FF),
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column {
//                    VitalsRow(icon = Icons.Filled.MonitorHeart, text = "${log.heartRate} bpm")
//                    VitalsRow(icon = Icons.Filled.Scale, text = "${log.weight} kg")

                    VitalsRow(icon = painterResource(id = R.drawable.heart), text = "${log.heartRate} bpm")
                    VitalsRow(icon = painterResource(id = R.drawable.scale), text = "${log.weight} kg")
                }
                Column {
                    VitalsRow(icon = painterResource(id = R.drawable.blood_pressure), text = "${log.bloodPressureSys}/${log.bloodPressureDia} mmHg")
                    VitalsRow(icon = painterResource(id = R.drawable.new_born), text = "${log.babyKicks} kicks")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF9C27B0).copy(alpha = 0.8f))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Mon, 13 Jan 2025 03:45 pm",
                    color = Color.White,
                    style = TextStyle(fontSize = 14.sp)
                )
            }
        }
    }
}

@Composable
fun VitalsRow(icon: Painter, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp) ,
            alpha = 0.7f
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = TextStyle(fontSize = 16.sp, color = Color.Black))
    }
}



@Preview(showBackground = true)
@Composable
fun TrackPregnancyScreenPreview() {
    TrackPregnancyScreen()
}

@Preview(showBackground = true)
@Composable
fun VitalsListPreview() {
    val vitalsLogs = listOf(
        VitalsLog(120, 80, 90, 68, 15, "2025-01-13 15:45"),
        VitalsLog(129, 86, 87, 75, 9, "2025-01-12 10:22"),
    )
    VitalsList(vitalsLogs)
}

@Preview(showBackground = true)
@Composable
fun VitalsLogItemPreview() {
    VitalsLogItem(log = VitalsLog(120, 80, 90, 68, 15, "2025-01-13 15:45"))
}