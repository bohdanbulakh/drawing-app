package com.bohdanbulakh.drawing_app.editors

import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.shapes.Shape
import com.bohdanbulakh.drawing_app.shapes.PointShape
import com.bohdanbulakh.drawing_app.utils.ArrayUtils

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