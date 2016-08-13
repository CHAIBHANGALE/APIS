import database.DB;
import database.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import database.Post;
import database.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 07-08-2016.
 */
@WebServlet(name = "PostServlet")
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String title=request.getParameter("title");
        if (title==null || title=="")
        {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Enter a title");
        }
        String content=request.getParameter("content");
        if (content==null || content=="")
        {

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Enter content please");
        }
        String user_id=User.get_id();

        try {
            Post post=new Post(title,content);
            //User user=DB.get_USERID(user_id);
            String _id= DB.insertPost(post);
            JSONObject jsonObject=new JSONObject();
           // JSONArray jsonArray=new JSONArray();
            jsonObject.put(Post.ID,_id);
            //jsonArray.put(user);
            //jsonObject.put(User.ID,user.get_id());
            response.getWriter().write(jsonObject.toString());
            //response.getWriter().write(jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
