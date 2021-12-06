package umn.ac.id.uas_suits.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import umn.ac.id.uas_suits.Order;
import umn.ac.id.uas_suits.R;
import umn.ac.id.uas_suits.Modal.BestSuit;


public class BestSuitAdapter<BestSuitViewHolder> extends RecyclerView.Adapter<BestSuitAdapter.BestSuitViewHolder> {

    Context context;
    List<BestSuit> bestSuitList;

    public BestSuitAdapter(Context context, List<BestSuit> bestSuitList) {
        this.context = context;
        this.bestSuitList = bestSuitList;
    }

    @NonNull
    @Override
    public BestSuitAdapter.BestSuitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.best_suit_row_item, parent, false);
        return new BestSuitAdapter.BestSuitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSuitAdapter.BestSuitViewHolder holder, int position) {


        holder.suitImage.setImageResource(bestSuitList.get(position).getImageUrl());
        holder.name.setText(bestSuitList.get(position).getName());
        holder.price.setText(bestSuitList.get(position).getPrice());
        holder.rating.setText(bestSuitList.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Order.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bestSuitList.size();
    }

    public static final class BestSuitViewHolder     extends RecyclerView.ViewHolder{

        ImageView suitImage;
        TextView price,name, rating, tailorName;

        public BestSuitViewHolder(@NonNull View itemView) {
            super(itemView);

            suitImage = itemView.findViewById(R.id.suit_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            tailorName = itemView.findViewById(R.id.tailor_name);

        }
    }
}

