package com.example.nikhil.finalproject;

import android.content.Context;
import android.content.Intent;
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
    String recipientinfo;


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
        viewHolder.buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAccept = new Intent(mContext, AcceptPage.class);
                recipientinfo = "hi"; //change this to get recipient info which is clicked
                intentAccept.putExtra("text_value", recipientinfo);
                mContext.startActivity(intentAccept);
            }
        });

        viewHolder.buttonCant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCant = new Intent(mContext, CancelPage.class );
                mContext.startActivity(intentCant);
            }
        });


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
