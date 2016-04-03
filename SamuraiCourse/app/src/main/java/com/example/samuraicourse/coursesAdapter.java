package com.example.samuraicourse;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;


/**
 * Created by dgist on 4/2/2016.
 */
public class coursesAdapter extends ArrayAdapter<coursess>{
    Context mcontext;

    int layoutId;

    public coursesAdapter(Context context, int layoutResourceId){
        super(context,layoutResourceId);

        mcontext = context;
        layoutId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final coursess currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mcontext).getLayoutInflater();
            row = inflater.inflate(layoutId, parent, false);
        }

        row.setTag(currentItem);
        final CheckBox checkBox = (CheckBox) row.findViewById(R.id.checkToDoItem);
        checkBox.setText(currentItem.getNumber());
        checkBox.setChecked(false);
        checkBox.setEnabled(true);

        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (checkBox.isChecked()) {
                    checkBox.setEnabled(false);
                    if (mcontext instanceof ToDoActivity) {
                        //ToDoActivity activity = (ToDoActivity) mcontext;

                        //activity.checkItem(currentItem);
                    }
                }
            }
        });

        return row;
    }
}
