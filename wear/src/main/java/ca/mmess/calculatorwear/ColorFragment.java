package ca.mmess.calculatorwear;

import android.app.Fragment;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ColorFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_color, container, false);

        //style buttons
        final WatchViewStub stub = (WatchViewStub) view.findViewById(R.id.frag_color_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                Button but1 = (Button) view.findViewById(R.id.but_dblue);
                Button but2 = (Button) view.findViewById(R.id.but_dpurp);
                Button but3 = (Button) view.findViewById(R.id.but_dblack);
                Button but4 = (Button) view.findViewById(R.id.but_lblue);
                Button but5 = (Button) view.findViewById(R.id.but_lpurp);
                Button but6 = (Button) view.findViewById(R.id.but_lgrey);
                Button but7 = (Button) view.findViewById(R.id.but_dgreen);
                Button but8 = (Button) view.findViewById(R.id.but_dorange);
                Button but9 = (Button) view.findViewById(R.id.but_dred);
                Button but10 = (Button) view.findViewById(R.id.but_lgreen);
                Button but11 = (Button) view.findViewById(R.id.but_lorange);
                Button but12 = (Button) view.findViewById(R.id.but_lred);
                but1.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.DARK_BLUE));
                but4.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.LIGHT_BLUE));
                but7.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.DARK_GREEN));
                but10.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.LIGHT_GREEN));
                but2.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.DARK_PURPLE));
                but5.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.LIGHT_PURPLE));
                but8.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.DARK_ORANGE));
                but11.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.LIGHT_ORANGE));
                but3.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.DARK_BLACK));
                but6.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.LIGHT_GREY));
                but9.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.DARK_RED));
                but12.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, WearActivity.LIGHT_RED));

            }
        });
        return view;
    }

}
