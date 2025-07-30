package com.parissakalaee.parkadecisionmaker.util

import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Helper class to handle window insets compatibility for Android API 16+
 */
object WindowInsetsHelper {

    /**
     * Apply window insets handling for API 16+ compatibility
     */
    fun applyWindowInsets(window: Window, rootView: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Modern approach for API 21+
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
                )
                insets
            }
        } else {
            // Fallback for API 16-20
            applyWindowInsetsCompat(rootView)
        }
    }

    /**
     * Compatibility method for API 16-20
     */
    private fun applyWindowInsetsCompat(rootView: View) {
        rootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                rootView.getWindowVisibleDisplayFrame(rect)
                
                val screenHeight = rootView.rootView.height
                val keypadHeight = screenHeight - rect.bottom
                
                // Adjust for status bar
                val statusBarHeight = getStatusBarHeight(rootView)
                
                if (keypadHeight > screenHeight * 0.15) {
                    // Keyboard is probably shown
                    rootView.setPadding(0, statusBarHeight, 0, keypadHeight)
                } else {
                    // Keyboard is hidden
                    rootView.setPadding(0, statusBarHeight, 0, 0)
                }
            }
        })
    }

    /**
     * Get status bar height for older Android versions
     */
    private fun getStatusBarHeight(view: View): Int {
        var result = 0
        val resourceId = view.context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = view.context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * Set up edge-to-edge display for modern versions while maintaining compatibility
     */
    fun setupEdgeToEdge(window: Window, rootView: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+ approach
            window.setDecorFitsSystemWindows(false)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Android 5.0+ approach
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            )
        }
        // For API 16-20, we handle this in applyWindowInsets
        applyWindowInsets(window, rootView)
    }

    /**
     * Handle navigation bar and status bar visibility for DrawerLayout
     */
    fun setupDrawerLayoutInsets(drawerLayout: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setOnApplyWindowInsetsListener(drawerLayout) { view, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                
                // Apply insets to the main content, but not to the drawer
                val child = (view as android.view.ViewGroup).getChildAt(0)
                child?.setPadding(0, systemBars.top, 0, 0)
                
                insets
            }
        } else {
            // For older versions, apply basic padding
            val statusBarHeight = getStatusBarHeight(drawerLayout)
            val child = (drawerLayout as android.view.ViewGroup).getChildAt(0)
            child?.setPadding(0, statusBarHeight, 0, 0)
        }
    }
}