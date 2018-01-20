package ca.mmess.calculatorwear;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.app.Fragment;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SimpleFragment extends Fragment {

    Button but1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("SampleFragment", "SampleFragment onCreateView() called");

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_simple, container, false);

        //style buttons
        final WatchViewStub stub = (WatchViewStub) view.findViewById(R.id.frag_simple_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                // setup display here after layout inflated
                WearActivity.display = (CalculatorDisplay) view.findViewById((R.id.display));

                WearActivity.setDisplay(String.valueOf(WearActivity.prefsDisplayReading));

                WearActivity.calculator.setCurrentDoubleValue(WearActivity.prefsDisplayReading);

                // setup display onClick listener
                WearActivity.display.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        WearActivity.calculator.clearLast();

                        Log.d("CalculatorDisplay", "onClick");

                    }
                });

                // setup display onLongClick listener
                WearActivity.display.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {

                        WearActivity.calculator.clearDisplay();

                        Log.d("CalculatorDisplay", "onLongClick");

                        return false;
                    }
                });

                but1 = (Button) view.findViewById(R.id.one);
                Button but2 = (Button) view.findViewById(R.id.two);
                Button but3 = (Button) view.findViewById(R.id.three);
                Button but4 = (Button) view.findViewById(R.id.four);
                Button but5 = (Button) view.findViewById(R.id.five);
                Button but6 = (Button) view.findViewById(R.id.six);
                Button but7 = (Button) view.findViewById(R.id.seven);
                Button but8 = (Button) view.findViewById(R.id.eight);
                Button but9 = (Button) view.findViewById(R.id.nine);
                Button but10 = (Button) view.findViewById(R.id.zero);
                Button but11 = (Button) view.findViewById(R.id.divide);
                Button but12 = (Button) view.findViewById(R.id.multiply);
                Button but13 = (Button) view.findViewById(R.id.minus);
                Button but14 = (Button) view.findViewById(R.id.plus);
                Button but15 = (Button) view.findViewById(R.id.point);
                Button but16 = (Button) view.findViewById(R.id.equals);

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
                but13.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but14.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but15.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));
                but16.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.prefsButColour));


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
            Log.d("SimpleFragment", e.toString());
        }

        Log.d("SimpleFragment", "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();


    }

}
