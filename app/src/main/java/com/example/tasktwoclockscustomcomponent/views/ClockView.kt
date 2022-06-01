package com.example.tasktwoclockscustomcomponent.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.tasktwoclockscustomcomponent.R
import java.util.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


class ClockView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    // Radius of the circle
    private var radius = 0f
    // Hands lengths
    private val hourHandLength: Float
    private val minuteHandLength: Float
    private val secondHandLength: Float
    // Hands widths
    private val hourHandWidth: Float
    private val minuteHandWidth: Float
    private val secondHandWidth: Float
    // Hands colors
    private val hourHandColor: Int
    private val minuteHandColor: Int
    private val secondHandColor: Int
    // Clock parts colors
    private val clockFaceColor: Int
    private val clockCaseColor: Int

    private val markColor: Int
    private val markSize: Float

    private var hour = 0
    private var minute = 0
    private var second = 0

    private var calendar: Calendar = Calendar.getInstance()

    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ClockView,
            0, 0).apply {

            try {
                hourHandLength = getFraction(R.styleable.ClockView_hourHandLength, 1, 1, 0.4f)
                minuteHandLength = getFraction(R.styleable.ClockView_minuteHandLength, 1, 1, 0.8f)
                secondHandLength = getFraction(R.styleable.ClockView_secondHandLength, 1, 1, 0.75f)

                hourHandWidth = getDimension(R.styleable.ClockView_hourHandWidth, 20f)
                minuteHandWidth = getDimension(R.styleable.ClockView_minuteHandWidth, 20f)
                secondHandWidth = getDimension(R.styleable.ClockView_secondHandWidth, 10f)

                hourHandColor = getColor(R.styleable.ClockView_hourHandColor, Color.BLACK)
                minuteHandColor = getColor(R.styleable.ClockView_minuteHandColor, Color.BLACK)
                secondHandColor = getColor(R.styleable.ClockView_secondHandColor, Color.DKGRAY)

                clockFaceColor = getColor(R.styleable.ClockView_clockFaceColor, Color.LTGRAY)
                clockCaseColor = getColor(R.styleable.ClockView_clockCaseColor, Color.BLACK)

                markColor = getColor(R.styleable.ClockView_markColor, Color.BLACK)
                markSize = getDimension(R.styleable.ClockView_markSize, 10f)
            } finally {
                recycle()
            }
        }
    }
    // Some Paints for different purposes
    private val casePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = clockCaseColor
        style = Paint.Style.FILL
    }

    private val markPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = markColor
        style = Paint.Style.FILL
    }

    private val facePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = clockFaceColor
        style = Paint.Style.FILL
    }

    private val hourHandPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = hourHandColor
        strokeWidth = hourHandWidth
        style = Paint.Style.FILL
    }

    private val minuteHandPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = minuteHandColor
        strokeWidth = minuteHandWidth
        style = Paint.Style.FILL
    }

    private val secondHandPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = secondHandColor
        strokeWidth = secondHandWidth
        style = Paint.Style.FILL
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        // Calculate the radius from the smaller of the width and height.
        radius = (min(width, height) / 2.0).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        second = calendar.get(Calendar.SECOND)

        canvas.apply {
            drawCase()
            drawHourHand(hour, minute)
            drawMinuteHand(minute)
            drawSecondHand(second)
            drawMarks()
        }
        invalidate()
        requestLayout()
    }

    private fun PointF.computeXY(degree: Int, radius: Float) {
        // Angles are in radians.
        val startAngle = - PI / 2
        val angle = startAngle + degree * (PI / 30)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    private fun PointF.computeHourXY(hour: Int, minute: Int) {
        val degree = (hour % 12 + minute.toDouble() / 60) * 5
        this.computeXY(degree.toInt(), radius*hourHandLength)
    }

    private fun PointF.computeMinuteXY(minute: Int) {
        this.computeXY(minute, radius*minuteHandLength)
    }

    private fun PointF.computeSecondXY(second: Int) {
        this.computeXY(second, radius*secondHandLength)
    }

    private fun Canvas.drawCase() {
        this.apply {
            drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, casePaint)
            drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius*0.95f, facePaint)
        }
    }

    private fun Canvas.drawHourHand(hour: Int, minute: Int) {
        pointPosition.computeHourXY(hour, minute)
        this.drawLine((width / 2).toFloat(), (height / 2).toFloat(), pointPosition.x, pointPosition.y, hourHandPaint)
    }

    private fun Canvas.drawMinuteHand(minute: Int) {
        pointPosition.computeMinuteXY(minute)
        this.drawLine((width / 2).toFloat(), (height / 2).toFloat(), pointPosition.x, pointPosition.y, minuteHandPaint)
    }

    private fun Canvas.drawSecondHand(second: Int) {
        pointPosition.computeSecondXY(second)
        this.drawLine((width / 2).toFloat(), (height / 2).toFloat(), pointPosition.x, pointPosition.y, secondHandPaint)
    }

    private fun Canvas.drawMarks() {
        for (i in 0..60 step 5) {
            pointPosition.computeXY(i, radius*0.9f)
            this.drawCircle(pointPosition.x, pointPosition.y, markSize, markPaint)
        }
    }

}