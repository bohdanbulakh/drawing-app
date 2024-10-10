package com.bohdanbulakh.drawing_app

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import com.bohdanbulakh.drawing_app.editors.*

class MainActivity : AppCompatActivity() {
    private var checkedItem: MenuItem? = null

    private val editor = ShapeObjectsEditor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val objectsMenuButton: Button = findViewById(R.id.objects)
        val menu = PopupMenu(this, objectsMenuButton)
        menu.menuInflater.inflate(R.menu.main_menu, menu.menu)

        objectsMenuButton.setOnClickListener {
            menu.show()
        }

        menu.setOnMenuItemClickListener { item ->
            checkedItem?.isChecked = false
            checkedItem = item
            checkedItem?.isChecked = true

            when (item.itemId) {
                R.id.point -> editor.startPointEditor()
                R.id.line -> editor.startLineEditor()
                R.id.rectangle -> editor.startRectEditor()
                R.id.ellipse -> editor.startEllipseEditor()
            }
            true
        }
    }
}