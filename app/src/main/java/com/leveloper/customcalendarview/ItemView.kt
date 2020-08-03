package com.leveloper.customcalendarview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes

class ItemView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    @AttrRes private val defStyleAttr: Int = R.attr.itemViewStyle,
    @StyleRes private val defStyleRes: Int = R.style.Calendar_ItemViewStyle
): View(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr){

    var value = 0
    private val bounds = Rect()
    private lateinit var paint: TextPaint

    private var dayTextSize = 0
    init {
        context.withStyledAttributes(attrs, R.styleable.ItemViewStyle, defStyleAttr, defStyleRes) {
            dayTextSize = getDimensionPixelSize(R.styleable.ItemViewStyle_dayTextSize, 0)
            paint = TextPaint().apply {
                color = Color.BLACK
                isAntiAlias = true
                textSize = dayTextSize.toFloat()
            }
        }
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) return

        /* 오전 or 오후 */
        val abc = value.toString()
        paint.getTextBounds(abc, 0, abc.length, bounds)
        canvas.drawText(
            abc,
            (width / 2 - bounds.width() / 2).toFloat(),
            bounds.height().toFloat(),
            paint
        )
    }
}