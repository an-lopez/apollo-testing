package com.mishka.graphqltest.util

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class BottomMarginDecoration(private val dpBottomOffset: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.bottom = 16.toPx()
    }
}