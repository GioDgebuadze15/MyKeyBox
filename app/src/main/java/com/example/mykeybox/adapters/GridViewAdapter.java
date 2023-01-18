package com.example.mykeybox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.mykeybox.R;
import com.example.mykeybox.Repository;

public class GridViewAdapter extends BaseAdapter {
    Context mContext;
    private Repository repository;
    private static final int totalBtn = 4;

    public GridViewAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return totalBtn;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        // !!!!!!!!!!! correct this
        Button btn;

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.grid_card_item, parent, false);
        }
        btn = view.findViewById(R.id.buttonGridView);
        btn.setText("Button " + ++i);

        repository = new Repository(mContext);
        boolean initialized = repository.initializeDrivers();

        if (initialized) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    repository.writeIntoPort(btn);
                }
            });
        }

        return view;
    }

}
