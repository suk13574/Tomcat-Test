import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/", "/test", "/dev"})
public class SimpleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        switch (path) {
            case "/":
                response.setContentType("text/plain");
                response.getWriter().write("hi");
                break;
            case "/test":
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
                break;
            case "/dev":
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
                break;
        }
    }
}
