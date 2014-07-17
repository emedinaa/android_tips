package com.example.rest.model;

import java.io.Serializable;

import com.example.rest.R;

public class ProductoVo implements Serializable {

	private String objectId;
	private String product_name, product_desc;
	private double product_price;
	private int imageID;
	
	public ProductoVo() {
		super();
		// TODO Auto-generated constructor stub
	//	imageID=R.drawable.img_mrbeen;
		imageID=0;
	}
	
	public ProductoVo(String objectId, String product_name, String product_desc,
			float product_price) {
		super();
		this.objectId = objectId;
		this.product_name = product_name;
		this.product_desc = product_desc;
		this.product_price = product_price;
	}
	
	

	public ProductoVo(String product_name, String product_desc,
			double product_price, int imageID) {
		super();
		this.product_name = product_name;
		this.product_desc = product_desc;
		this.product_price = product_price;
		this.imageID = imageID;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}


	
	
}
