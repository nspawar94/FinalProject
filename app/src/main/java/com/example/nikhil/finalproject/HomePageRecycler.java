package com.example.nikhil.finalproject;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
        import android.text.format.DateFormat;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import java.util.ArrayList;

public class HomePageRecycler extends RecyclerView.Adapter<HomePageRecycler.ViewHolder>{

    private ArrayList<Recipient> recipients;
    private Context mContext;

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
        viewHolder.textViewPosting.setText(recipients.get(i).getDate().toString());
    }


    @Override
    public int getItemCount() {
        return recipients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRequest, textViewLocation, textViewPosting;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRequest = itemView.findViewById(R.id.textViewRequest);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewPosting = itemView.findViewById(R.id.textViewposting);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

}
