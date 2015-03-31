package be.intec.maravillos.helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONInfo {

    public static final String GET_USERS = "http://172.16.202.4:8000/user/all";
    public static String GET_USER_BY_ID = "http://172.16.202.4:8000/user/";
    public static final String TAG = JSONInfo.class.getSimpleName();

    public static String getUsersFromJSon() {

        return GETConnection(GET_USERS);
    }

    public static String getOneUserByIdFromJSON(int id){
        return GETConnection(GET_USER_BY_ID+id);

    }

    private static String GETConnection(String uri){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jSONString = null;

        try {
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream input = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            if (input != null) {
                reader = new BufferedReader(new InputStreamReader(input));
            }

            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

            jSONString = builder.toString();
        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
        }

        return jSONString;
    }

    private void POSTConnection(String uri){

    }

}
