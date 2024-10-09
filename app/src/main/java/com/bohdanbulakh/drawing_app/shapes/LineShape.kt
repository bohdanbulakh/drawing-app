package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas

class LineShape() : Shape() {
    override fun show(canvas: Canvas) {
        val paint = if (gumStyle) gumStylePaint else paint
        canvas.drawLine(start.x, start.y, end.x, end.y, paint)
    }
}