package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas

class EllipseShape() : Shape() {
    override fun show(canvas: Canvas) {
        val paint = if (gumStyle) gumStylePaint else paint
        canvas.drawOval(start.x, start.y, end.x, end.y, paint)
    }
}