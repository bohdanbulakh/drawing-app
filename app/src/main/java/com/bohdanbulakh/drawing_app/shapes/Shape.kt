package com.bohdanbulakh.drawing_app.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.DashPathEffect
import com.bohdanbulakh.drawing_app.Coordinates
import kotlin.apply

abstract class Shape {
    lateinit var start: Coordinates
    lateinit var end: Coordinates

    var gumStyle: Boolean = true
    val gumStylePaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 5f
        pathEffect = DashPathEffect(floatArrayOf(20f, 20f), 0f)
    }

    val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 5f
    }

    fun set(start: Coordinates) {
        this.start = start
    }

    fun set(start: Coordinates, end: Coordinates) {
        this.start = start
        this.end = end
    }

    fun writeEnd(coords: Coordinates) {
        this.end = coords
    }

    abstract fun show(canvas: Canvas)
}