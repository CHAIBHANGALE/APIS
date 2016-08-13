import database.Post;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import database.DB;

/**
 * Created by hp on 03-08-2016.
 */
@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try
        {
            JSONObject jsonObject=new JSONObject();
            JSONArray jsonArray=new JSONArray();

            List<Post>posts=DB.getPosts();
            for (Post post:posts)
            {
                JSONObject jsonObject1=post.toJSONObjectpost();
                System.out.println(jsonObject1.toString());
                jsonArray.put(jsonObject1);

            }
            jsonObject.put("result",jsonArray);
            response.getWriter().write(jsonObject.toString());
        } catch (JSONException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
            e.printStackTrace();
        }


    }
}
