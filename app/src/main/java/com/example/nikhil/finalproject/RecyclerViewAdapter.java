package com.example.nikhil.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<Recipient> recipients;
    private Context mContext;

    RecyclerViewAdapter(ArrayList<Recipient> recipients, Context mContext){
        this.recipients = recipients;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textViewLocation.setText(recipients.get(i).getLocation());
      //  viewHolder.textViewPosting.setText(recipients.get(i).getDate().toString());


    }

    @Override
    public int getItemCount() {
        return recipients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRequest, textViewLocation, textViewPosting;
        Button buttonAccept, buttonCant;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRequest = itemView.findViewById(R.id.textViewRequest);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewPosting = itemView.findViewById(R.id.textViewposting);
            buttonAccept = itemView.findViewById(R.id.buttonAccept);
            buttonCant = itemView.findViewById(R.id.buttonCant);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

}
