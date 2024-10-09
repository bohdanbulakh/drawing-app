package com.bohdanbulakh.drawing_app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bohdanbulakh.drawing_app.editors.*

class MainActivity : AppCompatActivity() {
    private val submenuItems = arrayOf(R.id.line, R.id.point, R.id.rectangle, R.id.ellipse)
    private var checkedItem: MenuItem? = null

    private val editor = ShapeObjectsEditor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (submenuItems.contains(item.itemId)) {
            checkedItem?.isChecked = false
            checkedItem = item
            checkedItem?.isChecked = true

            when (item.itemId) {
                R.id.point -> editor.startPointEditor()
                R.id.line -> editor.startLineEditor()
                R.id.rectangle -> editor.startRectEditor()
                R.id.ellipse -> editor.startEllipseEditor()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}