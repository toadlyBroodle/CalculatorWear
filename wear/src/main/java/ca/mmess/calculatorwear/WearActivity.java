package ca.mmess.calculatorwear;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;

public class WearActivity extends Activity implements OnSharedPreferenceChangeListener {

    private static final String TAG = "WearActivity";
    public static final int DARK_BLUE = 0x0099CC;
    public static final int LIGHT_BLUE = 0x33B5E5;
    public static final int DARK_GREEN = 0x669900;
    public static final int LIGHT_GREEN = 0x99CC00;
    public static final int DARK_PURPLE = 0x9933CC;
    public static final int LIGHT_PURPLE = 0xAA66CC;
    public static final int DARK_RED = 0xCC0000;
    public static final int LIGHT_RED = 0xFF4444;
    public static final int DARK_ORANGE = 0xFF8800;
    public static final int LIGHT_ORANGE = 0xFFBB33;
    public static final int DARK_BLACK = 0x222222;
    public static final int LIGHT_GREY = 0xAAAAAA;

    public GridViewPager gridViewPager;
    public CustomGridPagerAdapter customGridPagerAdapter;
    public static SharedPreferences prefs;
    public static int prefsButColour;
    public static String prefsDisplayReading;

    public static CalculatorDisplay display;
    public static CalculatorDisplay displayAdv;
    public static Calculator calculator;
    public static Boolean waitingForSecondOperand = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear);

        // instantiate gridViewPager to handle watch view scrolling
        gridViewPager = (GridViewPager) findViewById(R.id.pager);
        gridViewPager.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                // Adjust page margins:
                //   A little extra horizontal spacing between pages looks a bit
                //   less crowded on a round display.
                final boolean round = insets.isRound();
                int rowMargin = 100;
                int colMargin = 10;
                if (round) {
                    colMargin = 50;
                } // pad round cols more

                gridViewPager.setPageMargins(rowMargin, colMargin);
                return insets;
            }
        });
        customGridPagerAdapter = new CustomGridPagerAdapter(this, getFragmentManager());
        gridViewPager.setAdapter(customGridPagerAdapter);

        calculator = new Calculator();

        Log.d(TAG, "onCreate() executed");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // get preferred button colour
        prefs = getPreferences(MODE_PRIVATE);
        prefsButColour = prefs.getInt("prefs_but_colour", 0x444444);
        prefsDisplayReading = prefs.getString("prefs_display_reading", "0");

        Log.d(TAG, "prefsDisplayReading =" + String.valueOf(prefsDisplayReading));
    }

    @Override
    protected void onPause() {
        super.onPause();

        // save preferred button colour
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putInt("prefs_but_colour", prefsButColour);
        editor.putString("prefs_display_reading", prefsDisplayReading);
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }

    public static void appendToDisplay(String toAppend) {

        display.append(toAppend);

        // get new text value of display and set to sharedValue so displays mirror eachother
        display.setSharedValue(display.getText().toString());
    }

    public static void setDisplay(String newDisp) {

        // get rid of trailing .000... TODO, not working
/*        String lastDigit = "";
        int length = newDisp.length() - 1;
        // if contains . and last number is 0
        if (newDisp.contains(".") && String.valueOf("0").equals(newDisp.charAt(length))) {
            // delete trailing zeros
            do {
                Log.d(TAG, "subtracting zero");
                length--;
            } while (String.valueOf("0").equals(newDisp.charAt(length)));
            // dump trailing .
            if (lastDigit.equals(String.valueOf("."))) {
                length--;
            }
            newDisp = newDisp.substring(0, length);
        }*/

        // remove trailing .0
        String last2Digs = "";
        if (newDisp.length() > 1) {
            last2Digs = newDisp.substring(-2 + newDisp.length(), newDisp.length());
        }
        if (last2Digs.equals(String.valueOf(".0"))) {
            newDisp = newDisp.substring(0, -2 + newDisp.length());
            Log.d(TAG, ".0 removed");
        }

        // shorten number to fit in display but keep "E..." on end if present
        if (newDisp.length() > 16) {
            if (newDisp.contains(String.valueOf("E"))) {
                int locOfE = newDisp.indexOf("E");
                String ending = newDisp.substring(locOfE, newDisp.length());
                String beginning = newDisp.substring(0, locOfE - ending.length() - 1);

                newDisp = beginning.concat(ending);
            } else
                newDisp = newDisp.substring(0, 16);
        }

        // set sharedValue to same as new display so advancedDisplay mirrors simpleDisplay
        display.setSharedValue(newDisp);

        // set text to display
        WearActivity.display.setText(newDisp);
    }

    // button methods
    public void onNumPushed(View view) {

        // reset display if waiting for new operand to be entered
        if (waitingForSecondOperand) {

            display.setText("0");

            waitingForSecondOperand = false;
        }

        calculator.appendToDisplay(view);
    }

    public void onOperationPushed(View view) {

        calculator.doCalculation(view);
        waitingForSecondOperand = true;
    }

    public void onDecimal(View view) {

        calculator.appendToDisplay(view);
    }

    // advanced button methods
    public void onAdvButtonPushed(View view) {

        switch (view.getId()) {

            case R.id.sinActionButton:
                calculator.doAdvCalculation(view);
                break;
            case R.id.cosActionButton:
                calculator.doAdvCalculation(view);
                break;
            case R.id.tanActionButton:
                calculator.doAdvCalculation(view);
                break;
            case R.id.lnActionButton:
                calculator.doAdvCalculation(view);
                break;
            case R.id.logActionButton:
                calculator.doAdvCalculation(view);
                break;
            // remainder handled as operator
            case R.id.piActionButton:
                setDisplay("3.14159265358979323846");
                break;
            case R.id.eActionButton:
                setDisplay("2.71828182845904523536");
                break;
            // power handled as operator
            case R.id.bracketOnActionButton:
                setDisplay("Coming soon...");
                break;
            case R.id.bracketOffActionButton:
                setDisplay("Coming soon...");
                break;
            case R.id.squareRootActionButton:
                calculator.doAdvCalculation(view);
                break;
        }

    }


    public void onColour(View view) {

        switch (view.getId()) {

            case R.id.but_dblue:
                prefsButColour = DARK_BLUE;
                //Log.d(TAG, "db switch statement");
                break;
            case R.id.but_dpurp:
                prefsButColour = DARK_PURPLE;
                break;
            case R.id.but_dblack:
                prefsButColour = DARK_BLACK;
                break;
            case R.id.but_lblue:
                prefsButColour = LIGHT_BLUE;
                break;
            case R.id.but_lpurp:
                prefsButColour = LIGHT_PURPLE;
                break;
            case R.id.but_lgrey:
                prefsButColour = LIGHT_GREY;
                break;
            case R.id.but_dgreen:
                prefsButColour = DARK_GREEN;
                break;
            case R.id.but_dorange:
                prefsButColour = DARK_ORANGE;
                break;
            case R.id.but_dred:
                prefsButColour = DARK_RED;
                break;
            case R.id.but_lgreen:
                prefsButColour = LIGHT_GREEN;
                break;
            case R.id.but_lorange:
                prefsButColour = LIGHT_ORANGE;
                break;
            case R.id.but_lred:
                prefsButColour = LIGHT_RED;
                break;
        }

        Log.d(TAG, String.valueOf(prefsButColour));

    }

}
