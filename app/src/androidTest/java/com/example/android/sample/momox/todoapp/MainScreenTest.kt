package com.example.android.sample.my.todoapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.android.sample.my.todoapp.main.MainActivity

import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainScreenTest {
    @Rule @JvmField var tasksActivityTestRule = object :
            ActivityTestRule<MainActivity>(MainActivity::class.java) {
    }

    @Test
    fun clickAddTaskButton_opensAddTaskUi() {
        onView(withId(R.id.fab_add)).perform(click())

        onView(withId(R.id.add_task_title)).check(matches(isDisplayed()))
    }

    @Test
    fun addTask() {
        val ANY_STRING = "abc"
        onView(withId(R.id.fab_add)).perform(click())
        onView(withId(R.id.add_task_title)).perform(typeText(ANY_STRING),
                closeSoftKeyboard())
        onView(withId(R.id.add_task_description)).perform(typeText(ANY_STRING),
                closeSoftKeyboard())

        onView(withId(R.id.add_task_title)).check(matches(withText(ANY_STRING)))
        onView(withId(R.id.add_task_description)).check(matches(withText(ANY_STRING)))
    }

    @Test
    fun editTask() {
        val title1 = "CFG"
        val description1 = "FGH"
        onView(withId(R.id.fab_add)).perform(click())
        onView(withId(R.id.add_task_title)).perform(typeText(title1),
                closeSoftKeyboard())
        onView(withId(R.id.add_task_description)).perform(typeText(description1),
                closeSoftKeyboard())

        onView(withId(R.id.fab_done)).perform(click())

        onView(withText(title1)).perform(click())

        val title = "cde"
        val description = "fgh"
        onView(withId(R.id.add_task_title))
                .perform(replaceText(title), closeSoftKeyboard())
        onView(withId(R.id.add_task_description)).perform(replaceText(description),
                closeSoftKeyboard())

        onView(withId(R.id.fab_edit)).perform(click())


        onView(withId(R.id.add_task_title)).check(matches(withText(title)))
        onView(withId(R.id.add_task_description)).check(matches(withText(description)))
    }
}