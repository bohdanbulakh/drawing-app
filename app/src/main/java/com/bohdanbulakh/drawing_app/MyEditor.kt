package com.bohdanbulakh.drawing_app

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.shapes.interfaces.ShapeInterface
import com.bohdanbulakh.drawing_app.utils.ArrayUtils

class MyEditor {
    private val arraySize = 103
    private val shapes = arrayOfNulls<ShapeInterface>(arraySize)
    private lateinit var currentShape: ShapeInterface

    fun start(shape: ShapeInterface) {
        currentShape = shape
    }

    fun onPaint(canvas: Canvas) {
        for (shape in shapes) {
            shape?.show(canvas)
        }
    }

    fun onLBdown(start: Coordinates) {
        val shape = currentShape.getShape()
        shape.set(start, start)
        ArrayUtils.addItem(shapes, shape)
    }

    fun onMouseMove(end: Coordinates) {
        ArrayUtils.getLastItem(shapes)?.writeEnd(end)
    }

    fun onLBup() {
        ArrayUtils.getLastItem(shapes)?.gumStyle = false
    }
}