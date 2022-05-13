package com.picpay.desafio.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher

object RecyclerViewMatchers {

    fun atPosition(
        position: Int,
        itemMatcher: Matcher<View>
    ) = object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            val viewHolder = item?.findViewHolderForAdapterPosition(position) ?: return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }

    fun notIsEmpty(): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView Not Is Empty")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                 return verifyItemList(item)
            }
        }
    }

    fun isEmpty(): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView  Is Empty")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                return verifyItemList(item)
            }
        }
    }

   private fun verifyItemList(item: RecyclerView?): Boolean{
        return if (item?.adapter?.itemCount!! > 0){
            true
        }else{
            item.adapter?.itemCount  == 0
        }
    }
}