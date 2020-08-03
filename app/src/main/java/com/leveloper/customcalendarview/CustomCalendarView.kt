package com.leveloper.customcalendarview

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import kotlin.math.max

class CustomCalendarView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    @AttrRes private val defStyleAttr: Int = R.attr.calendarViewStyle,
    @StyleRes private val defStyleRes: Int = R.style.Calendar_CalendarViewStyle
) : ViewGroup(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    private var _height: Float = 0f

    init {
        context.withStyledAttributes(attrs, R.styleable.CalendarViewStyle, defStyleAttr, defStyleRes) {
            _height = getDimension(R.styleable.CalendarViewStyle_dayHeight, 0f)
        }

        (1..42).forEach { i ->
            addView(ItemView(context).apply {
                value = i
                setOnClickListener {
                    println("what the fuck")
                }
            })
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val iWidth = (width / 7).toFloat()
        val iHeight = (height / 6).toFloat()

        var (i, j) = 0 to 0

        for (view in children) {
            val left = i * iWidth
            val top = j * iHeight

            view.layout(left.toInt(), top.toInt(), (left + iWidth).toInt(), (top + iHeight).toInt())

            i++
            if (i == 7) {
                i = 0
                j++
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = paddingTop + paddingBottom + max(suggestedMinimumHeight, _height.toInt())
        setMeasuredDimension(View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec), height)
    }
}