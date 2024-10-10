package com.bohdanbulakh.drawing_app.utils

import kotlin.collections.set

class ExtendedMap<K, V> : HashMap<K, V>() {
    fun keyByValue(value: V): K? {
        for (entry in entries) {
            if (entry.value == value) return entry.key
        }
        return null
    }
}

fun <K, V> extendedMapOf(vararg pairs: Pair<K, V>): ExtendedMap<K, V> {
    val result = ExtendedMap<K, V>()

    for (pair in pairs) {
        result[pair.first] = pair.second
    }

    return result
}
