package com.example.telemedizin.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.telemedizin.R;

public class PatientenViewHolder extends RecyclerView.ViewHolder {

    public TextView mFullName;
    public ImageView mRemoveBtn;
    public LinearLayout patientenItem;
    public PatientenViewHolder(View itemView) {
        super(itemView);
        mFullName = itemView.findViewById(R.id.patientenName);
        mRemoveBtn = itemView.findViewById(R.id.removePatientBtn);
        patientenItem = itemView.findViewById(R.id.patientenItemRoot);
    }
}