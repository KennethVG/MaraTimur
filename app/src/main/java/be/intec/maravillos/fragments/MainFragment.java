package be.intec.maravillos.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import be.intec.maravillos.DetailActivity;
import be.intec.maravillos.R;
import be.intec.maravillos.helper.JSONInfo;
import be.intec.maravillos.helper.UserAdapter;
import be.intec.maravillos.helper.UsersJSON;
import be.intec.maravillos.model.User;

public class MainFragment extends android.support.v4.app.Fragment {


    private static final String TAG = MainFragment.class.getSimpleName();
    private String response;
    private List<User> users;
    private ListView lvUsers;
    private UserAdapter adapter;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = null;

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            lvUsers = (ListView) rootView.findViewById(R.id.listView);
            new RequestTask().execute();
        }

        return rootView;
    }

    // Helper klasse om in de achtergrond de connectie te maken en de users op te vragen.
    private class RequestTask extends AsyncTask<String, String, String> implements AdapterView.OnItemClickListener {
        @Override
        protected String doInBackground(String... params) {
            response = JSONInfo.getUsersFromJSon();
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            UsersJSON usersJSON = new UsersJSON(response);
            users = usersJSON.getUsers();
            adapter = new UserAdapter(users, getActivity());
            lvUsers.setAdapter(adapter);
            lvUsers.setOnItemClickListener(this);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            User user = (User) parent.getItemAtPosition(position);
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }


}
