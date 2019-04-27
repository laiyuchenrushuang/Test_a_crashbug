package com.example.laiji.test_a_crash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class MyAdapter extends BaseAdapter {
    private Context context = null;
    private  List list;
    private ViewHolder mHolder;

    public MyAdapter(Context context, List list_data) {
        this.context = context;
        this.list = list_data;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item, null, true);
            mHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            mHolder.text1 = (TextView) convertView.findViewById(R.id.text1);
            mHolder.text2 = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        String text_1 = list.get(position).toString();
        String text_2 = list.get(position).toString();
        mHolder.text1.setText(text_1);
        mHolder.text2.setText(text_2+text_1);
        mHolder.iv.setImageResource(R.drawable.ic_launcher_background);
        return convertView;
    }

    private class ViewHolder {
        ImageView iv;
        TextView text1;
        TextView text2;
    }
}
