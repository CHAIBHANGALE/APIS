import com.sun.xml.internal.bind.v2.model.core.ID;
import database.Signup;
import org.json.JSONException;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import database.DB;
import org.json.JSONObject;

/**
 * Created by hp on 26-07-2016.
 */

public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        String email=request.getParameter(Signup.EMAIL);

        if (email==null || email.equals(""))
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Field 'email' required");
            return ;
        }

        String password=request.getParameter(Signup.PASSWORD);

        if (password==null || password.equals("")  )
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Field required");
            return;
        }

            try {
                Signup signup = new Signup(email, password);
                String _id=DB.insertValue(signup);
                JSONObject jsonResponse=new JSONObject();
                jsonResponse.put(Signup.ID,_id);

                response.getWriter().write(jsonResponse.toString());
            }

        catch (IOException | JSONException e)
        {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
            e.printStackTrace();
        }


    }
}
