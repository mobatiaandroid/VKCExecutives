package com.mobatia.vkcexecutive.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.mobatia.vkcexecutive.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ArticleAutoCompletionAdapter extends ArrayAdapter<String> {
    private Context context;
    private int resourceId;
    private List<String> items, tempItems, suggestions;

    public ArticleAutoCompletionAdapter(@NonNull Context context, int resourceId, ArrayList<String> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resourceId, parent, false);
            }
            String fruit = getItem(position);
            TextView name = (TextView) view.findViewById(R.id.textArticle);

            name.setText(fruit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return fruitFilter;
    }

    private Filter fruitFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String fruit = (String) resultValue;
            return fruit;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (String fruit : tempItems) {
                    if (fruit.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        suggestions.add(fruit);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<String> tempValues = (ArrayList<String>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (String fruitObj : tempValues) {
                    add(fruitObj);
                    notifyDataSetChanged();
                }
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
}