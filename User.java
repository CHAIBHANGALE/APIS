package database;

import com.mongodb.client.FindIterable;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.bson.Document;

/**
 * Created by hp on 02-08-2016.
 */
public class User {
    public static String _id;
    public String email;
    public String password;
    public String name;
    byte[] Image;


    public static final String ID="_id";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";

    //Insertion into database values
    User(Document document)
    {
       _id= document.getObjectId(ID).toString();
        email=document.getString(EMAIL);
        password=document.getString(PASSWORD);

    }

    public static String get_id()
    {
        return _id;
    }
}
