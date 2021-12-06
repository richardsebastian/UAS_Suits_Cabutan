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
import umn.ac.id.uas_suits.Modal.PopularSuit;

public class PopularSuitAdapter<PopularSuitViewHolder> extends RecyclerView.Adapter<PopularSuitAdapter.PopularSuitViewHolder> {

    Context context;
    List<PopularSuit> popularSuitList;

    public PopularSuitAdapter(Context context, List<PopularSuit> popularSuitList) {
        this.context = context;
        this.popularSuitList = popularSuitList;
    }

    @NonNull
    @Override
    public PopularSuitAdapter.PopularSuitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.popular_suit_row_item, parent, false);
        return new PopularSuitAdapter.PopularSuitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularSuitAdapter.PopularSuitViewHolder holder, int position) {


        holder.suitImage.setImageResource(popularSuitList.get(position).getImageUrl());
        holder.name.setText(popularSuitList.get(position).getName());
        holder.price.setText(popularSuitList.get(position).getPrice());

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
        return popularSuitList.size();
    }

    public static final class PopularSuitViewHolder extends RecyclerView.ViewHolder{

        ImageView suitImage;
        TextView price,name;

        public PopularSuitViewHolder(@NonNull View itemView) {
            super(itemView);

            suitImage = itemView.findViewById(R.id.suit_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);



        }
    }
}

