package com.bohdanbulakh.drawing_app.editors

import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.shapes.Shape
import com.bohdanbulakh.drawing_app.shapes.EllipseShape
import com.bohdanbulakh.drawing_app.utils.ArrayUtils

class EllipseEditor(val shapes: Array<Shape?>) : ShapeEditor(shapes) {
    override fun onLBdown(start: Coordinates) {
        val shape = EllipseShape()
        shape.set(start, start)
        ArrayUtils.addItem(shapes, shape)
    }
}