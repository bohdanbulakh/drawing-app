package com.bohdanbulakh.drawing_app.editors

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.ArrayUtils
import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.shapes.Shape

abstract class ShapeEditor(val shapesArray: Array<Shape?>) : Editor() {
    abstract override fun onLBdown(start: Coordinates)

    override fun onLBup(end: Coordinates) {
        val shape = ArrayUtils.getLastItem(shapesArray)
        shape?.gumStyle = false
        shape?.writeEnd(end)
    }

    override fun onMouseMove(end: Coordinates) {
        ArrayUtils.getLastItem(shapesArray)?.writeEnd(end)
    }

    override fun onPaint(canvas: Canvas) {
        for (shape in shapesArray) {
            shape?.show(canvas)
        }
    }
}