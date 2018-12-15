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

public class HomePageRecycler extends RecyclerView.Adapter<HomePageRecycler.ViewHolder>{

    private ArrayList<Recipient> recipients;
    private Context mContext;
    String recipientinfo;

    HomePageRecycler(ArrayList<Recipient> recipients, Context mContext){
        this.recipients = recipients;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listhome, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textViewLocation.setText(recipients.get(i).getLocation());
 //       viewHolder.textViewPosting.setText(recipients.get(i).getDate().toString());
        viewHolder.buttonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAccept = new Intent(mContext, RequestStatusPage.class);
                Recipient r = recipients.get(i);
                recipientinfo = r.getRecipientID();
                intentAccept.putExtra("Recipient ID", recipientinfo);
                mContext.startActivity(intentAccept);

            }
        });
    }


    @Override
    public int getItemCount() {
        return recipients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView  textViewLocation, textViewPosting;
        Button buttonStatus;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewPosting = itemView.findViewById(R.id.textViewposting);
            buttonStatus = itemView.findViewById(R.id.buttonStatus);
            parentLayout =itemView.findViewById(R.id.parent_layout);

        }
    }

}
