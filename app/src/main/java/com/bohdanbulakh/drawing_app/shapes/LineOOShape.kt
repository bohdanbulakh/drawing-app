package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.shapes.interfaces.LineInterface
import com.bohdanbulakh.drawing_app.utils.CoordinateUtils

class LineOOShape() : EllipseShape(), LineInterface {
    override fun show(canvas: Canvas) {
        super<LineInterface>.show(canvas)

        val startOld = start
        val endOld = end

        for (coords in arrayOf(start, end)) {
            val coordPair = CoordinateUtils.calcCircleCoords(coords, 25f)

            start = coordPair.start
            end = coordPair.end
            super<EllipseShape>.show(canvas)
        }

        start = startOld
        end = endOld
    }

    override fun getShape() = LineOOShape()
}