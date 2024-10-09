package com.bohdanbulakh.drawing_app.editors

import com.bohdanbulakh.drawing_app.ArrayUtils
import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.shapes.Shape
import com.bohdanbulakh.drawing_app.shapes.RectShape

class RectEditor(val shapes: Array<Shape?>) : ShapeEditor(shapes) {
    override fun onLBdown(start: Coordinates) {
        val shape = RectShape()
        shape.set(start, start)
        ArrayUtils.addItem(shapes, shape)
    }
}