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
import com.bohdanbulakh.drawing_app.editors.*

class MainActivity : AppCompatActivity() {
    private var checkedToolbarMenuItem: MenuItem? = null
    private lateinit var toolbarMenu: Menu
    private lateinit var mainMenu: Menu
    private lateinit var windowTitle: TextView

    private val editor = ShapeObjectsEditor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        mainMenu = objectsMainMenu.menu
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
            in MenuItems.POINT.ids -> editor.startPointEditor()
            in MenuItems.LINE.ids -> editor.startLineEditor()
            in MenuItems.RECT.ids -> editor.startRectEditor()
            in MenuItems.ELLIPSE.ids -> editor.startEllipseEditor()
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
        checkedToolbarMenuItem?.icon?.setTint(Color.BLACK)
    }
}