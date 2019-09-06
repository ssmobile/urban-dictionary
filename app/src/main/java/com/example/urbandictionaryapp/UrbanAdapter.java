package com.example.urbandictionaryapp;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbandictionaryapp.model.ListItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UrbanAdapter extends RecyclerView.Adapter<UrbanAdapter.ViewHolder> {

    private static List<ListItem> itemList;

    private static final String TAG = "TAG_UrbanAdapter";

    public UrbanAdapter(List<ListItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.urban_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem item = itemList.get(position);
        String word = "“" + item.getWord() + "”";
        String author = "Posted by: " + item.getAuthor();
        String definition = item.getDefinition().replaceAll("[\\[\\]]","");
        String example = "example:\n" + item.getExample().replaceAll("[\\[\\]]","");
        String writtenOn = getAgo(item.getWrittenOn());
        Log.d(TAG, "onBindViewHolder: " + example);

        holder.wordTV.setText(word);
        holder.authorTV.setText(author);
        holder.definitionTV.setText(definition);
        holder.exampleTV.setText(example);
        holder.writtenOnTV.setText(writtenOn);
        holder.thumbsDownTV.setText(String.valueOf(item.getThumbsDown()));
        holder.thumbsUpTV.setText(String.valueOf(item.getThumbsUp()));

    }

    @Override
    public int getItemCount() { return itemList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView wordTV;
        TextView authorTV;
        TextView definitionTV;
        TextView exampleTV;
        TextView writtenOnTV;
        TextView thumbsUpTV;
        TextView thumbsDownTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wordTV = itemView.findViewById(R.id.wordTV);
            authorTV = itemView.findViewById(R.id.authorTV);
            definitionTV = itemView.findViewById(R.id.definitionTV);
            exampleTV = itemView.findViewById(R.id.exampleTV);
            writtenOnTV = itemView.findViewById(R.id.writtenOnTV);
            thumbsUpTV = itemView.findViewById(R.id.thumbsUpTV);
            thumbsDownTV = itemView.findViewById(R.id.thumbsDownTV);
        }


    }

    private static String getAgo(String writtenOnDate) {

        final long[] timemillis = {31536000000L, 2628002880L, 86400000L, 3600000, 60000, 1000};
        final String[] timeStrings = {"year", "month", "day", "hour", "minute", "second"};
        final String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String result = "";

        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = format.parse(writtenOnDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return writtenOnDate;
        }

        assert date != null;
        long repoMillis = date.getTime();
        long nowMillis = new Date().getTime() + timemillis[3]*4;
        long diff = nowMillis - repoMillis;

        for (int i=0 ; i<timemillis.length ; i++) {
            long interval = diff/timemillis[i];
            if (interval !=0) {
                String suffix = (interval == 1) ? "" : "s";
                result = String.format(Locale.US,
                        "Posted %d %s%s ago", interval, timeStrings[i], suffix);
                return result;
            }
        }

        return result;
    }

}
