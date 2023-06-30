package ETU1850.framework.servlet;

import ETU1850.framework.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class FrontServlet extends HttpServlet{
    private HashMap<String, Mapping> mappingUrls;
    public void init() throws ServletException{

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet NewServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>"); 
            }
        }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
            processRequest(request, response);
        }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        processRequest(request, response);
    }
}