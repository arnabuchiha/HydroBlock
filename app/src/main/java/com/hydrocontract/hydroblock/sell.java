package com.hydrocontract.hydroblock;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ramotion.fluidslider.FluidSlider;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;



public class sell extends Fragment {
    int quantity;
    public sell(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_sell);

        final int max = 130;
        final int min = 10;
        final int total = max - min;

        final FluidSlider slider = dialog.findViewById(R.id.slider);
        slider.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return Unit.INSTANCE;
            }
        });

        slider.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return Unit.INSTANCE;
            }
        });

        // Java 8 lambda
        slider.setPositionListener(pos -> {
            final String value = String.valueOf((int) (min + total * pos));
            slider.setBubbleText(value);
            quantity=Integer.parseInt(value);
            return Unit.INSTANCE;
        });
        System.out.println(quantity);
        slider.setPosition(0.3f);
        slider.setStartText(String.valueOf(min));
        slider.setEndText(String.valueOf(max));
        dialog.show();

        return inflater.inflate(R.layout.fragment_sell, container, false);

    }
}
