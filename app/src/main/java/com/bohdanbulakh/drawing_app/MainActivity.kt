package com.bohdanbulakh.drawing_app

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bohdanbulakh.drawing_app.shapes.CubeShape
import com.bohdanbulakh.drawing_app.shapes.EllipseShape
import com.bohdanbulakh.drawing_app.shapes.LineOOShape
import com.bohdanbulakh.drawing_app.shapes.LineShape
import com.bohdanbulakh.drawing_app.shapes.PointShape
import com.bohdanbulakh.drawing_app.shapes.RectShape

class MainActivity : AppCompatActivity() {
    private var checkedToolbarMenuItem: MenuItem? = null
    private lateinit var toolbarMenu: Menu
    private lateinit var windowTitle: TextView

    private val editor = MyEditor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val canvas = findViewById<Canvas>(R.id.canvas)
        canvas.setEditor(editor)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val objectsMenuButton: Button = findViewById(R.id.objects)
        val objectsMainMenu = PopupMenu(this, objectsMenuButton)
        objectsMainMenu.menuInflater.inflate(R.menu.main_menu, objectsMainMenu.menu)

        objectsMenuButton.setOnClickListener {
            objectsMainMenu.show()
        }

        objectsMainMenu.setOnMenuItemClickListener { item ->
            startEditor(item)
        }

        windowTitle = findViewById(R.id.window_title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        toolbarMenu = menu!!
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return startEditor(item)
    }

    private fun startEditor(item: MenuItem): Boolean {
        val objectsItem = MenuItems.getShape(item.itemId)

        windowTitle.text = objectsItem?.text
        makeToolbarMenuItemActive(objectsItem!!.toolbar)

        when (item.itemId) {
            in MenuItems.POINT.ids -> editor.start(PointShape())
            in MenuItems.LINE.ids -> editor.start(LineShape())
            in MenuItems.LINE_WITH_POINTS.ids -> editor.start(LineOOShape())
            in MenuItems.RECT.ids -> editor.start(RectShape())
            in MenuItems.CUBE.ids -> editor.start(CubeShape())
            in MenuItems.ELLIPSE.ids -> editor.start(EllipseShape())
        }
        return true
    }

    private fun makeToolbarMenuItemActive(itemId: Int) {
        checkedToolbarMenuItem?.icon?.setTint(
            ContextCompat.getColor(
                this, R.color.dark_grey
            )
        )

        checkedToolbarMenuItem = toolbarMenu.findItem(itemId)
        checkedToolbarMenuItem?.icon?.setTint(Color.MAGENTA)
    }
}