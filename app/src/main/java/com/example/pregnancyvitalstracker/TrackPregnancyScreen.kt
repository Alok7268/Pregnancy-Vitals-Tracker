package com.example.pregnancyvitalstracker


import androidx.compose.ui.tooling.preview.Preview
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TrackPregnancyScreen() {
    var vitalsLogs by remember { mutableStateOf(listOf<VitalsLog>()) }
    var showAddVitalsDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddVitalsDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Vitals")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("Track My Pregnancy", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))
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
//    Card(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth(),
//        elevation = 4.dp
//    )
    //    {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Blood Pressure: ${log.bloodPressureSys}/${log.bloodPressureDia} mmHg")
            Text("Heart Rate: ${log.heartRate} bpm")
            Text("Weight: ${log.weight} kg")
            Text("Baby Kicks: ${log.babyKicks} kicks")
            Text("Recorded at: ${log.timestamp}")
        }
    }

@Preview
@Composable
fun TrackPregnancyScreenPreview() {
    TrackPregnancyScreen()
}

@Preview
@Composable
fun VitalsListPreview() {
    val vitalsLogs = listOf(
        VitalsLog(120, 80, 70, 65, 5, "2023-10-27 10:00"),
        VitalsLog(130, 85, 75, 66, 6, "2023-10-27 11:00"),
        VitalsLog(125, 82, 72, 64, 7, "2023-10-27 12:00"),
    )
    VitalsList(vitalsLogs)
}
@Preview
@Composable
fun VitalsLogItemPreview(){
    VitalsLogItem(log = VitalsLog(120, 80, 70, 65, 5, "2023-10-27 10:00"))
}