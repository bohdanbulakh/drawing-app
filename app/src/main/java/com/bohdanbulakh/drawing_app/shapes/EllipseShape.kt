package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

open class EllipseShape() : Shape() {
    val fillPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.rgb(0, 255, 255)
    }

    override fun show(canvas: Canvas) {
        if (gumStyle) {
            canvas.drawOval(coords.start.x, coords.start.y, coords.end.x, coords.end.y, gumStylePaint)
        } else {
            canvas.drawOval(coords.start.x, coords.start.y, coords.end.x, coords.end.y, fillPaint)
            canvas.drawOval(coords.start.x, coords.start.y, coords.end.x, coords.end.y, paint)
        }
    }

    override fun getShape() = EllipseShape()
}