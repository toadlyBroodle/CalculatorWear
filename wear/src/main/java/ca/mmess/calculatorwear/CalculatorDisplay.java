package ca.mmess.calculatorwear;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class CalculatorDisplay extends TextView {

    private final String TAG = "CalculatorDisplay";
    public static String sharedValue;

    public CalculatorDisplay(Context context) {
        super(context);
        init();
    }

    public CalculatorDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalculatorDisplay(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        sharedValue = "0";
    }

    public void setSharedValue(String string) {
        sharedValue = string;

        // set advancedDisplay so it mirrors simpleDisplay
        try {
            WearActivity.displayAdv.setText(sharedValue);

        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    }


}
