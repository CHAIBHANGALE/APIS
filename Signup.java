package database;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 26-07-2016.
 */
public class Signup {

    private int id;
    private String email;
    private String password;


    public Signup(String memail, String mpassword)
    {
        this.email=memail;
        this.password=mpassword;
    }

    //Constants for Database

    public static final String ID="_id";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";

    //Insertion into database values
    Signup(Document document)
    {
        document.getObjectId(ID).toString();
        document.getString(EMAIL);
        document.getString(PASSWORD);

    }


    //Conversion into a document

    Document toDocument()
    {
        Document document=new Document();
        //document.append(ID,id);
        document.append(EMAIL,email);
        document.append(PASSWORD,password);
        return document;
    }



    public JSONObject toJSONObject() throws JSONException
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(ID,id);
        jsonObject.put(EMAIL,email);
        jsonObject.put(PASSWORD,password);
        return jsonObject;
    }
}
