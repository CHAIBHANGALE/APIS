package database;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 26-07-2016.
 */
public class Signup {

    private String _id;
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
        _id=document.getObjectId(ID).toString();
        email=document.getString(EMAIL);
        password=document.getString(PASSWORD);

    }


    //Conversion into a document

    Document toDocument()
    {
        Document document=new Document();
        document.append(EMAIL,email);
        document.append(PASSWORD,password);
        return document;
    }



   /* public JSONObject toJSONObject() throws JSONException
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(ID,id);
        jsonObject.put(EMAIL,email);
        jsonObject.put(PASSWORD,password);
        return jsonObject;
    }*/
}
