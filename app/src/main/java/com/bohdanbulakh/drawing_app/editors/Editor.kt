package com.bohdanbulakh.drawing_app.editors

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.Coordinates

abstract class Editor {
    abstract fun onLBdown(start: Coordinates)
    abstract fun onLBup(end: Coordinates)
    abstract fun onMouseMove(end: Coordinates)
    abstract fun onPaint(canvas: Canvas)
}