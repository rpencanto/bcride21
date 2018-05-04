package com.teqi.bcride21.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.teqi.bcride21.R;

public class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {
    //Ctrl+I
    View myView;
    //Alt+Insert(Constructor)

    public CustomInfoWindow(Context context) {
        myView = LayoutInflater.from(context)
                .inflate(R.layout.custom_customer_info_window,null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        TextView txtpickuptitle = ((TextView)myView.findViewById(R.id.txtPickupInfo));
        txtpickuptitle.setText(marker.getTitle());

        TextView txtPickupSnippet = ((TextView)myView.findViewById(R.id.txtPickupSnippet));
        txtPickupSnippet.setText(marker.getSnippet());

        return myView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
