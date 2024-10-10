package com.bohdanbulakh.drawing_app.editors

import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.shapes.Shape
import com.bohdanbulakh.drawing_app.shapes.RectShape
import com.bohdanbulakh.drawing_app.utils.ArrayUtils
import com.bohdanbulakh.drawing_app.utils.CoordinateUtils

class RectEditor(val shapes: Array<Shape?>) : ShapeEditor(shapes) {
    private lateinit var center: Coordinates

    override fun onLBdown(start: Coordinates) {
        center = start
        val shape = RectShape()
        shape.set(start, start)
        ArrayUtils.addItem(shapes, shape)
    }

    override fun onMouseMove(end: Coordinates) {
        val shape = ArrayUtils.getLastItem(shapes)
        val start = CoordinateUtils.calculateStart(center, end)
        shape?.set(start, end)
    }
}