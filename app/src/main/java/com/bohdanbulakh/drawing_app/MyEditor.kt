package com.bohdanbulakh.drawing_app

import android.graphics.Canvas
import com.bohdanbulakh.drawing_app.shapes.*
import com.bohdanbulakh.drawing_app.shapes.interfaces.*
import com.bohdanbulakh.drawing_app.utils.*

class MyEditor {
    private val arraySize = 103
    private var shapes = arrayOfNulls<ShapeInterface>(arraySize)
    private var currentShape: ShapeInterface? = null
    private lateinit var table: Table

    private val highlightShape = { index: Int, color: Int ->
        shapes[index]?.paint?.color = color
    }

    private val removeShape = { index: Int? ->
        if (index != null) {
            ArrayUtils.removeAtIndex(index, shapes)
        }
    }

    fun start(shape: Shape) {
        currentShape = shape
    }

    fun onPaint(canvas: Canvas) {
        for (shape in shapes) {
            shape?.show(canvas)
        }
    }

    fun onLBdown(start: Coordinates) {
        val shape = currentShape?.getShape()
        shape?.set(CoordinatesPair(start, start))
        ArrayUtils.addItem(shapes, shape)
    }

    fun onMouseMove(end: Coordinates) {
        if (currentShape != null) {
            ArrayUtils.getLastItem(shapes)?.writeEnd(end)
        }
    }

    fun onLBup() {
        if (currentShape != null) {
            val shape = ArrayUtils.getLastItem(shapes)
            shape!!.gumStyle = false

            table.add(ShapeInfo(shape))
        }
    }

    fun setTable(
        table: Table
    ) {
        this.table = table
        this.table.setCallbacks(highlightShape, removeShape)
    }

    fun loadShapes(shapesData: List<String>) {
        ArrayUtils.clear(shapes)

        for (shapeData in shapesData) {
            val fields = shapeData.split("\t")

            val constructor =
                Class.forName("com.bohdanbulakh.drawing_app.shapes." + fields[0]).getConstructor()
            val shape: ShapeInterface = constructor.newInstance() as ShapeInterface
            shape.gumStyle = false
            shape.set(
                CoordinatesPair(
                    Coordinates(fields[1].toFloat(), fields[2].toFloat()),
                    Coordinates(fields[3].toFloat(), fields[4].toFloat())
                )
            )
            table.add(ShapeInfo(shape))
            ArrayUtils.addItem(shapes, shape)
        }

        table.loadShapesInfo(shapesData)
    }

    companion object {
        private var instance: MyEditor? = null

        fun getInstance(): MyEditor {
            if (instance == null) {
                instance = MyEditor()
            }
            return instance!!
        }
    }
}