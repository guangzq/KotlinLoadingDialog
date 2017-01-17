package com.zqg.kotlin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class LVCircularRing @JvmOverloads constructor(context: Context, attrs: AttributeSet = null!!, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private var mWidth = 0f
    private var mPadding = 0f
    private var startAngle = 0f
    private var mPaint: Paint? = null

    init {
        initPaint()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (measuredWidth > height)
            mWidth = measuredHeight.toFloat()
        else
            mWidth = measuredWidth.toFloat()
        mPadding = 5f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint!!.color = Color.argb(100, 255, 255, 255)
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2 - mPadding, mPaint!!)
        mPaint!!.color = Color.WHITE
        val rectF = RectF(mPadding, mPadding, mWidth - mPadding, mWidth - mPadding)
        canvas.drawArc(rectF, startAngle, 100f, false, mPaint!!)

    }


    private fun initPaint() {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.color = Color.WHITE
        mPaint!!.strokeWidth = 8f
    }

    fun startAnim() {
        stopAnim()
        startViewAnim(0f, 1f, 1000)
    }

    fun stopAnim() {
        if (valueAnimator != null) {
            clearAnimation()
            valueAnimator!!.repeatCount = 1
            valueAnimator!!.cancel()
            valueAnimator!!.end()
        }
    }

    internal var valueAnimator: ValueAnimator? = null

    private fun startViewAnim(startF: Float, endF: Float, time: Long): ValueAnimator {
        valueAnimator = ValueAnimator.ofFloat(startF, endF)

        valueAnimator!!.duration = time
        valueAnimator!!.interpolator = LinearInterpolator()
        valueAnimator!!.repeatCount = ValueAnimator.INFINITE//无限循环
        valueAnimator!!.repeatMode = ValueAnimator.RESTART//

        valueAnimator!!.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            startAngle = 360 * value

            invalidate()
        }
        valueAnimator!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
            }
        })
        if (!valueAnimator!!.isRunning) {
            valueAnimator!!.start()
        }

        return valueAnimator as ValueAnimator
    }
}
