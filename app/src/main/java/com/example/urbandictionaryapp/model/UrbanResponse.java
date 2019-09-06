package com.example.urbandictionaryapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UrbanResponse {

	@SerializedName("list")
	private List<ListItem> list;

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	@Override
 	public String toString(){
		return 
			"UrbanResponse{" +
			"list = '" + list + '\'' + 
			"}";
		}
}