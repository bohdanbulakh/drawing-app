package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.utils.CoordinateUtils

open class RectShape() : Shape() {
    private lateinit var center: Coordinates

    override fun show(canvas: Canvas) {
        val paint = if (gumStyle) gumStylePaint else paint
        canvas.drawRect(start.x, start.y, end.x, end.y, paint)
    }

    override fun set(start: Coordinates, end: Coordinates) {
        super.set(start, end)
        this.center = start
    }

    override fun writeEnd(coords: Coordinates) {
        start = CoordinateUtils.calcStartFromCenter(center, coords)
        end = coords
    }

    override fun getShape() = RectShape()
}