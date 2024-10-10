package com.bohdanbulakh.drawing_app.editors

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.shapes.Shape
import com.bohdanbulakh.drawing_app.utils.ArrayUtils

abstract class ShapeEditor(val shapesArray: Array<Shape?>) : Editor() {
    abstract override fun onLBdown(start: Coordinates)

    override fun onLBup(end: Coordinates) {
        ArrayUtils.getLastItem(shapesArray)?.gumStyle = false
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