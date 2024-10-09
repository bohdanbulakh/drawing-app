package com.bohdanbulakh.drawing_app.editors

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.Coordinates
import com.bohdanbulakh.drawing_app.shapes.Shape

class ShapeObjectsEditor() {
    companion object {
        private const val ARRAY_SIZE = 102
        private val shapes = arrayOfNulls<Shape>(ARRAY_SIZE)

        private var editor: ShapeEditor? = null

        fun onLBdown(start: Coordinates) {
            editor?.onLBdown(start)
        }

        fun onMouseMove(end: Coordinates) {
            editor?.onMouseMove(end)
        }

        fun onLBup(end: Coordinates) {
            editor?.onLBup(end)
        }

        fun onPaint(canvas: Canvas) {
            editor?.onPaint(canvas)
        }

        fun startPointEditor() {
            editor = PointEditor(shapes)
        }

        fun startLineEditor() {
            editor = LineEditor(shapes)
        }

        fun startRectEditor() {
            editor = RectEditor(shapes)
        }

        fun startEllipseEditor() {
            editor = EllipseEditor(shapes)
        }
    }
}