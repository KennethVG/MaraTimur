package be.intec.maravillos.helper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.intec.maravillos.R;
import be.intec.maravillos.model.User;

public class UserAdapter extends BaseAdapter {

    private List<User> users;
    private Context context;
    private ViewHolder holder;

    public UserAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);


        // Re-use views:
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.mTxtvFirstName = (TextView) convertView.findViewById(R.id.txtv_firstname);
            holder.mTxtvLastName = (TextView) convertView.findViewById(R.id.txtv_lastname);
            holder.mTxtvCity = (TextView) convertView.findViewById(R.id.txtv_city);
            holder.mTxtvCountry = (TextView) convertView.findViewById(R.id.txtv_country);
            holder.mImgProfile = (ImageView) convertView.findViewById(R.id.img_profile);

            // store the holder with the view.
            convertView.setTag(holder);
        }
        else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the ViewHolder.
            holder = (ViewHolder) convertView.getTag();
        }

        User user = users.get(position);

        if(user != null){
            holder.mTxtvFirstName.setText(user.getFirstName());
            holder.mTxtvLastName.setText(user.getLastName());
            holder.mTxtvCity.setText(user.getCity());
            holder.mTxtvCountry.setText(user.getCountry());
            new LoadImageFromBackGround(holder.mImgProfile).execute(user.getUrlString());
        }
        return convertView;
    }

    private static class ViewHolder {
        public TextView mTxtvFirstName, mTxtvLastName, mTxtvCountry, mTxtvCity;
        public ImageView mImgProfile;
    }
}



