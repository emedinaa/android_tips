package com.example.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.example.rest.model.ProductoVo;
import com.example.rest.model.adapter.ProductoAdapter;
import com.example.rest.utils.ResultProduct;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.codehaus.jackson.map.DeserializationConfig;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RESTActivity extends Activity implements OnItemClickListener {

	List<ProductoVo> data;
	private ListView lstProduct;
	private ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rest);
		
		app();
	}
	public void update_handler(View view)
	{
		loadProducts();
		///loadProducts2();
	}
	
	private void loadProducts2() {
		// TODO Auto-generated method stub
		List<ProductoVo> data2=new ArrayList<ProductoVo>();
		data2.add(new ProductoVo("Sillas", "x 6", 100,R.drawable.folders));
		data2.add(new ProductoVo("Mesas", "x 1", 100,R.drawable.img002));
		data2.add(new ProductoVo("Cuadernos", "Oficio", 100,R.drawable.img003));
		data2.add(new ProductoVo("Hojas", "x 100", 100,R.drawable.img004));
		data2.add(new ProductoVo("Hojas", "x 100", 100,R.drawable.img_mrbeen));
		data2.add(new ProductoVo("Hojas", "x 100", 100,R.drawable.img002));
		data2.add(new ProductoVo("Hojas", "x 100", 100,R.drawable.img003));
		data2.add(new ProductoVo("Hojas", "x 100", 100,R.drawable.img004));
		
		ProductoAdapter adapter2=new ProductoAdapter(this, R.layout.item_product,
				data2);
		lstProduct.setAdapter(adapter2);
	}
	private void loadProducts() 
	{
		progress.show();
		AsyncHttpClient client=new AsyncHttpClient();
		String path=RESTConstant.GET_PRODUCTS;
		
		Header header1=new BasicHeader("X-Parse-Application-Id", RESTConstant.APP_ID);
		Header header2=new BasicHeader("X-Parse-REST-API-Key", RESTConstant.REST_API_KEY);
		Header header3=new BasicHeader("Content-Type", "application/json; charset=utf-8");
		Header[] headers={header1,header2,header3};
		
		client.get(this,path,headers,null,new AsyncHttpResponseHandler()
		{
			@Override
			@Deprecated
			public void onSuccess(String content) {
				// TODO Auto-generated method stub
				super.onSuccess(content);
				Log.v("CONSOLE", "products  result "+content);
				
				ObjectMapper mapper=new ObjectMapper();
				mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				ResultProduct results =null;
				try {
					results=mapper.readValue(content, ResultProduct.class);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.v("CONSOLE","result "+results);
				
				if(results!=null)
				{
					data=results.getResults();
					populateListProduct();
				}
			}
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
				progress.hide();
			}
			@Override
			@Deprecated
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
			}
		});
		
	}
	protected void populateListProduct() {
		// TODO Auto-generated method stub
		ProductoAdapter adapter=new ProductoAdapter(this, R.layout.item_product,
				data);
		
		lstProduct.setAdapter(adapter);
	}
	private void app() {
		// TODO Auto-generated method stub
		//listview
		lstProduct=(ListView)findViewById(R.id.lstUser);
		progress=new ProgressDialog(this);
		progress.setCancelable(false);
		progress.setMessage("Cargando...");
		
		data=new ArrayList<ProductoVo>();

		/*
		//data
		data=new ArrayList<UserVo>();
		data.add(new UserVo(100,"Eduardo", "Medina", "abc@abc.com", "31"));
		data.add(new UserVo(101,"Eduardo", "Medina", "cba@abc.com", "32"));
		data.add(new UserVo(102,"Josè", "Paredes", "abc@abc.com", "31"));
		data.add(new UserVo(103,"Eduardo", "Medina", "abc@abc.com", "32"));
		data.add(new UserVo(104,"Josè", "Paredes", "cba@abc.com", "31"));
		data.add(new UserVo(105,"Eduardo", "Medina", "abc@abc.com", "32"));
		data.add(new UserVo(106,"Josè", "Medina", "abc@abc.com", "31"));
		data.add(new UserVo(107,"Eduardo", "Paredes", "cba@abc.com", "32"));
		data.add(new UserVo(108,"Josè", "Medina", "abc@abc.com", "31"));
		
		//adapter
		UserAdapter adapter=new UserAdapter(this, R.layout.item_user,
				data);
		
		//setear el adapter
		lstUser.setAdapter(adapter);
		
		//evento
		lstUser.setOnItemClickListener(this);
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.example_list_adapter, menu);
		return true;
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void onItemClick(AdapterView<?> adapter, View view, 
			int position, long id) {
		// TODO Auto-generated method stub
		UserVo vo=data.get(position);
		Log.v("CONSOLE", "vo "+vo.toString()+" "+vo.getName()+" "+vo.getEmail());
		
		Intent intent =new Intent(this, DetailsUserActivity.class);
		intent.putExtra("vo", vo);
		startActivity(intent);
	}*/

	/* data
	 * 11-22 16:29:01.626: V/CONSOLE(22810): products  result 
		 {"results":[{"product_desc":"Paquetex20","product_name":"Protector de transparencias","product_price":25,"createdAt":"2013-11-22T19:44:00.363Z","updatedAt":"2013-11-22T19:44:54.796Z","objectId":"aDMyjZMFV5"},
		 {"product_desc":"Carta","product_name":"Legajador AZ","product_price":12,"createdAt":"2013-11-22T19:45:37.419Z","updatedAt":"2013-11-22T19:46:47.997Z","objectId":"mhTBGNpLz8"},
		 {"product_desc":"Oficio","product_name":"Legajador AZ","product_price":12,"createdAt":"2013-11-22T19:47:02.357Z","updatedAt":"2013-11-22T19:47:10.313Z","objectId":"ZAWIEpbVGN"},
		{"product_desc":"Carpeta Yute Carta","product_name":"Folder","product_price":200,"createdAt":"2013-11-22T19:47:35.641Z","updatedAt":"2013-11-22T19:47:54.546Z","objectId":"LNNgrimIWu"},
		{"product_desc":"Carpeta Yute Oficio","product_name":"Folder","product_price":200,"createdAt":"2013-11-22T19:48:15.094Z","updatedAt":"2013-11-22T19:48:29.830Z","objectId":"eFkOR1gCtK"},
		 {"product_desc":"Caja # 12","product_name":"Caja","product_price":20,"createdAt":"2013-11-22T19:48:50.529Z","updatedAt":"2013-11-22T19:49:03.895Z","objectId":"6eZ2V8iSfE"},
		 {"product_desc":"Portarevistas Selecto","product_name":"Portarevistas","product_price":8,"createdAt":"2013-11-22T19:49:43.538Z","updatedAt":"2013-11-22T19:50:07.357Z","objectId":"XddvsANJIL"}]}
	 */
}
