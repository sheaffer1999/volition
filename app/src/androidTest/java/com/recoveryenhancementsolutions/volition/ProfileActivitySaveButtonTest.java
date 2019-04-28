package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProfileActivitySaveButtonTest {

  @Rule
  public final ActivityTestRule<ProfileActivity> activityTestRuleProfile = new ActivityTestRule<>(
      ProfileActivity.class, false, false);
  @Rule
  public final ActivityTestRule<HomeActivity> activityTestRuleHome = new ActivityTestRule<>(
      HomeActivity.class, false, false);

  /**
   * Prepares the testing environment with a testing database.
   */
  @Before
  public void prepare() {
    VolitionDatabase.setTestDatabase(InstrumentationRegistry.getTargetContext());
  }

  /**
   * Test the functionality of the save button when not in edit mode.
   */
  @Test
  public void testButtonCreateMode() {
    // Start the activity in create mode.
    final Intent i = new Intent();
    activityTestRuleProfile.launchActivity(i);

    // Fill the form with profile information.
    fillProfile();

    // Click on the "Record Answers" button.
    onView(withId(R.id.record_button)).perform(scrollTo(), click());

    // Check that the user is on the questionnaire confirmation activity.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        QuestionnaireConfirmActivity.class.getName());
  }

  @Test
  public void testButtonEditMode() {
    // Start the home activity.
    final Intent i = new Intent();
    activityTestRuleHome.launchActivity(i);

    // Delay thread for one second to allow slower devices to catch up.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
    // Switch to ProfileActivity and fill out the profile.
    // Led to AmbiguousViewMatcherException problems: onView(withId(R.id.core_navigation_profile)).perform(click());
    // So instead, using code generated by Espresso Test Recorder
    final ViewInteraction bottomNavigationItemView = onView(
        allOf(withId(R.id.core_navigation_profile), withContentDescription("Profile"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.core_navigation),
                    0),
                3),
            isDisplayed()));
    bottomNavigationItemView.perform(click());

    // Delay thread for one second to allow slower devices to catch up.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    fillProfile();

    // Click on the "Record Answers" button.
    onView(withId(R.id.record_button)).perform(scrollTo(), click());

    // Delay thread for one second to allow slower devices to catch up.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Check that the user is on the home activity.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        HomeActivity.class.getName());

    // Switch to activity activity.
    // onView(withId(R.id.core_navigation_activity)).perform(click());
    final ViewInteraction bottomNavigationItemView2 = onView(
        allOf(withId(R.id.core_navigation_activity), withContentDescription("Activity"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.core_navigation),
                    0),
                1),
            isDisplayed()));
    bottomNavigationItemView2.perform(click());

    // Delay thread for one second to allow slower devices to catch up.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Switch to ProfileActivity.
    // onView(withId(R.id.core_navigation_profile)).perform(click());
    final ViewInteraction bottomNavigationItemView3 = onView(
        allOf(withId(R.id.core_navigation_profile), withContentDescription("Profile"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.core_navigation),
                    0),
                3),
            isDisplayed()));
    bottomNavigationItemView3.perform(click());

    // Delay thread for one second to allow slower devices to catch up.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Click on the "Record Answers" button.
    onView(withId(R.id.record_button)).perform(scrollTo(), click());

    // Delay thread for one second to allow slower devices to catch up.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Check that the user is on the activity activity.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ActivityActivity.class.getName());

    // Switch to PlanActivity.
    // onView(withId(R.id.core_navigation_plan)).perform(click());
    ViewInteraction bottomNavigationItemView4 = onView(
        allOf(withId(R.id.core_navigation_plan), withContentDescription("Plan"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.core_navigation),
                    0),
                2),
            isDisplayed()));
    bottomNavigationItemView4.perform(click());

    // Delay thread for one second to allow slower devices to catch up.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Switch to ProfileActivity.
    // onView(withId(R.id.core_navigation_profile)).perform(click());
    final ViewInteraction bottomNavigationItemView5 = onView(
        allOf(withId(R.id.core_navigation_profile), withContentDescription("Profile"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.core_navigation),
                    0),
                3),
            isDisplayed()));
    bottomNavigationItemView5.perform(click());

    // Delay thread for one second to allow slower devices to catch up.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Click on the "Record Answers" button.
    onView(withId(R.id.record_button)).perform(scrollTo(), click());

    // Delay thread for one second to allow slower devices to catch up.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Check that the user is on PlanActivity.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        PlanActivity.class.getName());
  }

  /**
   * Fills the form with profile information.
   */
  private void fillProfile() {
    onView(withId(R.id.name)).perform(scrollTo(), replaceText("Hello World"));
    onView(withId(R.id.date_of_birth)).perform(scrollTo(), replaceText("Sep 9, 1989"));
    onView(withId(R.id.gender_spinner)).perform(scrollTo(), click());

    final DataInteraction appCompatTextView = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView"))))
        .atPosition(3);
    appCompatTextView.perform(click());

    onView(withId(R.id.radioClient)).perform(scrollTo(), click());
    onView(withId(R.id.radioOpiates)).perform(scrollTo(), click());
    onView(withId(R.id.use_type_spinner)).perform(scrollTo(), click());

    final DataInteraction appCompatTextView3 = onData(anything())
        .inAdapterView(childAtPosition(
            withClassName(is("android.widget.PopupWindow$PopupBackgroundView"))))
        .atPosition(2);
    appCompatTextView3.perform(click());

    onView(withId(R.id.clean_date)).perform(scrollTo(), replaceText("Jan 1, 2019"));
  }

  private static Matcher<View> childAtPosition(
      final Matcher<View> parentMatcher) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position 0 in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
            && view.equals(((ViewGroup) parent).getChildAt(0));
      }
    };
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
  private static final String TAG = "ProfileActivitySaveButtonTest";
}