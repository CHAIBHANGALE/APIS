package database;

import com.mongodb.Cursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 29-07-2016.
 */
public class SignIn {

    private String username;
    private String password;

    public SignIn(String username,String password){
        this.username=username;
        this.password=password;
    }

    //For the database
    public static final String ID="_id";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";




    public JSONObject toJSON() throws
            JSONException{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(EMAIL,username);
        jsonObject.put(PASSWORD,password);
        return jsonObject;
    }
}
