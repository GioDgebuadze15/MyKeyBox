package com.example.mykeybox;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Dialog extends BottomSheetDialogFragment {

//    private FragmentSecondBinding binding;
    private TextView textViewDialog ;
    private Button btn_visit ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_dialog, container, false);
        textViewDialog = view.findViewById(R.id.textViewDialog);
        btn_visit = view.findViewById(R.id.btn_visit);
//        binding = FragmentSecondBinding.inflate(inflater, container, false);




        return view;
    }


}
