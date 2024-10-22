package com.bohdanbulakh.drawing_app.shapes.interfaces

import android.graphics.Canvas
import android.graphics.Paint
import com.bohdanbulakh.drawing_app.Coordinates

interface ShapeInterface {
    var start: Coordinates
    var end: Coordinates

    var gumStyle: Boolean

    val gumStylePaint: Paint
    val paint: Paint

    fun set(start: Coordinates, end: Coordinates) {
        this.start = start
        this.end = end
    }

    fun writeEnd(coords: Coordinates) {
        this.end = coords
    }

    fun show(canvas: Canvas)
    fun getShape(): ShapeInterface
}