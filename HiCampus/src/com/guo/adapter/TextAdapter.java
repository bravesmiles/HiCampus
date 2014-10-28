package com.guo.adapter;

import java.util.List;

import com.smiles.campus.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.guo.mywebsiteclient.R;

public class TextAdapter extends BaseAdapter {
	Activity context;
	List<String> textList;

	public TextAdapter(List<String> textList, Context context) {
		this.context = (Activity) context;
		this.textList = textList;
	}

	@Override
	public int getCount() {
		return textList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = context.getLayoutInflater().inflate(R.layout.item_custom_lisnear2, null);
		TextView way_name = (TextView) convertView.findViewById(R.id.offwall_item_title);
		way_name.setText(textList.get(position));
		return convertView;
	}
}
