package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(Context context, Person[] arr) {
        super(context, R.layout.adapter_string, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Person cPerson = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_string, null);
        }

        // Заполняем адаптер
        ((ImageView) convertView.findViewById(R.id.flag)).setImageResource(cPerson.getFlag());
        ((TextView) convertView.findViewById(R.id.name)).setText(cPerson.getName());
        ((TextView) convertView.findViewById(R.id.money)).setText(cPerson.getMoney());
        return convertView;
    }
}
