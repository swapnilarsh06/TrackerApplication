/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author sarsh
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username=null;
            RequestDispatcher rd=null;
            username = request.getParameter("username");
            if(username==null)          //implies login.java is called from a page other than login.jsp
            {
                if(request.getSession().getAttribute("username")!=null)
                    username=request.getSession().getAttribute("username").toString();
                else
                {
                    request.setAttribute("ErrorMessage", "Kindly login to proceed");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);       
                }
            }
            
            else                        //implies login.java is called from login.jsp
            {
                String loginSheetPath=getServletContext().getInitParameter("loginsheetpath");
                boolean authentication=Authenticator.authenticate(username,loginSheetPath);
                if(!authentication)
                {
                    request.setAttribute("ErrorMessage", "Login Failed. Invalid Username. Try Again!!");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
                
            }
            if(username!=null && username.trim()!="")
            {
                HttpSession currentSession=request.getSession(true);
                currentSession.setAttribute("username", username);
                HashMap<String,String> activetasks=TaskFetcher.fetchActiveTasks(username,getServletContext().getInitParameter("tasks_excel_path"));
                HashMap<String,String> alltasks=TaskFetcher.fetchAllTasks(username,getServletContext().getInitParameter("tasks_excel_path"));
                if(activetasks==null)
                {
                    out.println("No tasks were found for the user"+username);
                }
                else
                {
                    request.setAttribute("activetasks", activetasks);
                }
                if(alltasks==null)
                {
                    out.println("No tasks were found for the user"+username);
                }
                else
                {
                    request.setAttribute("alltasks", alltasks);
                }

                request.getRequestDispatcher("Task_tracker.jsp").forward(request,response);
            }
            else
            {
                //JOptionPane.showMessageDialog(null, "No username is passed!!Try again");
                out.println("Either no username is passed to the servlet login.java or a blank username is passed.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
           out.close();
//            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
