package com.bohdanbulakh.drawing_app.utils

import android.widget.TableLayout
import android.widget.TableRow

class TableUtils {
    companion object {
        fun findRowIndex(row: TableRow?, table: TableLayout): Int? {
            if (row == null) return null

            var index: Int? = null
            for (i in 0 until table.childCount) {
                val currentRow = table.getChildAt(i) as? TableRow
                if (currentRow == row) {
                    index = i
                    break
                }
            }

            return index
        }
    }
}