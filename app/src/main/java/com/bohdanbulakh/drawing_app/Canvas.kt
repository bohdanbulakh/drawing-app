package com.bohdanbulakh.drawing_app

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class Canvas(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private lateinit var editor: MyEditor

    fun setEditor(editor: MyEditor) {
        this.editor = editor
    }

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
            MotionEvent.ACTION_UP -> editor.onLBup()
        }
        return true
    }
}