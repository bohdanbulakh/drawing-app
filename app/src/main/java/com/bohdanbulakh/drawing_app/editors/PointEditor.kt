package com.bohdanbulakh.drawing_app.editors

import com.bohdanbulakh.drawing_app.ArrayUtils
import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.shapes.Shape
import com.bohdanbulakh.drawing_app.shapes.PointShape

class PointEditor(val shapes: Array<Shape?>) : ShapeEditor(shapes) {
    override fun onLBdown(start: Coordinates) {
        val shape = PointShape()
        shape.set(start)
        ArrayUtils.addItem(shapes, shape)
    }

    override fun onMouseMove(end: Coordinates) {
    }

    override fun onLBup(end: Coordinates) {
    }
}