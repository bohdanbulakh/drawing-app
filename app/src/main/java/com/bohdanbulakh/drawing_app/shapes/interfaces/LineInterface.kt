package com.bohdanbulakh.drawing_app.shapes.interfaces

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.shapes.LineShape

interface LineInterface : ShapeInterface {
    override fun show(canvas: Canvas) {
        val paint = if (gumStyle) gumStylePaint else paint
        canvas.drawLine(start.x, start.y, end.x, end.y, paint)
    }

    override fun getShape(): ShapeInterface = LineShape()
}