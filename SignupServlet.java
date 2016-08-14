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

        // Set header Content-type of the HttpResponse
        // Read more here. https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
        response.setContentType("application/json");
        // For accepting cross domain requests
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");

        //get email by get method
        String email=request.getParameter(Signup.EMAIL);
        if (email==null || email.equals(""))
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Field 'email' required");
            return ;
        }

        //get password by get method
        String password=request.getParameter(Signup.PASSWORD);

        if (password==null || password.equals("")  )
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Field required");
            return;
        }

            try {
                Signup signup = new Signup(email, password);
                //insert a single value in database
                String _id=DB.insertValue(signup);
                //creating a json object and put the id of data inserted into it
                JSONObject jsonResponse=new JSONObject();
                jsonResponse.put(Signup.ID,_id);

                //function to print the id ie return response
                response.getWriter().write(jsonResponse.toString());
            }

            //if input output erroroccurs due to database
        catch (IOException | JSONException e)
        {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
            e.printStackTrace();
        }


    }
}
