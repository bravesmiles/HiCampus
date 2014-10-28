package com.guo.adapter;

import java.util.List;

import com.smiles.campus.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.guo.mywebsiteclient.R;

public class ImageAndTextAdapterNoIntro extends BaseAdapter {
	Activity context;
	List<String> textList;
	List<Integer> icons;

	public ImageAndTextAdapterNoIntro(List<String> textList, List<Integer> icons, Context context) {
		this.context = (Activity) context;
		this.textList = textList;
		this.icons = icons;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return textList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = context.getLayoutInflater().inflate(R.layout.item_custom_lisnear2, null);
		TextView way_name = (TextView) convertView.findViewById(R.id.offwall_item_title);
		ImageView icon = (ImageView) convertView.findViewById(R.id.item_custom_imgv);
		way_name.setText(textList.get(position).toString());
		icon.setImageResource(icons.get(position));
		// account_name.setText(mylist.get(position).toString());
		return convertView;
	}
}
