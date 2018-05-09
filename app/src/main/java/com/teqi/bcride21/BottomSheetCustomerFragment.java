package com.teqi.bcride21;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teqi.bcride21.Common.Common;
import com.teqi.bcride21.Remote.IGoogleAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//ep14 02:59
//ep17
public class BottomSheetCustomerFragment extends BottomSheetDialogFragment {
    String mLocation,mDestination;

    boolean isTapOnmap;

    IGoogleAPI mService;

    TextView txtCalculate, txtLocation,txtDestination;

    public static BottomSheetCustomerFragment newInstance(String location, String destination,boolean isTapOnmap)
    {
        BottomSheetCustomerFragment f = new BottomSheetCustomerFragment();
        Bundle args = new Bundle();
        args.putString("location",location);
        args.putString("destination",destination);
        args.putBoolean("IsTapOnmap",isTapOnmap);
        f.setArguments(args);
        return f;
    }
    //press Ctl+O (OnCreate)

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = getArguments().getString("location");
        mDestination = getArguments().getString("destination");
        isTapOnmap = getArguments().getBoolean("isTapOnmap");
    }
    //Ctlr+O (OnCreateView)

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_customer,container,false);
        txtLocation = (TextView)view.findViewById(R.id.txtLocation);
        txtDestination = (TextView)view.findViewById(R.id.txtDestination);
        txtCalculate = (TextView)view.findViewById(R.id.txtCalculate);//ep14 17:30, ep17 0244

        mService = Common.getGoogleService();
        getPrice(mLocation,mDestination);

        if (!isTapOnmap)
        {
            txtLocation.setText(mLocation);
            txtDestination.setText(mDestination);//ep16 0914, ep17 0317
        }

        return view;
    }
    //ep14 0920 and 1140
    private void getPrice(String mLocation, String mDestination) {
        String requestUrl=null;
        try
        {
            requestUrl = "https://maps.googleapis.com/maps/api/direction/jason?"+
                    "mode=driving&"
                    +"transit_routing_preference=less_driving&"
                    +"origin="+mLocation+"&"
                    +"destination="+mDestination+"&"
                    +"key="+getResources().getString(R.string.google_browser_key);//ep16 1214
            Log.e("LINK",requestUrl);
            mService.getPath(requestUrl).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().toString());
                        JSONArray routes = jsonObject.getJSONArray("routes");

                        JSONObject object = routes.getJSONObject(0);
                        JSONArray legs = object.getJSONArray("legs");

                        JSONObject legsObject = legs.getJSONObject(0);

                        JSONObject distance = legsObject.getJSONObject("distance");
                        String distance_text = distance.getString("text");

                        Double distance_value = Double.parseDouble(distance_text.replaceAll("[^0-9\\\\.]+", ""));//ep16 1512

                        JSONObject time = legsObject.getJSONObject("duration");
                        String time_text = time.getString("text");
                        Integer time_value = Integer.parseInt(time_text.replaceAll("\\D+", ""));//ep16 1733

                        String final_calculate = String.format("%s + %s = $%.2f", distance_text, time_text,
                                Common.getPrice(distance_value, time_value));//1733 ep16

                        txtCalculate.setText(final_calculate);

                        if (isTapOnmap)
                        {
                            String start_address = legsObject.getString("start_address");
                            String end_address = legsObject.getString("end_address");

                            txtLocation.setText(start_address);
                            txtDestination.setText(end_address);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("ERROR",t.getMessage());

                }
            });
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
