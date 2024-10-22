package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import com.bohdanbulakh.drawing_app.CoordinatesPair
import com.bohdanbulakh.drawing_app.shapes.interfaces.ShapeInterface

abstract class Shape : ShapeInterface {
    override lateinit var coords: CoordinatesPair

    override var gumStyle: Boolean = true
    override val gumStylePaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.MAGENTA
        strokeWidth = 5f
        pathEffect = DashPathEffect(floatArrayOf(20f, 20f), 0f)
    }

    override val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 5f
    }
}