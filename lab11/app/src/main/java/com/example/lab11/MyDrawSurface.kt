package com.example.lab11

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyDrawSurface : SurfaceView, SurfaceHolder.Callback {

    companion object {
        private var speed = 10f
        private var dx = speed
        private var dy = 0f

        fun setSpeed(newSpeed: Float) {
            speed = newSpeed
            if (dx != 0f) {
                if (dx > 0) {
                    dx = newSpeed
                } else {
                    dx = -newSpeed
                }
            } else if (dy != 0f) {
                if (dy > 0) {
                    dy = newSpeed
                } else {
                    dy = -newSpeed
                }
            }
        }
    }

    private var cx = 0f
    private var cy = 0f

    private var paint = Paint()
    private lateinit var job: Job
    private val cubeEdge: Float = 50f
    private var direction: Direction = Direction.Right

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {
        paint.color = Color.RED
        job = GlobalScope.launch {
            var canvas: Canvas?
            while (true) {
                canvas = holder.lockCanvas(null)
                if (canvas != null) {
                    canvas.drawColor(Color.argb(255, 55, 55, 55))
                    canvas.drawRect(cx, cy, cx + cubeEdge, cy + cubeEdge, paint)
                    holder.unlockCanvasAndPost(canvas)
                }
                cx += dx
                cy += dy
                if (cx > width - cubeEdge && direction == Direction.Right) {
                    dx = 0f
                    dy = speed
                    direction = Direction.Down
                } else if (cx < 0 && direction == Direction.Left) {
                    dx = 0f
                    dy = -speed
                    direction = Direction.Up
                } else if (cy > height - cubeEdge && direction == Direction.Down) {
                    dy = 0f
                    dx = -speed
                    direction = Direction.Left
                } else if (cy < 0 && direction == Direction.Up) {
                    dy = 0f
                    dx = speed
                    direction = Direction.Right
                }
            }
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        job.cancel()
    }
}