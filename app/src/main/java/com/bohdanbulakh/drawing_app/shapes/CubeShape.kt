package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.CoordinatesPair
import com.bohdanbulakh.drawing_app.shapes.interfaces.LineInterface
import com.bohdanbulakh.drawing_app.utils.CoordinateUtils

class CubeShape() : RectShape(), LineInterface {
    override fun show(canvas: Canvas) {
        val startOld = start
        val endOld = end

        val coordsNew = CoordinateUtils.calcCubeCoords(start, end)

        for (coordPair in arrayOf(
            CoordinatesPair(start, end),
            coordsNew,
        )) {
            start = coordPair.start
            end = coordPair.end
            super<RectShape>.show(canvas)
        }

        val lineCoords = CoordinateUtils.getCubeLinesCoords(
            startOld, endOld, coordsNew.start, coordsNew.end
        )
        for (coordPair in lineCoords) {
            start = coordPair.start
            end = coordPair.end
            super<LineInterface>.show(canvas)
        }

        start = startOld
        end = endOld
    }


    override fun getShape() = CubeShape()
}