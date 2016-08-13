package database; /**
 * Created by hp on 26-07-2016.
 */
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;

import com.mongodb.client.MongoDatabase;

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

/*func get user
        by id ,para string id ,chk of user
            if exits return user if not return null*/

    public static User get_USERID(String _id)
    {
        User user=null;
        FindIterable<Document> userFind=mongoDatabase.getCollection("Users").find(new BasicDBObject("_id",_id));
        if (userFind!=null)
            return user;
        return null;

    }


    public static User login(String email, String password) {


        User user=null;
        FindIterable<Document> cursor = mongoDatabase.getCollection("Users").find(new BasicDBObject("email", email).append("password", password));

                if(cursor!=null)
                     user = new User(cursor.first());

            return user;
        


    }





    public static String insertPost(Post post)throws IOException
    {
        Document document=post.toDocument();
        mongoDatabase.getCollection("post").insertOne(document);
        String _id= document.getObjectId(Post.ID).toString();

        if (_id==null)
        {
            throw new IOException("Document not inserted in collection");
        }
        return _id;

    }


    public static List<Post> getPosts()
    {
        List <Post>posts=new ArrayList<>();
        FindIterable<Document>iterate=mongoDatabase.getCollection("post").find();
        for (Document document:iterate)
        {
            Post post=new Post(document);
            posts.add(post);
        }
        return posts;
    }

}
