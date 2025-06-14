package com.example.deliverytrackerlive.firebase

data class DeliveryBoyLocation(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis()
)
