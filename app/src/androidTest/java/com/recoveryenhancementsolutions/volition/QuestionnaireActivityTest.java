package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class QuestionnaireActivityTest {

  @Rule
  public ActivityTestRule<QuestionnaireActivity> mActivityTestRule = new ActivityTestRule<>(
      QuestionnaireActivity.class);

  @Test
  public void questionnaireActivityTest() {
    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                14),
            isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.YESbtn), withText("Yes"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                14),
            isDisplayed()));
    appCompatButton2.perform(click());

    ViewInteraction appCompatButton3 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                15),
            isDisplayed()));
    appCompatButton3.perform(click());

    ViewInteraction appCompatButton4 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                15),
            isDisplayed()));
    appCompatButton4.perform(click());

    ViewInteraction appCompatButton5 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                15),
            isDisplayed()));
    appCompatButton5.perform(click());

    ViewInteraction appCompatButton6 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                15),
            isDisplayed()));
    appCompatButton6.perform(click());

    ViewInteraction appCompatButton7 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                15),
            isDisplayed()));
    appCompatButton7.perform(click());

    ViewInteraction appCompatButton8 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                15),
            isDisplayed()));
    appCompatButton8.perform(click());

    ViewInteraction appCompatButton9 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                15),
            isDisplayed()));
    appCompatButton9.perform(click());

    ViewInteraction appCompatButton10 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                15),
            isDisplayed()));
    appCompatButton10.perform(click());

    ViewInteraction appCompatButton11 = onView(
        allOf(withId(R.id.NObtn), withText("No"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                15),
            isDisplayed()));
    appCompatButton11.perform(click());
  }

  private static Matcher<View> childAtPosition(
      final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
            && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
