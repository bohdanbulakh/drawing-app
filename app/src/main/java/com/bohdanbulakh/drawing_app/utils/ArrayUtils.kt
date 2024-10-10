package com.bohdanbulakh.drawing_app.utils

class ArrayUtils {
    companion object {
        fun <T> addItem(array: Array<T?>, value: T) {
            if (array.last() != null) {
                removeFirst(array)
            }

            for (i in 0..array.lastIndex) {
                if (array[i] == null) {
                    array[i] = value
                }
            }
        }

        fun <T> getLastItem(array: Array<T>): T {
            for (i in 0..(array.lastIndex - 1)) {
                if (array[i] != null && array[i + 1] == null) return array[i]
            }

            return array.last()
        }

        fun <T> removeFirst(array: Array<T?>) {
            for (i in 1..array.lastIndex) {
                array[i - 1] = array[i]
            }
            array[array.lastIndex] = null
        }
    }
}