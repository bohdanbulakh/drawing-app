package com.bohdanbulakh.drawing_app

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bohdanbulakh.drawing_app.editors.*
import com.bohdanbulakh.drawing_app.utils.extendedMapOf

class MainActivity : AppCompatActivity() {
    private var checkedMainMenuItem: MenuItem? = null
    private var checkedToolbarMenuItem: MenuItem? = null
    private lateinit var toolbarMenu: Menu
    private lateinit var mainMenu: Menu

    private val itemsMap = extendedMapOf(
        R.id.point to R.id.toolbar_menu_point,
        R.id.line to R.id.toolbar_menu_line,
        R.id.rectangle to R.id.toolbar_menu_rect,
        R.id.ellipse to R.id.toolbar_menu_ellipse,
    )

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
        val mainItem =
            if (item.itemId in itemsMap.keys) item.itemId
            else itemsMap.keyByValue(item.itemId)!!

        val objectsItem =
            if (item.itemId in itemsMap.values) item.itemId
            else itemsMap[item.itemId]!!

        makeMainMenuItemActive(mainItem)
        makeToolbarMenuItemActive(objectsItem)

        when (item.itemId) {
            in MenuItems.POINT.ids -> editor.startPointEditor()
            in MenuItems.LINE.ids -> editor.startLineEditor()
            in MenuItems.RECT.ids -> editor.startRectEditor()
            in MenuItems.ELLIPSE.ids -> editor.startEllipseEditor()
        }
        return true
    }

    private fun makeMainMenuItemActive(itemId: Int) {
        checkedMainMenuItem?.isChecked = false

        val mainItem = mainMenu.findItem(itemId)
        mainItem.isChecked = true
        checkedMainMenuItem = mainItem
    }

    private fun makeToolbarMenuItemActive(itemId: Int) {
        checkedToolbarMenuItem?.icon?.setTint(
            ContextCompat.getColor(
                this,
                R.color.dark_grey
            )
        )

        val objectsItem = toolbarMenu.findItem(itemId)
        checkedToolbarMenuItem = objectsItem
        checkedToolbarMenuItem?.icon?.setTint(Color.BLACK)
    }
}