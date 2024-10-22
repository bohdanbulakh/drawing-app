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

        fun calcCubeCoords(coords: CoordinatesPair): CoordinatesPair {
            val startX = coords.start.x + (coords.end.x - coords.start.x) / 3
            val startY = coords.start.y - (coords.end.y - coords.start.y) / 3

            val endX = coords.end.x + (coords.end.x - coords.start.x) / 3
            val endY = coords.end.y - (coords.end.y - coords.start.y) / 3

            return CoordinatesPair(
                Coordinates(startX, startY),
                Coordinates(endX, endY),
            )
        }

        fun getCubeLinesCoords(
            coords: CoordinatesPair, coords2: CoordinatesPair
        ): Array<CoordinatesPair> {
            return arrayOf(
                CoordinatesPair(coords.start, coords2.start),
                CoordinatesPair(coords.end, coords2.end),
                CoordinatesPair(
                    Coordinates(coords.start.x, coords.end.y),
                    Coordinates(coords2.start.x, coords2.end.y),
                ),
                CoordinatesPair(
                    Coordinates(coords.end.x, coords.start.y),
                    Coordinates(coords2.end.x, coords2.start.y),
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