package com.bohdanbulakh.drawing_app.utils

import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.CoordinatesPair

class CoordinateUtils {
    companion object {
        fun calcStartFromCenter(center: Coordinates, end: Coordinates): Coordinates {
            val startX = 2 * center.x - end.x
            val startY = 2 * center.y - end.y
            return Coordinates(startX, startY)
        }

        fun calcCubeCoords(start: Coordinates, end: Coordinates): CoordinatesPair {
            val startX = start.x + (end.x - start.x) / 3
            val startY = start.y - (end.y - start.y) / 3

            val endX = end.x + (end.x - start.x) / 3
            val endY = end.y - (end.y - start.y) / 3

            return CoordinatesPair(
                Coordinates(startX, startY),
                Coordinates(endX, endY),
            )
        }

        fun getCubeLinesCoords(
            start: Coordinates, end: Coordinates, start2: Coordinates, end2: Coordinates
        ): Array<CoordinatesPair> {
            return arrayOf(
                CoordinatesPair(start, start2),
                CoordinatesPair(end, end2),
                CoordinatesPair(
                    Coordinates(start.x, end.y),
                    Coordinates(start2.x, end2.y),
                ),
                CoordinatesPair(
                    Coordinates(end.x, start.y),
                    Coordinates(end2.x, start2.y),
                ),
            )
        }


        fun calcCircleCoords(center: Coordinates, radius: Float): CoordinatesPair {
            val startX = center.x - radius
            val startY = center.y + radius

            val endX = center.x + radius
            val endY = center.y - radius

            return CoordinatesPair(
                Coordinates(startX, startY),
                Coordinates(endX, endY),
            )
        }
    }
}