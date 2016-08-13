package database;

import java.lang.annotation.Documented;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 07-08-2016.
 */
public class Post {
    private String _id;
    private String title;
    private String content;

    public static String ID="_id";
    public static String TITLE="title";
    public static String CONTENT="content";


    public Post(String title, String content)
    {
        this.title=title;
        this.content=content;
    }

    Post(Document document)
    {
        _id=document.getObjectId(ID).toString();
        title=document.getString(TITLE);
        content=document.getString(CONTENT);

    }

    Document toDocument()
    {
        Document document=new Document();
        document.append(TITLE,title);
        document.append(CONTENT,content);
        return document;
    }

    public JSONObject toJSONObjectpost() throws JSONException
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(ID,_id);
        jsonObject.put(TITLE,title);
        jsonObject.put(CONTENT,content);
        return jsonObject;
    }

}
