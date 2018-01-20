package ca.mmess.calculatorwear;

import android.app.Fragment;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AdvancedFragment extends Fragment {

    Button but1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("AdvancedFragment", "AdvancedFragment onCreateView() called");

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_advanced, container, false);

        //style buttons
        final WatchViewStub stub = (WatchViewStub) view.findViewById(R.id.frag_advanced_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                // DRUNK CODE BEGINS

                // setup display here after layout inflated
                WearActivity.displayAdv = (CalculatorDisplay) view.findViewById((R.id.adv_display));

                // dont think we need these two for the advancedDisplay because we're just going
                // to piggy back the value from the simpleDisplay elsewhere
//                WearActivity.setDisplay(String.valueOf(WearActivity.prefsDisplayReading));

//                WearActivity.calculator.setCurrentDoubleValue(WearActivity.prefsDisplayReading);

                // setup display onClick listener
                WearActivity.displayAdv.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        // no worries because calculator just calls WearActivities setDisplay functions
                        WearActivity.calculator.clearLast();

                        Log.d("CalculatorDisplay", "onClick");

                    }
                });

                // setup display onLongClick listener
                WearActivity.display.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {

                        // dito
                        WearActivity.calculator.clearDisplay();

                        Log.d("CalculatorDisplay", "onLongClick");

                        return false;
                    }
                });

                // DRUNK CODE ENDS


                but1 = (Button) view.findViewById(R.id.sinActionButton);
                Button but2 = (Button) view.findViewById(R.id.cosActionButton);
                Button but3 = (Button) view.findViewById(R.id.tanActionButton);
                Button but4 = (Button) view.findViewById(R.id.lnActionButton);
                Button but5 = (Button) view.findViewById(R.id.logActionButton);
                Button but6 = (Button) view.findViewById(R.id.remainderActionButton);
                Button but7 = (Button) view.findViewById(R.id.piActionButton);
                Button but8 = (Button) view.findViewById(R.id.eActionButton);
                Button but9 = (Button) view.findViewById(R.id.powerActionButton);
                Button but10 = (Button) view.findViewById(R.id.bracketOnActionButton);
                Button but11 = (Button) view.findViewById(R.id.bracketOffActionButton);
                Button but12 = (Button) view.findViewById(R.id.squareRootActionButton);

                but1.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but2.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but3.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but4.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but5.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but6.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but7.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but8.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but9.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but10.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but11.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but12.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            but1.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));

        } catch (Exception e) {
            Log.d("AdvancedFragment", e.toString());
        }

        Log.d("AdvancedFragment", "onResume() called");
    }

}
