package com.akarmoon.mosquemarker

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IndonesiaInstrumentedTest {
    @Before
    fun setup() {
        launchActivity<MainActivity>()
        onView(withId(R.id.add_marker_fab)).perform(ViewActions.click())
        onView(withId(R.id.name_input))
            .perform(ViewActions.replaceText("Masjid Raya Bandung"))
        onView(withId(R.id.location_address_input))
            .perform(ViewActions.replaceText("Jl. Dalem Kaum No.14, Balonggede, Kec. Regol, Kota Bandung, Jawa Barat 40251"))
        onView(withId(R.id.marker_checkbox)).perform(ViewActions.click())
        onView(withId(R.id.notes_input))
            .perform(ViewActions.replaceText("Masjid luas yang didirikan pada abad ke-19 dengan atap kubah yang besar, dan menara kembar 81 m."))
        onView(withId(R.id.save_btn)).perform(ViewActions.click())
    }

    @Test
    fun new_marker_is_displayed_in_list() {
        onView(ViewMatchers.withText("Masjid Raya Bandung"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
    }

    @Test
    fun new_marker_is_displayed_in_detail() {
        onView(ViewMatchers.withText("Masjid Raya Bandung")).perform(ViewActions.click())
        onView(ViewMatchers.withText("Masjid Raya Bandung"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("Jl. Dalem Kaum No.14, Balonggede, Kec. Regol, Kota Bandung, Jawa Barat 40251"))
            .check(
                ViewAssertions.matches(ViewMatchers.isDisplayed())
            )
        onView(ViewMatchers.withText(("Ini Mesjid")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
    }

    @Test
    fun edit_new_marker() {
        Thread.sleep(1000)
        onView(ViewMatchers.withText("Masjid Raya Bandung")).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(withId(R.id.edit_marker_fab)).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(withId(R.id.name_input))
            .perform(ViewActions.replaceText("Museum Konferensi Asia Afrika"))
        Thread.sleep(1000)
        onView(withId(R.id.location_address_input))
            .perform(ViewActions.replaceText("Jl. Asia Afrika No.65, Braga, Kec. Sumur Bandung, Kota Bandung, Jawa Barat 40111"))
        Thread.sleep(1000)
        onView(withId(R.id.marker_checkbox)).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(withId(R.id.notes_input))
            .perform(ViewActions.replaceText("Museum yang didedikasikan untuk Konferensi negara Asia-Afrika yang baru merdeka, di Bandung tahun 1955."))
        Thread.sleep(1000)
        onView(withId(R.id.save_btn)).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(ViewMatchers.withText("Museum Konferensi Asia Afrika"))
            .perform(ViewActions.click())
        onView(ViewMatchers.withText("Museum Konferensi Asia Afrika"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText(("Ini bukan Mesjid")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000)
    }

    @Test
    fun delete_new_marker() {
        Thread.sleep(1000)
        onView(ViewMatchers.withText("Masjid Raya Bandung")).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(withId(R.id.edit_marker_fab)).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(withId(R.id.delete_btn)).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(ViewMatchers.withText("Masjid Raya Bandung"))
            .check(ViewAssertions.doesNotExist())
        Thread.sleep(3000)
    }
}