package com.bohdanbulakh.drawing_app.utils

import com.bohdanbulakh.drawing_app.Coordinates

class CoordinateUtils {
    companion object {
        fun calculateStart(center: Coordinates, end: Coordinates): Coordinates {
            val startX = 2 * center.x - end.x
            val startY = 2 * center.y - end.y
            return Coordinates(startX, startY)
        }
    }
}