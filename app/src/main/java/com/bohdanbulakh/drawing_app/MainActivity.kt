package com.bohdanbulakh.drawing_app

import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bohdanbulakh.drawing_app.shapes.*
import java.io.File
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {
    private var checkedToolbarMenuItem: MenuItem? = null
    private lateinit var toolbarMenu: Menu
    private lateinit var windowTitle: TextView
    private lateinit var table: Table
    private val editor = MyEditor.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val objectsMainMenu = setPopupMenu(R.id.objects, R.menu.main_menu)
        objectsMainMenu.setOnMenuItemClickListener { item ->
            startEditor(item)
        }

        table = Table()
        editor.setTable(table)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, table)
            .commit()

        val fileMenu = setPopupMenu(R.id.file, R.menu.file_menu)

        fileMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.save -> saveFile()
                R.id.open -> openFile()
            }

            true
        }

        val tableButton = findViewById<Button>(R.id.tableButton)

        tableButton.setOnClickListener {
            table.toggleVisibility()
        }

        windowTitle = findViewById(R.id.window_title)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val resizer = table.view?.findViewById<View>(R.id.resizer)
        resizerSetup(resizer)
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


    private fun saveFile() {
        val path = this.filesDir?.path
        val file = File(path, "data.txt")
        file.delete()

        for (info in table.shapesInfo) {
            file.appendText(info.toArray().joinToString("\t") + "\n", Charsets.US_ASCII)
        }
    }

    private fun openFile() {
        val path = this.filesDir?.path

        val file = File(path, "data.txt")
        if (file.exists()) {
            val inputAsString = FileInputStream(file).bufferedReader(Charsets.US_ASCII).readText()
            val splitInput = inputAsString.split("\n")
            val shapesData = splitInput.subList(0, splitInput.lastIndex)
            editor.loadShapes(shapesData)
        }
    }

    private fun setPopupMenu(buttonId: Int, menuRes: Int): PopupMenu {
        val menuButton: Button = findViewById(buttonId)
        val menu = PopupMenu(this, menuButton)
        menu.menuInflater.inflate(menuRes, menu.menu)
        menuButton.setOnClickListener {
            menu.show()
        }

        return menu
    }


    private fun resizerSetup(resizer: View?) {
        resizer?.setOnTouchListener(object : View.OnTouchListener {
            private var initialY = 0f
            private var initialHeight = 0
            private val minHeight = 340
            private var maxHeight: Int? = null

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val point = Point()
                windowManager.defaultDisplay.getSize(point)
                maxHeight = point.y - 410

                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        initialY = event.rawY
                        initialHeight = table.requireView().height
                        return true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        val deltaY = (event.rawY - initialY).toInt()
                        val newHeight = initialHeight + deltaY

                        if (newHeight in minHeight..maxHeight!!) {
                            val view = table.requireView()
                            view.layoutParams.height = newHeight
                            view.requestLayout()
                        }
                        return true
                    }
                }
                return false
            }
        })
    }
}