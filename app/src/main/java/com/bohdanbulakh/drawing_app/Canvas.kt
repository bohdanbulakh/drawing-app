package com.bohdanbulakh.drawing_app

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bohdanbulakh.drawing_app.editors.ShapeObjectsEditor

class Canvas(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    val editor = ShapeObjectsEditor

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        editor.onPaint(canvas)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val coords = Coordinates(event!!.x, event.y)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> editor.onLBdown(coords)
            MotionEvent.ACTION_MOVE -> editor.onMouseMove(coords)
            MotionEvent.ACTION_UP -> editor.onLBup(coords)
        }
        return true
    }
}