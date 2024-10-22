package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.shapes.interfaces.LineInterface
import com.bohdanbulakh.drawing_app.utils.CoordinateUtils

class LineOOShape() : EllipseShape(), LineInterface {
    override fun show(canvas: Canvas) {
        super<LineInterface>.show(canvas)

        val coordsOld = coords

        for (coord in arrayOf(coords.start, coords.end)) {
            coords = CoordinateUtils.calcCircleCoords(coord, 25f)
            super<EllipseShape>.show(canvas)
        }

        coords = coordsOld
    }

    override fun getShape() = LineOOShape()
}