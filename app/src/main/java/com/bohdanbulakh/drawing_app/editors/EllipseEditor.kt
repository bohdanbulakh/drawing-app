package com.bohdanbulakh.drawing_app.editors

import com.bohdanbulakh.drawing_app.ArrayUtils
import com.bohdanbulakh.drawing_app.CoordinateUtils
import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.shapes.Shape
import com.bohdanbulakh.drawing_app.shapes.EllipseShape

class EllipseEditor(val shapes: Array<Shape?>) : ShapeEditor(shapes) {
    private lateinit var center: Coordinates

    override fun onLBdown(start: Coordinates) {
        center = start
        val shape = EllipseShape()
        shape.set(start, start)
        ArrayUtils.addItem(shapes, shape)
    }

    override fun onMouseMove(end: Coordinates) {
        val shape = ArrayUtils.getLastItem(shapes)
        val start = CoordinateUtils.calculateStart(center, end)
        shape?.set(start, end)
    }
}