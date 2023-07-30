package com.example.bait2073mobileapplicationdevelopment.utilities

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.widget.SeekBar

object CustomSeekBarUtils {

    private var progressBarWidth: Int = 0
    private var progressBarHeight: Int = 0
    private var lastProgressX: Int = 0
    private var progressItemWidth: Int = 0
    private var progressItemRight: Int = 0

    fun drawProgressItems(canvas: Canvas, seekBar: SeekBar, mProgressItemsList: List<ProgressItem>) {
        progressBarWidth = seekBar.width
        progressBarHeight = seekBar.height
        val thumb = seekBar.thumb
        val thumbOffset = thumb.intrinsicWidth

        for (i in 0 until mProgressItemsList.size) {
            val progressItem = mProgressItemsList[i]
            val progressPaint = Paint()
            progressPaint.color = seekBar.resources.getColor(progressItem.color)

            progressItemWidth = (progressItem.progressItemPercentage * progressBarWidth / 100).toInt()

            progressItemRight = lastProgressX + progressItemWidth

            // For the last item, set the right of the progress item to the width of the progress bar
            if (i == mProgressItemsList.size - 1 && progressItemRight != progressBarWidth) {
                progressItemRight = progressBarWidth
            }

            val progressRect = Rect()
            progressRect.set(lastProgressX, thumbOffset / 2, progressItemRight, progressBarHeight - thumbOffset / 2)
            canvas.drawRect(progressRect, progressPaint)
            lastProgressX = progressItemRight
        }
    }
}
