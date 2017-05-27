package com.example.test.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.test.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by liubin on 2017/5/26.
 */
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule = new ActivityTestRule<>(ListActivity.class);


    @Test
    public void testclickAddBook() throws Exception{
        onView(withId(R.id.tv_addNews)).perform(click());

        Intent resultData = new Intent();
        String phoneNumber = "123-345-6789";
        resultData.putExtra("phone", phoneNumber);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(toPackage(mActivityRule.getActivity().getPackageName())).respondWith(result);

        // Assert that data we set up above is shown.
//        onView(withId(R.id.phoneNumber).check(matches(withText(phoneNumber)));
    }


    @Test
    public void testclickAtPosition() throws Exception{
        onView(withId(R.id.rlv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.book_title)).check(matches(withText("Clean Code")));
        onView(withId(R.id.book_author)).check(matches(withText("Robert C. Martin")));
    }

    @Test
    public void testclickOnViewInRow() throws Exception {
        onView(withId(R.id.rlv)).perform(RecyclerViewActions.actionOnItem(
                hasDescendant(withText("Clean Code")), click()));

        onView(withId(R.id.book_title)).check(matches(withText("Clean Code")));
    }


}