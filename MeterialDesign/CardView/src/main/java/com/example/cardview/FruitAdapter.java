package com.example.cardview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 加载比较大的图片视频时，要用Glide，否则会有内存溢出
 */

class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
	private Context mContext;
	private List<Fruit> fruitList;

	static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView fruitImg;
		TextView fruitName;
		View view;

		public ViewHolder(View itemView) {
			super(itemView);
			view = itemView;
			fruitImg = (ImageView) itemView.findViewById(R.id.fruit_img);
			fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
		}
	}

	public FruitAdapter(List<Fruit> fruitList) {
		this.fruitList = fruitList;
	}

	@Override
	public int getItemCount() {
		return fruitList.size();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,
			   parent, false);
		if (mContext == null) {
			mContext = view.getContext();
		}
		final ViewHolder holder = new ViewHolder(view);
		holder.view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int position = holder.getAdapterPosition();
				Fruit fruit = fruitList.get(position);
				Intent intent = new Intent(mContext, FruitActivity.class);
				intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
				intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
				mContext.startActivity(intent);
			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Fruit fruit = fruitList.get(position);
		holder.fruitName.setText(fruit.getName());
		Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImg);
	}
}


