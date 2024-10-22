package com.bohdanbulakh.drawing_app

data class Coordinates(
    val x: Float,
    val y: Float,
)

data class CoordinatesPair(
    val start: Coordinates,
    val end: Coordinates,
)