package com.example.firebase;
import java.lang.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

//import android.support.test.espresso.contrib.RecyclerViewActions;

import android.os.SystemClock;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.filters.LargeTest;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class hometest {
//    ActivityScenario activityScenario = ActivityScenario.launch(home.class);
    @Rule
    public ActivityScenarioRule<home> activityRule =
            new ActivityScenarioRule<>(home.class);
    @Test
    public void test_add_btn() {
        onView(withId(R.id.home_page)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.addpage)).check(matches(isDisplayed()));
    }


    @Test
    public void add_information() {
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.date)).perform(ViewActions.typeText("22-07-2022"));
        onView(withId(R.id.time)).perform(ViewActions.typeText("12:25AM"));
        onView(withId(R.id.ss)).perform(ViewActions.typeText("102"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ds)).perform(ViewActions.typeText("80"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.hb)).perform(ViewActions.typeText("65"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.comment)).perform(ViewActions.typeText("shuye achi"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.home_page)).check(matches(isDisplayed()));
    }

    @Test public void added_info_test(){
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        onView(withId(R.id.home_page)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(2));
        SystemClock.sleep(3000);
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
        onView(withId(R.id.ddate)).check(matches(withText("22-07-2022")));
        onView(withId(R.id.dtime)).check(matches(withText("12:25AM")));
        onView(withId(R.id.dss)).check(matches(withText("102")));
        onView(withId(R.id.dds)).check(matches(withText("80")));
        onView(withId(R.id.dhb)).check(matches(withText("65")));

    }

    public static class MyViewAction {

        public static ViewAction clickChildViewWithId(int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    v.performClick();
                }
            };
        }

    }

    @Test
    public void edit_test() {
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(0));
        SystemClock.sleep(3000);
        onView(withId(R.id.list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, MyViewAction.clickChildViewWithId(R.id.Vedit)));
        SystemClock.sleep(3000);
//        onView(withId(R.id.edit_page)).check(matches(isDisplayed()));
        onView(withId(R.id.Edate)).perform(ViewActions.typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Etime)).perform(ViewActions.typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Ess)).perform(ViewActions.typeText(""));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.Eds)).perform(ViewActions.typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Ehb)).perform(ViewActions.typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Ecomment)).perform(ViewActions.typeText("before "));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.editbtn)).perform(click());
        onView(withId(R.id.home_page)).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }


    @Test
    public void delete_test() {
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(0));
        SystemClock.sleep(3000);
        onView(withId(R.id.list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.Vdelete)));

        SystemClock.sleep(3000);
        onView(withText("DELETE"))
                .perform(click());
    }

    @Test
    public void delete_cancel_test() {
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(0));
        SystemClock.sleep(3000);
        onView(withId(R.id.list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.Vdelete)));

        SystemClock.sleep(3000);
        onView(withText("CANCEL"))
                .perform(click());
    }


    @Test
    public void details_test() {
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        onView(withId(R.id.home_page)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(0));
        SystemClock.sleep(3000);
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.details_page)).check(matches(isDisplayed()));
        onView(withId(R.id.ddate)).check(matches(withText("22-07-2022")));
        SystemClock.sleep(3000);

    }

}



