package com.sphtechapp.myapplicationsph2.activities


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sphtechapp.myapplicationsph2.R
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ListActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(ListActivity::class.java)

    @Test
    fun listActivityTest() {

        Thread.sleep(4000)

        val textView = onView(
            allOf(
                withId(R.id.item_year), withText("2008"),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.cardview.widget.CardView::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("2008")))

        val textView2 = onView(
            allOf(
                withId(R.id.item_year), withText("2015"),
                withParent(withParent(IsInstanceOf.instanceOf(androidx.cardview.widget.CardView::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("2015")))

        onView(allOf(
            withId(R.id.item_vol_decrease),
            nthChildsDescendant(withId(R.id.recycler_view), 5), isDisplayed()))

        onView(allOf(
            withId(R.id.item_vol_decrease),
            nthChildsDescendant(withId(R.id.recycler_view), 5)))
            .perform(click())

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(
            CoreMatchers.endsWith("2018")
        ))))

    }

    private fun nthChildsDescendant(parentMatcher: Matcher<View?>, childPosition: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            override fun describeTo(_description: Description?) {
                _description?.appendText("with $childPosition child view of type parentMatcher")
            }

            override fun matchesSafely(_view: View?): Boolean {
                var view: View = _view!!
                while (view.getParent() != null) {
                    if (parentMatcher.matches(view.getParent())) {
                        return view.equals((view.getParent() as ViewGroup).getChildAt(childPosition))
                    }
                    view = view.getParent() as View
                }
                return false
            }
        }
    }
}
