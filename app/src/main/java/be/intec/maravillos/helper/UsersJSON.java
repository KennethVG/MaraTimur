package be.intec.maravillos.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.intec.maravillos.model.User;

/**
 * Created by Kenneth on 17/02/2015.
 */
public class UsersJSON {


    private JSONArray array;

    public UsersJSON(String jsonString) {
        try {
            if (jsonString != null) {
                array = new JSONArray(jsonString);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    public List<User> getUsers() {

        List<User> users = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                users.add(new User(obj.optInt("id"), obj.optString("lastname"), obj.optString("firstname"), obj.optString("city"), obj.optString("country"), obj.optString("img")));

            }
        } catch (JSONException jsonE) {
            jsonE.printStackTrace();
        }
        return users;
    }

    public User getUserById() {
        User user = null;
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                user = new User();
                user.setId(obj.optInt("id"));
                user.setQuote(obj.optString("quote"));
                user.setUrlString(obj.optString("img"));
                JSONObject church = obj.optJSONObject("church");
                user.setChurchName(church.optString("name"));
            }
        } catch (JSONException jsonE) {
            jsonE.printStackTrace();
        }
        return user;
    }
}
