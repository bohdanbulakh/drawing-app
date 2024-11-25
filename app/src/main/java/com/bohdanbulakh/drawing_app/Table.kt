package com.bohdanbulakh.drawing_app

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableLayout.VERTICAL
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.bohdanbulakh.drawing_app.shapes.interfaces.ShapeInterface
import com.bohdanbulakh.drawing_app.utils.TableUtils

class Table : Fragment() {
    private lateinit var tableLayout: ConstraintLayout
    private lateinit var table: TableLayout
    private lateinit var removeButton: Button
    private lateinit var highlightShapeCallback: (index: Int, color: Int) -> Unit
    private lateinit var removeShapeCallback: (index: Int?) -> Unit
    private var activeRow: TableRow? = null
    val shapesInfo = mutableListOf<ShapeInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_table_overlay, container, false)
        tableLayout = view.findViewById(R.id.table_layout)
        table = view.findViewById(R.id.table)
        table.orientation = VERTICAL
        createTableHeader()

        removeButton = view.findViewById(R.id.remove_shape)

        return view
    }

    fun toggleVisibility() {
        tableLayout.visibility = if (tableLayout.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }

    fun add(info: ShapeInfo) {
        shapesInfo.add(info)

        val row = makeRow(info)
        table.addView(row)

        row.setOnClickListener {
            if (activeRow != row) {
                highlight(Typeface.NORMAL, Color.BLACK)
            }

            activeRow?.setBackgroundColor(Color.parseColor("#eeeeee"))

            activeRow = row
            if ((activeRow?.getChildAt(0) as TextView).typeface.isBold) {
                highlight(Typeface.NORMAL, Color.BLACK)
                activeRow = null
            } else {
                highlight(Typeface.BOLD, Color.MAGENTA)
                row.setBackgroundColor(Color.parseColor("#8ac4ff"))
            }
        }

        removeButton.setOnClickListener {
            val rowN = TableUtils.findRowIndex(activeRow, table)
            if (rowN != null) {
                shapesInfo.removeAt(rowN - 1)
                removeShapeCallback(rowN - 1)
            }

            table.removeView(activeRow)
        }
    }

    fun setCallbacks(
        highlightShape: (index: Int, color: Int) -> Unit,
        removeShape: (index: Int?) -> Unit,
    ) {
        this.highlightShapeCallback = highlightShape
        this.removeShapeCallback = removeShape
    }

    private fun highlight(
        typeface: Int,
        color: Int
    ) {
        val firstCell = activeRow?.getChildAt(0) as TextView?
        firstCell?.setTypeface(Typeface.DEFAULT, typeface)

        val rowN = TableUtils.findRowIndex(activeRow, table)
        if (rowN != null) {
            highlightShapeCallback(rowN - 1, color)
        }
    }

    private fun makeRow(info: ShapeInfo): TableRow {
        val row = TableRow(table.context)
        val infoArray = info.toArray()

        val rowData = arrayOfNulls<TextView>(5).mapIndexed { index, _ ->
            TextView(table.context).apply {
                setPadding(8, 8, 40, 8)
                setTextColor(Color.BLACK)
                text = infoArray[index]
            }
        }


        for (data in rowData) {
            row.addView(data)
        }

        return row
    }


    private fun createTableHeader() {
        val headerRow = TableRow(table.context)

        val header = Array(5) { _ ->
            TextView(table.context).apply {
                setPadding(8, 8, 40, 8)
                textSize = 25f
                setTextColor(Color.BLACK)
            }
        }
        header[0].setText(R.string.name)
        header[1].setText(R.string.x1)
        header[2].setText(R.string.y1)
        header[3].setText(R.string.x2)
        header[4].setText(R.string.y2)

        for (cell in header) {
            headerRow.addView(cell)
        }

        table.addView(headerRow)
    }

    fun loadShapesInfo(shapesInfo: List<String>) {
        this.shapesInfo.clear()
        table.removeViews(1, table.children.count() - 1)

        for (shapeInfo in shapesInfo) {
            val info = shapeInfo.split("\t")
            add(
                ShapeInfo(
                    info[0],
                    info[1].toFloat(),
                    info[2].toFloat(),
                    info[3].toFloat(),
                    info[4].toFloat(),
                )
            )
        }
    }
}
