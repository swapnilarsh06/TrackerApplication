/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sarsh
 */
@WebServlet(urlPatterns = {"/Status_Update"})
public class Status_Update extends HttpServlet {

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
            throws ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(Status_Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        String username=null;
        String task=null;
        try {
            
            HttpSession currentSession=request.getSession(false);
            String path=getServletContext().getInitParameter("tasks_excel_path");
            task=request.getParameter("myradio");
            System.out.println("task="+task);
            username=currentSession.getAttribute("username").toString();
            if(username==null)
            {
                request.setAttribute("ErrorMessage", "Kindly login to proceed");
                request.getRequestDispatcher("Error.jsp").forward(request, response);       
            }
            int optionChosen=0;
            if(request.getParameter("Resume")!=null)
            {
                optionChosen=1;
            }
            else if(request.getParameter("Pause")!=null)
            {
                optionChosen=2;
            }
            else if(request.getParameter("Stop")!=null)
            {
                optionChosen=3;
            }
            else if(request.getParameter("Finish")!=null)
            {
                optionChosen=4;
            }
            StatusUpdater.updateStatus(path,username,task,optionChosen);
            StatusUpdater.addStatusUpdate(path,username,task,"hard coded value comments",optionChosen);
            
            request.getRequestDispatcher("/login").forward(request, response);       
    }
    
         catch(Exception e)
        {
            System.out.println(e);
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
