package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.shapes.interfaces.LineInterface
import com.bohdanbulakh.drawing_app.utils.CoordinateUtils

class CubeShape() : RectShape(), LineInterface {
    override fun show(canvas: Canvas) {
        val coordsOld = coords

        val coordsNew = CoordinateUtils.calcCubeCoords(coords)
        for (coordPair in arrayOf(
            coords,
            coordsNew,
        )) {
            coords = coordPair
            super<RectShape>.show(canvas)
        }

        val lineCoords = CoordinateUtils.getCubeLinesCoords(coordsOld, coordsNew)
        for (coordPair in lineCoords) {
            coords = coordPair
            super<LineInterface>.show(canvas)
        }

        coords = coordsOld
    }


    override fun getShape() = CubeShape()
}