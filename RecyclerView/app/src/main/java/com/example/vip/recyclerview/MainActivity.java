package com.example.vip.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RecyclerView能实现ListView的所有功能，并且还扩展了一些新功能，如横屏滚动和瀑布流布局
 * RecyclerView的排列方式除了LinearLayoutManager之外，
 * 还有GridLayoutManager（网格布局）和StaggeredGridLayoutManager（瀑布流布局）
 */
public class MainActivity extends AppCompatActivity {
	private List<Fruit> fruitList = new ArrayList<>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initFruitList();
//		initStaggeredFruitList();
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		//		设置为横向滚动
//		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//		StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
//			   StaggeredGridLayoutManager.VERTICAL);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(new FruitAdapter(fruitList));

	}

	private void initFruitList() {
		fruitList.add(new Fruit(R.drawable.apple, "苹果"));
		fruitList.add(new Fruit(R.drawable.banana, "香蕉"));
		fruitList.add(new Fruit(R.drawable.cherry, "cherry"));
		fruitList.add(new Fruit(R.drawable.grape, "葡萄"));
		fruitList.add(new Fruit(R.drawable.mango, "芒果"));
		fruitList.add(new Fruit(R.drawable.orange, "橙子"));
		fruitList.add(new Fruit(R.drawable.pear, "梨"));
		fruitList.add(new Fruit(R.drawable.strawberry, "草莓"));
		fruitList.add(new Fruit(R.drawable.watermelon, "西瓜"));
		fruitList.add(new Fruit(R.drawable.pineapple, "地菠萝"));
	}

	private void initStaggeredFruitList() {
		for (int i = 0; i < 2; i++) {
			fruitList.add(new Fruit(R.drawable.apple, getRandomLengthName("苹果")));
			fruitList.add(new Fruit(R.drawable.banana, getRandomLengthName("香蕉")));
			fruitList.add(new Fruit(R.drawable.cherry, getRandomLengthName("cherry")));
			fruitList.add(new Fruit(R.drawable.grape, getRandomLengthName("葡萄")));
			fruitList.add(new Fruit(R.drawable.mango, getRandomLengthName("芒果")));
			fruitList.add(new Fruit(R.drawable.orange, getRandomLengthName("橙子")));
			fruitList.add(new Fruit(R.drawable.pear, getRandomLengthName("梨")));
			fruitList.add(new Fruit(R.drawable.strawberry, getRandomLengthName("草莓")));
			fruitList.add(new Fruit(R.drawable.watermelon, getRandomLengthName("西瓜")));
			fruitList.add(new Fruit(R.drawable.pineapple, getRandomLengthName("地菠萝")));
		}
	}

	private String getRandomLengthName(String name) {
		Random random = new Random();
		int length = random.nextInt(20) + 1;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			buffer.append(name);
		}
		return buffer.toString();
	}

}

class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
	private List<Fruit> fruitList;

	static class ViewHolder extends RecyclerView.ViewHolder {
		ImageView imageView;
		TextView textView;
		View fruitView;

		public ViewHolder(View itemView) {
			super(itemView);
			fruitView=itemView;
			imageView = itemView.findViewById(R.id.fruit_img);
			textView = itemView.findViewById(R.id.fruit_name);

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
//		设置为竖滚动时，用R.layout.recycler_vertical_item布局
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
			   .recycler_horizontal_item, parent, false);
		ViewHolder holder = new ViewHolder(view);
//		注册点击事件
		holder.imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(view.getContext(), "点击了图片", Toast.LENGTH_SHORT).show();
			}
		});

		holder.fruitView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(view.getContext(), "点击了文本", Toast.LENGTH_SHORT).show();
			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Fruit fruit = fruitList.get(position);
		holder.imageView.setImageResource(fruit.getImageView());
		holder.textView.setText(fruit.getTextView());
	}


}

