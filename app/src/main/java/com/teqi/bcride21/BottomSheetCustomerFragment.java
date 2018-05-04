package com.teqi.bcride21;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BottomSheetCustomerFragment extends BottomSheetDialogFragment {
    String mTag;

    public static BottomSheetCustomerFragment newInstance(String tag)
    {
        BottomSheetCustomerFragment f = new BottomSheetCustomerFragment();
        Bundle args = new Bundle();
        args.putString("TAG",tag);
        f.setArguments(args);
        return f;
    }
    //press Ctl+O (OnCreate)

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTag = getArguments().getString("TAG");
    }
    //Ctlr+O (OnCreateView)

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_customer,container,false);
        return view;
    }
}
