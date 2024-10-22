package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.CoordinatesPair
import com.bohdanbulakh.drawing_app.utils.CoordinateUtils

open class RectShape() : Shape() {
    private lateinit var center: Coordinates

    override fun show(canvas: Canvas) {
        val paint = if (gumStyle) gumStylePaint else paint
        canvas.drawRect(coords.start.x, coords.start.y, coords.end.x, coords.end.y, paint)
    }

    override fun set(pair: CoordinatesPair) {
        super.set(pair)
        this.center = coords.start
    }

    override fun writeEnd(end: Coordinates) {
        coords = CoordinatesPair(CoordinateUtils.calcStartFromCenter(center, end), end)
    }

    override fun getShape() = RectShape()
}