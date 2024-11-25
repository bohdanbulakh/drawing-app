package com.bohdanbulakh.drawing_app

import com.bohdanbulakh.drawing_app.shapes.interfaces.ShapeInterface

data class ShapeInfo(
    val name: String,
    val x1: Float,
    val y1: Float,
    val x2: Float,
    val y2: Float,
) {

    constructor(shape: ShapeInterface) : this(
        shape::class.simpleName!!,
        shape.coords.start.x,
        shape.coords.start.y,
        shape.coords.end.x,
        shape.coords.end.y,
    )

    fun toArray(): Array<String> {
        return arrayOf(
            name,
            x1.toString(),
            y1.toString(),
            x2.toString(),
            y2.toString(),
        )
    }
}