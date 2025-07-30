package com.parissakalaee.parkadecisionmaker.util

import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

/**
 * Extension functions for better window insets handling on older Android versions
 */

/**
 * Apply system window insets to view padding (API 16+ compatible)
 */
fun View.applySystemWindowInsets() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = systemBars.bottom
            )
            insets
        }
    } else {
        // Fallback for API 16-20: Use estimated status bar height
        val statusBarHeight = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            .let { resourceId ->
                if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
            }
        updatePadding(top = statusBarHeight)
    }
}

/**
 * Apply system window insets only to top padding (useful for toolbars)
 */
fun View.applySystemWindowInsetsTop() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = systemBars.top)
            insets
        }
    } else {
        // Fallback for API 16-20
        val statusBarHeight = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            .let { resourceId ->
                if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
            }
        updatePadding(top = statusBarHeight)
    }
}

/**
 * Apply system window insets only to bottom padding (useful for navigation bars)
 */
fun View.applySystemWindowInsetsBottom() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = systemBars.bottom)
            insets
        }
    }
    // No fallback needed for API 16-20 as navigation bar wasn't common
}

/**
 * Setup proper window insets for DrawerLayout content
 */
fun ViewGroup.setupDrawerLayoutContent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Only apply top inset to avoid interfering with drawer
            view.updatePadding(top = systemBars.top)
            insets
        }
    } else {
        val statusBarHeight = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            .let { resourceId ->
                if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
            }
        updatePadding(top = statusBarHeight)
    }
}