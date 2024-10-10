package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas

class RectShape() : Shape() {

    override fun show(canvas: Canvas) {
        val paint = if (gumStyle) gumStylePaint else paint
        canvas.drawRect(start.x, start.y, end.x, end.y, paint)

    }
}