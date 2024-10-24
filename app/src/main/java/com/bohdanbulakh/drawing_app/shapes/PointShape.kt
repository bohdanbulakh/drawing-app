package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas

class PointShape() : Shape() {
    override fun show(canvas: Canvas) {
        canvas.drawPoint(coords.start.x, coords.start.y, paint)
    }

    override fun getShape() = PointShape()
}