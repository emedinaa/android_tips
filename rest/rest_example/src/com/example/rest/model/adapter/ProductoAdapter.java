package com.example.rest.model.adapter;

import java.util.List;

import com.example.rest.R;
import com.example.rest.model.ProductoVo;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductoAdapter extends ArrayAdapter<ProductoVo> {

	private List<ProductoVo> data;
	Activity context;
	
	public ProductoAdapter(Activity _context, int resource, List<ProductoVo> _objects) {
		super(_context, resource, _objects);
		// TODO Auto-generated constructor stub
		data=_objects;
		context=_context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return super.getCount();
		return data.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
			
		//contenedor
		LayoutInflater inflater=context.getLayoutInflater();
		View view =inflater.inflate(R.layout.item_product, null);
		//elementos
		ImageView imgPhoto=(ImageView)view.findViewById(R.id.imageView1);
		TextView txtProduct=(TextView)view.findViewById(R.id.txt_product_name);
		TextView txtDesc=(TextView)view.findViewById(R.id.txt_product_desc);
		TextView txtPrice=(TextView)view.findViewById(R.id.txt_product_price);
		
		//data item 
		ProductoVo vo= data.get(position);
		String name=vo.getProduct_name();
		String desc=vo.getProduct_desc();
		double price=vo.getProduct_price();
		int imageId=vo.getImageID();
		
		//setear la data
		txtProduct.setText(name);
		txtDesc.setText(desc);
		txtPrice.setText(String.valueOf(price));
		imgPhoto.setImageResource(imageId);
		Log.v("CONSOLE", "image ID "+imageId);
		
		return view;

	}

}
