package ca.mmess.calculatorwear;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Scanner;

public class Calculator {

    private final static String TAG = "Calculator";

    public Double currentDoubleValue;
    public Double oldDoubleValue = 0D;
    public String currentOperator = null;
    public String lastOperator = null;

    public void appendToDisplay(View view) {

        String currText = WearActivity.display.getText().toString();

        // get rid of leading zero when appropriate to do so
        if (currText.equals(String.valueOf("0"))) {
            Log.d(TAG, "display reads 0");

            // and not adding a '.'
            if (((Button) view).getText().equals(String.valueOf("."))) {
                Log.d(TAG, "adding .");
            } else {
                // else get rid of leading zero
                Log.d(TAG, "get rid of leading zero");
                WearActivity.setDisplay("");
            }
        }

        // append string to current display reading
        // unless adding additional decimal point
        if (currText.contains(String.valueOf(".")) && ((Button) view).getText().equals(String.valueOf("."))) {
            Log.d(TAG, "trying to add additional .");
        } else { // append string to current display reading
            Log.d(TAG, "appending to display");
            WearActivity.appendToDisplay(((Button) view).getText().toString());
        }
    }

    public void doCalculation(View view) {

        currentDoubleValue = stringToDouble(WearActivity.display.getText().toString());
        currentOperator = ((Button) view).getText().toString();

        String newDisplay = "";

        if (lastOperator != null && !WearActivity.waitingForSecondOperand) {
            if (lastOperator.equals("+")) {
                newDisplay = String.valueOf(oldDoubleValue + currentDoubleValue);

            } else if (lastOperator.equals("-")) {
                newDisplay = String.valueOf(oldDoubleValue - currentDoubleValue);

            } else if (lastOperator.equals("*")) {
                newDisplay = String.valueOf(oldDoubleValue * currentDoubleValue);

            }  else if (lastOperator.equals("^")) {

                newDisplay = String.valueOf(Math.pow(oldDoubleValue, currentDoubleValue));

            } else if (lastOperator.equals("%")) {

                newDisplay = String.valueOf(oldDoubleValue % currentDoubleValue);

            } else if (lastOperator.equals("/")) {
                // cannot divide by zero
                if (currentDoubleValue == 0) {
                    newDisplay = "NaN";
                } else {
                    newDisplay = String.valueOf(oldDoubleValue / currentDoubleValue);
                }
            }
        }

        if (!newDisplay.equals(String.valueOf("")))

        {
            WearActivity.setDisplay(newDisplay);
            currentDoubleValue = stringToDouble(newDisplay);
        }

        oldDoubleValue = currentDoubleValue;
        lastOperator = currentOperator;
    }

    public void doAdvCalculation(View view) {

        currentDoubleValue = stringToDouble(WearActivity.display.getText().toString());
        String newDisplay = "";

        switch (view.getId()) {

            case R.id.sinActionButton:
                newDisplay = String.valueOf(Math.sin(currentDoubleValue));
                break;

            case R.id.cosActionButton:
                newDisplay = String.valueOf(Math.cos(currentDoubleValue));
                break;

            case R.id.tanActionButton:
                newDisplay = String.valueOf(Math.tan(currentDoubleValue));
                break;

            case R.id.lnActionButton:
                newDisplay = String.valueOf(Math.log(currentDoubleValue));
                break;

            case R.id.logActionButton:
                newDisplay = String.valueOf(Math.log10(currentDoubleValue));
                break;

            case R.id.squareRootActionButton:
                if (currentDoubleValue == 0) {
                    newDisplay = "NaN";
                } else {
                    newDisplay = String.valueOf(Math.sqrt(currentDoubleValue));
                }
                Log.d(TAG, "square root calculated");
                break;

        }

        if (!newDisplay.equals(String.valueOf(""))) {
            WearActivity.setDisplay(newDisplay);
            currentDoubleValue = stringToDouble(newDisplay);
        }
    }

    public void clearDisplay() {
        currentDoubleValue = 0D;
        oldDoubleValue = 0D;
        currentOperator = null;
        lastOperator = null;

        WearActivity.setDisplay("0");
    }

    public void clearLast() {

        String oldReading = String.valueOf(WearActivity.display.getText());
        if (oldReading.length() > 0) {
            WearActivity.setDisplay(oldReading.substring(0, oldReading.length() - 1));
        }

        if (oldReading.length() == 1) WearActivity.setDisplay("0");
    }

    public String add(String opOne, String opTwo) {

        Double op1 = stringToDouble(opOne);
        Double op2 = stringToDouble(opTwo);

        return String.valueOf(op1 + op2);
    }

    public Double stringToDouble(String string) {

        Scanner scanner = new Scanner(string);

        // catch errors if string is not number
        try {
            while (!scanner.hasNextDouble()) {
                scanner.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
        return scanner.nextDouble();
    }

    public void setCurrentDoubleValue(String curDblVal) {
        this.currentDoubleValue = stringToDouble(curDblVal);
    }
}
