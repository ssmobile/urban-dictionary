package com.example.urbandictionaryapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("defid")
	private int defid;

	@SerializedName("sound_urls")
	private List<String> soundUrls;

	@SerializedName("thumbs_down")
	private int thumbsDown;

	@SerializedName("author")
	private String author;

	@SerializedName("written_on")
	private String writtenOn;

	@SerializedName("definition")
	private String definition;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("thumbs_up")
	private int thumbsUp;

	@SerializedName("word")
	private String word;

	@SerializedName("current_vote")
	private String currentVote;

	@SerializedName("example")
	private String example;

	public void setDefid(int defid){
		this.defid = defid;
	}

	public int getDefid(){
		return defid;
	}

	public void setSoundUrls(List<String> soundUrls){
		this.soundUrls = soundUrls;
	}

	public List<String> getSoundUrls(){
		return soundUrls;
	}

	public void setThumbsDown(int thumbsDown){
		this.thumbsDown = thumbsDown;
	}

	public int getThumbsDown(){
		return thumbsDown;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setWrittenOn(String writtenOn){
		this.writtenOn = writtenOn;
	}

	public String getWrittenOn(){
		return writtenOn;
	}

	public void setDefinition(String definition){
		this.definition = definition;
	}

	public String getDefinition(){
		return definition;
	}

	public void setPermalink(String permalink){
		this.permalink = permalink;
	}

	public String getPermalink(){
		return permalink;
	}

	public void setThumbsUp(int thumbsUp){
		this.thumbsUp = thumbsUp;
	}

	public int getThumbsUp(){
		return thumbsUp;
	}

	public void setWord(String word){
		this.word = word;
	}

	public String getWord(){
		return word;
	}

	public void setCurrentVote(String currentVote){
		this.currentVote = currentVote;
	}

	public String getCurrentVote(){
		return currentVote;
	}

	public void setExample(String example){
		this.example = example;
	}

	public String getExample(){
		return example;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"defid = '" + defid + '\'' + 
			",sound_urls = '" + soundUrls + '\'' + 
			",thumbs_down = '" + thumbsDown + '\'' + 
			",author = '" + author + '\'' + 
			",written_on = '" + writtenOn + '\'' + 
			",definition = '" + definition + '\'' + 
			",permalink = '" + permalink + '\'' + 
			",thumbs_up = '" + thumbsUp + '\'' + 
			",word = '" + word + '\'' + 
			",current_vote = '" + currentVote + '\'' + 
			",example = '" + example + '\'' + 
			"}";
		}
}