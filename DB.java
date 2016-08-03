package database; /**
 * Created by hp on 26-07-2016.
 */
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.*;
import org.bson.Document;

import java.io.IOException;

public class DB {
    private static final String DB_HOST="0.0.0.0";
    private static final int DB_PORT=27017;


    private static MongoDatabase mongoDatabase;


        static {
            MongoClient mongoClient=new MongoClient(DB_HOST,DB_PORT);
            mongoDatabase=mongoClient.getDatabase("assignment");
        }


    public static String insertValue(Signup signup)throws IOException
    {
        Document document=signup.toDocument();
//        BasicDBObject basicDBObject=new BasicDBObject(document);
        mongoDatabase.getCollection("Users").insertOne(document);
        String _id= document.getObjectId(Signup.ID).toString();

        if (_id==null)
        {
            throw new IOException("Document not inserted in collection");
        }
        return _id;

    }

    public static User login(String email,String password)
    {
        if(email.equals() && password.equals())
        {
            return
        }

    }

/*    public int getValue(SignIn signIn)
    {
        MongoCollection<Document> dbCollection=mongoDatabase.getCollection("Users");
        FindIterable<Document> cursor=dbCollection.find();
        try {

            for (Document document:cursor) {
                if (document.equals(signIn)) {
                  String id=  document.getObjectId(signIn);
                }
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    return ;
}*/

    public static List<Signup> getList()
    {
        List<Signup> signIns=new ArrayList<>();
        FindIterable<Document>iterable=mongoDatabase.getCollection("Users").find();
        for (Document document:iterable)
        {
            Signup signUp=new Signup(document);
            signIns.add(signUp);
        }
        return signIns;
    }

}
