package com.guo.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guo.entity.Express;
//import com.guo.mywebsiteclient.R;
import com.smiles.campus.R;

public class ImageAndTextAdapter extends BaseAdapter {
	Activity context;
	List<Express> textList;

	public ImageAndTextAdapter(List<Express> textList, Context context) {
		this.context = (Activity) context;
		this.textList = textList;
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
		convertView = context.getLayoutInflater().inflate(R.layout.item_custom_lisnear, null);
		TextView way_name = (TextView) convertView.findViewById(R.id.offwall_item_title);
		way_name.setText(textList.get(position).toString());
		// account_name.setText(mylist.get(position).toString());
		return convertView;
	}
}
