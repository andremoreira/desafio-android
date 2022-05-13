package com.picpay.desafio.android

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.RecyclerViewMatchers.isEmpty
import com.picpay.desafio.android.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MainActivityTest {

    companion object {
        private const val TIME = 5000L
    }

    @get:Rule
    var activityRule = activityScenarioRule<MainActivity>()

    private var context: Context? = null
    private var title: String? = ""

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        title = context?.resources?.getString(R.string.title).toString()
    }

    @Test
    fun shouldReturnTitleContacts() {
        onView(withText(title)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun shouldReturnListContacts() {
        onView(withText(title)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.user_list_recycler)).let {
            it.check(matches(isEnabled()))
            it.check(matches(isCompletelyDisplayed()))
        }
        Thread.sleep(TIME)
    }

    @Test
    fun shouldReturnListContactsIsEmpty() {
        onView(withText(context?.resources?.getString(R.string.title))).check(
            matches(
                isCompletelyDisplayed()
            )
        )
        onView(withId(R.id.user_list_recycler)).check(matches(isEnabled()))
        Thread.sleep(TIME)

        onView(withId(R.id.user_list_recycler))
            .check(matches(isEmpty()))
    }

    @Test
    fun shouldReturnListContactsNotIsEmpty() {
        onView(withText(context?.resources?.getString(R.string.title))).check(
            matches(
                isCompletelyDisplayed()
            )
        )
        onView(withId(R.id.user_list_recycler)).check(matches(isEnabled()))
        Thread.sleep(TIME)
        onView(withId(R.id.user_list_recycler))
            .check(matches(RecyclerViewMatchers.notIsEmpty()))
    }
}