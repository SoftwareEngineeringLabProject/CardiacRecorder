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


    @Test
    public void item_test() {
        onView(withId(R.id.list)).check(matches(isDisplayed()));
        onView(withId(R.id.home_page)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(4));
        SystemClock.sleep(3000);
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(4,click()));
        SystemClock.sleep(3000);
//        onView(withId(R.id.list)).perform(
//                RecyclerViewActions.actionOnItemAtPosition(4, MyViewAction.clickChildViewWithId(R.id.Vedit)));
    }
}



//
//    public static ViewAction actionOnItemView(Matcher<View> matcher, ViewAction action) {
//
//        return new ViewAction() {
//
//            @Override public String getDescription() {
//                return String.format("performing ViewAction: %s on item matching: %s", action.getDescription(), StringDescription.asString(matcher));
//            }
//
//            @Override public Matcher<View> getConstraints() {
//                return allOf(withParent(isAssignableFrom(RecyclerView.class)), isDisplayed());
//            }
//
//            @Override public void perform(UiController uiController, View view) {
//                List<View> results = new ArrayList<>();
//                for (View v : TreeIterables.breadthFirstViewTraversal(view)) {
//                    if (matcher.matches(v)) results.add(v);
//                }
//                if (results.isEmpty()) {
//                    throw new RuntimeException(String.format("No view found %s", StringDescription.asString(matcher)));
//                } else if (results.size() > 1) {
//                    throw new RuntimeException(String.format("Ambiguous views found %s", StringDescription.asString(matcher)));
//                }
//                action.perform(uiController, results.get(0));
//            }
//        };
//    }
//public static class MyViewAction {
//
//    public static ViewAction clickChildViewWithId(int id) {
//        return new ViewAction() {
//            @Override
//            public Matcher<View> getConstraints() {
//                return null;
//            }
//
//            @Override
//            public String getDescription() {
//                return "Click on a child view with specified id.";
//            }
//
//            @Override
//            public void perform(UiController uiController, View view) {
//                View v = view.findViewById(id);
//                v.performClick();
//            }
//        };
//    }
//
//}