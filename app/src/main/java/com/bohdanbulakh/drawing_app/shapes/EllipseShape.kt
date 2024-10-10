package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class EllipseShape() : Shape() {
    private val fillPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.rgb(0, 255, 255)
    }

    override fun show(canvas: Canvas) {
        if (gumStyle) {
            canvas.drawOval(start.x, start.y, end.x, end.y, gumStylePaint)
        } else {
            canvas.drawOval(start.x, start.y, end.x, end.y, fillPaint)
            canvas.drawOval(start.x, start.y, end.x, end.y, paint)
        }
    }
}