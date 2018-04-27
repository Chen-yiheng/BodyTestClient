package com.perry.nightmode;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22 0022.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
    private List<Fruit> fruitList;
    private int itemLayoutId;


    public RecyclerViewAdapter(List<Fruit> fruitList, int itemLayoutId) {
        this.fruitList = fruitList;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        final Holder holder = new Holder(view);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Toast.makeText(view.getContext(), "you click" + fruitList.get(position).getName()
                        , Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        holder.fruitIntroduce.setText(fruit.getIntroduce());
        Glide.with(holder.view.getContext()).load(fruit.getImageId()).into(holder.fruitImage);

    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }


    static class Holder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        TextView fruitIntroduce;
        View view;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            fruitImage = itemView.findViewById(R.id.image_item);
            fruitName = itemView.findViewById(R.id.fruit_name);
            fruitIntroduce = itemView.findViewById(R.id.fruit_introduce);
        }
    }

}
