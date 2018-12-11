package com.example.nikhil.finalproject;

import android.content.Context;
import android.location.Location;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class recycleviewDonorProfile extends RecyclerView.Adapter<recycleviewDonorProfile.ViewHolder> {
    //data VDO 12:34 min
    private ArrayList<Donor> Location;
    private Context mContext;

    recycleviewDonorProfile(ArrayList<Donor> Location, Context mContext){
        this.Location = Location;
        this.mContext = mContext;



    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_donorprofilelist, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textViewQueryHistory.setText(Location.get(i).location);

        //if need toast and clickable: VDO 17:35
    }

    @Override
    public int getItemCount() {
        return Location.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button buttonDelete;
        TextView textViewQueryCount, textViewQueryHistory;
        RelativeLayout parentLayout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            textViewQueryCount = itemView.findViewById(R.id.textViewQueryCount);
            textViewQueryHistory = itemView.findViewById(R.id.textViewQueryHistory);
            parentLayout = itemView.findViewById(R.id.parent_layout);




        }
    }

}
