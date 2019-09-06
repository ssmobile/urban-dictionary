package com.example.urbandictionaryapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class UrbanResponse {

	@SerializedName("list")
	private List<ListItem> list;

	public UrbanResponse(List<ListItem> list) {
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	@NotNull
	@Override
 	public String toString(){
		return 
			"UrbanResponse{" +
			"list = '" + list + '\'' + 
			"}";
		}
}