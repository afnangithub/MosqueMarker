package com.akarmoon.mosquemarker

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Before
    fun setup() {
        launchActivity<MainActivity>()
        onView(withId(R.id.add_marker_fab)).perform(click())
        onView(withId(R.id.name_input))
            .perform(replaceText("Masjid Raya Bandung"))
        onView(withId(R.id.location_address_input))
            .perform(replaceText("Jl. Dalem Kaum No.14, Balonggede, Kec. Regol, Kota Bandung, Jawa Barat 40251"))
        onView(withId(R.id.marker_checkbox)).perform(click())
        onView(withId(R.id.notes_input))
            .perform(replaceText("A sprawling mosque founded in the 19th century with a large domed roof, and twin 81 m minarets."))
        onView(withId(R.id.save_btn)).perform(click())
    }

    @Test
    fun new_marker_is_displayed_in_list() {
        Thread.sleep(3000)
        onView(withText("Masjid Raya Bandung")).check(matches(isDisplayed()))
    }

    @Test
    fun new_marker_is_displayed_in_detail() {
        onView(withText("Masjid Raya Bandung")).perform(click())
        onView(withText("Masjid Raya Bandung")).check(matches(isDisplayed()))
        onView(withText("Jl. Dalem Kaum No.14, Balonggede, Kec. Regol, Kota Bandung, Jawa Barat 40251")).check(
            matches(isDisplayed())
        )
        onView(withText(("This is a Mosque"))).check(matches(isDisplayed()))
        Thread.sleep(3000)
    }

    @Test
    fun edit_new_marker() {
        Thread.sleep(1000)
        onView(withText("Masjid Raya Bandung")).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.edit_marker_fab)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.name_input)).perform(replaceText("Museum Konferensi Asia Afrika"))
        Thread.sleep(1000)
        onView(withId(R.id.location_address_input)).perform(replaceText("Jl. Asia Afrika No.65, Braga, Kec. Sumur Bandung, Kota Bandung, Jawa Barat 40111"))
        Thread.sleep(1000)
        onView(withId(R.id.marker_checkbox)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.notes_input)).perform(replaceText("A museum dedicated to the Conference of newly independent Asian-African countries, in Bandung in 1955."))
        Thread.sleep(1000)
        onView(withId(R.id.save_btn)).perform(click())
        Thread.sleep(1000)
        onView(withText("Museum Konferensi Asia Afrika")).perform(click())
        onView(withText("Museum Konferensi Asia Afrika")).check(matches(isDisplayed()))
        onView(withText(("This is not a Mosque"))).check(matches(isDisplayed()))
        Thread.sleep(3000)
    }

    @Test
    fun delete_new_marker() {
        Thread.sleep(1000)
        onView(withText("Masjid Raya Bandung")).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.edit_marker_fab)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.delete_btn)).perform(click())
        Thread.sleep(1000)
        onView(withText("Masjid Raya Bandung")).check(doesNotExist())
        Thread.sleep(3000)
    }
}