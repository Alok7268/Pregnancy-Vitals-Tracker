package com.example.pregnancyvitalstracker

data class VitalsLog(
    val bloodPressureSys: Int,
    val bloodPressureDia: Int,
    val heartRate: Int,
    val weight: Int,
    val babyKicks: Int,
    val timestamp: String
)