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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class recycleviewDonorProfile extends RecyclerView.Adapter<recycleviewDonorProfile.ViewHolder> {
    //data VDO 12:34 min
    private ArrayList<Donor> donorHistory;
    private Context mContext;


    recycleviewDonorProfile(ArrayList<Donor> donorHistory, Context mContext) {
        this.donorHistory = donorHistory;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_donorprofilelist, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textViewDntLocation.setText(donorHistory.get(i).getLocation());
        viewHolder.textViewDntType.setText(donorHistory.get(i).getDonateType());
        //viewHolder.textViewDntDate.setText(donorHistory.get(i).getCreatedDate());
        /*viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference donationRef = database.getReference("Donor");
                Donor r = donorHistory.get(i);
                donationRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String deletekey = dataSnapshot.getKey();
                        donationRef.child(deletekey).setValue(null);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                //if need toast and clickable: VDO 17:00

            }
        });*/
    }


    @Override
    public int getItemCount() {

        return donorHistory.size();


    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button buttonDelete;
        TextView textViewCount, textViewDntLocation, textViewDntDate, textViewDntType;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //buttonDelete = itemView.findViewById(R.id.buttonDelete);
            textViewCount = itemView.findViewById(R.id.textViewCount);
            textViewDntLocation = itemView.findViewById(R.id.textViewDntLocation);
            textViewDntDate = itemView.findViewById(R.id.textViewDntDate);
            textViewDntType = itemView.findViewById(R.id.textViewDntType);
            parentLayout = itemView.findViewById(R.id.parent_layout);




        }
    }

}

