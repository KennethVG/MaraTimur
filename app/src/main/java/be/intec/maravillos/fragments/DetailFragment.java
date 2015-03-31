package be.intec.maravillos.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import be.intec.maravillos.BioActivity;
import be.intec.maravillos.R;
import be.intec.maravillos.helper.JSONInfo;
import be.intec.maravillos.helper.LoadImageFromBackGround;
import be.intec.maravillos.helper.UsersJSON;
import be.intec.maravillos.model.User;

public class DetailFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private static final String TAG = DetailFragment.class.getSimpleName();

    // Views
    private TextView mTxtvFirstname, mTxtvLastname, mTxtvCity, mTxtvCountry, mTxtvChurchName, mTxtvQuote;
    private Button mBtnBio, mBtnVideo;
    private ImageView mImgProfile;

    // Simple Variables
    private String response = null;
    private User user;
    private String firstname, lastname, city, country;

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the user from the previous Fragment!
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            user = (User) extras.get("user");
            firstname = user.getFirstName();
            lastname = user.getLastName();
            city = user.getCity();
            country = user.getCountry();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        // Bind views
        mTxtvFirstname = (TextView) rootView.findViewById(R.id.txtv_detail_firstname);
        mTxtvLastname = (TextView) rootView.findViewById(R.id.txtv_detail_lastname);
        mTxtvChurchName = (TextView) rootView.findViewById(R.id.txtv_detail_churchname);
        mTxtvQuote = (TextView) rootView.findViewById(R.id.txtv_detail_quote);
        mTxtvCity = (TextView) rootView.findViewById(R.id.txtv_detail_city);
        mTxtvCountry = (TextView) rootView.findViewById(R.id.txtv_detail_country);
        mImgProfile = (ImageView) rootView.findViewById(R.id.imageView);
        mBtnBio = (Button) rootView.findViewById(R.id.btn_bio);

        // Put name, city and country from previous fragment in Textviews:
        mTxtvCity.append(" " + city);
        mTxtvFirstname.setText(firstname);
        mTxtvLastname.setText(lastname);
        mTxtvCountry.append(" " + country);

        // Load image on background and put it in ImageView:
        new LoadImageFromBackGround(mImgProfile).execute(user.getUrlString());

        // Load UserByID and get extra info like CHURCHNAME and QUOTE:
        new RequestTask().execute();

        return rootView;
    }

    // Event Handling for Button BIO and Button VIDEO
    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mBtnBio) {
            intent = new Intent(getActivity(), BioActivity.class);
            intent.putExtra("user", user);
        } else if (v == mBtnVideo) {

        }
        startActivity(intent);
    }

    // Helper klasse om in de achtergrond de connectie te maken en de users op te vragen.
    private class RequestTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            response = JSONInfo.getOneUserByIdFromJSON(user.getId());
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (response != null) {
                UsersJSON usersJSON = new UsersJSON(response);
                user = usersJSON.getUserById();
                mTxtvChurchName.append(" " + user.getChurchName());
                mTxtvQuote.setText(user.getQuote());
            }
        }

    }

}
