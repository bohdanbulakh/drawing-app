package com.bohdanbulakh.drawing_app

enum class MenuItems(val main: Int, val toolbar: Int, val text: String) {
    POINT(R.id.point, R.id.toolbar_menu_point, "Крапка"), LINE(
        R.id.line,
        R.id.toolbar_menu_line,
        "Лінія"
    ),
    LINE_WITH_POINTS(
        R.id.line_with_points,
        R.id.toolbar_menu_line_with_points,
        "Лінія з крапками"
    ),
    RECT(R.id.rectangle, R.id.toolbar_menu_rect, "Прямокутник"), CUBE(
        R.id.cube,
        R.id.toolbar_menu_cube,
        "Куб"
    ),
    ELLIPSE(R.id.ellipse, R.id.toolbar_menu_ellipse, "Еліпс");

    val ids = arrayOf(main, toolbar)

    companion object {
        fun getShape(id: Int): MenuItems? {
            for (shape in MenuItems.entries) {
                if (id in shape.ids) return shape
            }

            return null
        }
    }
}