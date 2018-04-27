package com.example.vip.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vip on 2017/3/11.
 */
//
public class MyAdapter<T> extends BaseAdapter {
    private Context context;
    private int[] images;
    private String[] names;

    public MyAdapter(Context context, int[] images, String[] names) {
        this.context = context;
        this.images = images;
        this.names = names;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.iamge);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image.setImageResource(images[position]);
        holder.name.setText(names[position]);
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView name;
    }
}

