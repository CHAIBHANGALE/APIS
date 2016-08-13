import database.SignIn;
import database.Signup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import database.DB;
import database.User;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by hp on 29-07-2016.
 */
@WebServlet(name = "SigninServlet")
public class SigninServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        if (username==null || username.equals(""))
        {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"field 'username' required");
            return;
        }

        if (password==null ||password.equals(""))
        {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"field 'password' required");
            return;
        }

        User loggedInUser=DB.login(username,password);
        try {
            JSONObject jsonResponse=new JSONObject();
            jsonResponse.put("_id",loggedInUser.get_id());
            jsonResponse.put("email",username);
            jsonResponse.put("password",password);
            response.getWriter().write(jsonResponse.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
