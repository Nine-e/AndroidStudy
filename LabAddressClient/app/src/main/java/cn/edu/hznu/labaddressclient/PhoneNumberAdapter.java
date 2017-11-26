package cn.edu.hznu.labaddressclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by del on 2017/11/26.
 */

public class PhoneNumberAdapter extends ArrayAdapter<PhoneNumber> {
    private int resourceId;
    public PhoneNumberAdapter(Context context, int textViewResourceId, List<PhoneNumber> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final PhoneNumber phoneNumber = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView textView1 = (TextView)view.findViewById(R.id.phone_name);
        TextView textView2 = (TextView)view.findViewById(R.id.phone_number);

        textView1.setText(phoneNumber.getName());
        textView2.setText(phoneNumber.getNumber());

        return view;
    }
}

