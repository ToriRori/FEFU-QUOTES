package com.viktoria.temp;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class QuoteAdapter extends ArrayAdapter<Quote>{
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Quote> QuoteList;

    QuoteAdapter(Context context, int resource, ArrayList<Quote> quotes) {
        super(context, resource, quotes);
        this.QuoteList = quotes;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public @NonNull View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Quote quote = QuoteList.get(position);

        viewHolder.nameView.setText(quote.quote);
        viewHolder.teacherButton.setText((String)(quote.surname + " " + quote.name + " " + quote.patronymic));
        viewHolder.subjectButton.setText(quote.subject);
        viewHolder.schoolButton.setText(quote.school);

        viewHolder.schoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        viewHolder.subjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        viewHolder.teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return convertView;
    }

    private String formatValue(int count, String unit){
        return String.valueOf(count) + " " + unit;
    }
    private class ViewHolder {
        final Button schoolButton, teacherButton, subjectButton;
        final TextView nameView;
        ViewHolder(View view){
            schoolButton = (Button) view.findViewById(R.id.schoolButton);
            teacherButton = (Button) view.findViewById(R.id.teacherButton);
            subjectButton = (Button) view.findViewById(R.id.subjectButton);
            nameView = (TextView) view.findViewById(R.id.nameView);
        }
    }
}
