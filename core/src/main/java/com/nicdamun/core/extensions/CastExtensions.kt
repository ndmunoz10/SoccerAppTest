package com.nicdamun.core.extensions

/**
 * @author Nicolás David Muñoz Cuervo
 * File Objective: Define some useful methods extensions for casting types
 */

/**
 * Returns the casted [this] element to [T] or null if
 * [this] is not of type [T]
 */
inline fun <reified T> Any?.tryCast(): T? {
    if (this is T) {
        return this
    }
    return null
}