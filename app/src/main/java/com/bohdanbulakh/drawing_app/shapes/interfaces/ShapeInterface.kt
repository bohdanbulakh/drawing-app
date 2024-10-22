package com.bohdanbulakh.drawing_app.shapes.interfaces

import android.graphics.Canvas
import android.graphics.Paint
import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.CoordinatesPair

interface ShapeInterface {
    var coords: CoordinatesPair

    var gumStyle: Boolean

    val gumStylePaint: Paint
    val paint: Paint

    fun set(pair: CoordinatesPair) {
        coords = pair
    }

    fun writeEnd(end: Coordinates) {
        coords = CoordinatesPair(coords.start, end)
    }

    fun show(canvas: Canvas)
    fun getShape(): ShapeInterface
}