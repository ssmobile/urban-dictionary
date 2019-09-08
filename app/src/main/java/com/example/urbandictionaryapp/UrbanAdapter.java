package com.example.urbandictionaryapp;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbandictionaryapp.model.ListItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UrbanAdapter extends RecyclerView.Adapter<UrbanAdapter.ViewHolder> {

    private List<ListItem> itemList;

    private static final String TAG = "TAG_UrbanAdapter";

    UrbanAdapter(List<ListItem> itemList) {
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

        ViewHolder(@NonNull View itemView) {
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



    @RequiresApi(api = Build.VERSION_CODES.N)
    void sortByThumbsUpAscending(boolean ascending) {
        Comparator<ListItem> comparator = new ThumbsComparator(ascending, true);
        Log.d(TAG, "sortByThumbsUpAscending: itemlist:" + itemList.toString());
        itemList.sort(comparator);

        Log.d(TAG, "sortByThumbsUpAscending: itemlist:" + itemList.toString());

        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void sortByThumbsDown(boolean ascending) {
        Comparator<ListItem> comparator = new ThumbsComparator(ascending, false);

        Log.d(TAG, "sortByThumbsDown: itemlist:" + itemList.toString());
        itemList.sort(comparator);

        Log.d(TAG, "sortByThumbsDown: itemlist:" + itemList.toString());

        notifyDataSetChanged();
    }


    class ThumbsComparator implements Comparator<ListItem> {

        boolean ascending;
        boolean thumbsup;

        ThumbsComparator(boolean ascending, boolean thumbsup) {
            this.ascending = ascending;
            this.thumbsup = thumbsup;
        }

        @Override
        public int compare(ListItem listItem, ListItem t1) {
            if (ascending) {
                if (thumbsup) {
                    return Integer.compare(t1.getThumbsUp(), listItem.getThumbsUp());
                }

                return Integer.compare(t1.getThumbsDown(), listItem.getThumbsDown());

            } else {
                if (thumbsup) {
                    return Integer.compare(listItem.getThumbsUp(), t1.getThumbsUp());
                }

                return Integer.compare(listItem.getThumbsDown(), t1.getThumbsDown());
            }
        }
    }


}
