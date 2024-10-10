package com.bohdanbulakh.drawing_app

enum class MenuItems(val main: Int, toolbar: Int) {
    POINT(R.id.point, R.id.toolbar_menu_point),
    LINE(R.id.line, R.id.toolbar_menu_line),
    RECT(R.id.rectangle, R.id.toolbar_menu_rect),
    ELLIPSE(R.id.ellipse, R.id.toolbar_menu_ellipse),;

    val ids = arrayOf(main, toolbar)
}