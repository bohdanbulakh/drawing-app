package com.bohdanbulakh.drawing_app.shapes.interfaces

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.shapes.LineShape

interface LineInterface : ShapeInterface {
    override fun show(canvas: Canvas) {
        val paint = if (gumStyle) gumStylePaint else paint
        canvas.drawLine(coords.start.x, coords.start.y, coords.end.x, coords.end.y, paint)
    }

    override fun getShape(): ShapeInterface = LineShape()
}