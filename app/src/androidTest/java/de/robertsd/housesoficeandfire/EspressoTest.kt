package de.robertsd.housesoficeandfire

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import de.robertsd.housesoficeandfire.activities.HousesActivity
import de.robertsd.housesoficeandfire.helper.RecyclerViewAction.Companion.withRecyclerView
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class EspressoTest {

    @get:Rule
    var activityRule: ActivityTestRule<HousesActivity> = ActivityTestRule(HousesActivity::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(activityRule.activity.viewModel.idlingResource)
    }

    @Test
    fun test1() {
        onView(
            withRecyclerView(R.id.rvHouses).atPositionOnView(
                1,
                R.id.tvHouseName
            )
        ).check(matches(withText("House Allyrion of Godsgrace")))
        onView(withRecyclerView(R.id.rvHouses).atPosition(1)).perform(click())
        onView(withId(R.id.tvHouseName)).check(matches(withText("House Allyrion of Godsgrace")))
        onView(withId(R.id.tvHouseID)).check(matches(withText("ID: 2")))
        onView(withId(R.id.tvHouseRegion)).check(matches(withText("Region: Dorne")))
        onView(withId(R.id.tvHouseCoatOfArms)).check(matches(withText("Coat Of Arms: Gyronny Gules and Sable, a hand couped Or")))
        onView(withId(R.id.tvHouseTitles)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tvHouseLord)).check(matches(withText("Lord: Delonne Allyrion")))
        onView(withId(R.id.tvAmountOfSwornMembers)).check(matches(withText("Amount sworn members: 4")))
    }
}