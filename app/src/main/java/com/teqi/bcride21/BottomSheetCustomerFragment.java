package com.teqi.bcride21;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//ep14 02:59
public class BottomSheetCustomerFragment extends BottomSheetDialogFragment {
    String mLocation,mDestination;

    public static BottomSheetCustomerFragment newInstance(String location, String destination)
    {
        BottomSheetCustomerFragment f = new BottomSheetCustomerFragment();
        Bundle args = new Bundle();
        args.putString("location",location);
        args.putString("destination",destination);
        f.setArguments(args);
        return f;
    }
    //press Ctl+O (OnCreate)

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = getArguments().getString("location");
        mDestination = getArguments().getString("destination");
    }
    //Ctlr+O (OnCreateView)

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_customer,container,false);
        TextView txtLocation = (TextView)view.findViewById(R.id.txtLocation);
        TextView txtDestination = (TextView)view.findViewById(R.id.txtDestination);
        TextView txtCalculate = (TextView)view.findViewById(R.id.txtCalculate);//ep14 17:30

        txtLocation.setText(mLocation);
        txtDestination.setText(mDestination);

        return view;
    }
}
