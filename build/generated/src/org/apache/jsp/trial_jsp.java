package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.HashMap;

public final class trial_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Index Page</title>\n");
      out.write("        <style type=\"text/css\">\n");
      out.write(".head {\n");
      out.write("background-color:#2ABCA7;\n");
      out.write("color:#FAFAFA;\n");
      out.write("}\n");
      out.write("h1 {\n");
      out.write("text-align:center;\n");
      out.write("padding:18px 0 18px 0;\n");
      out.write("font-size: 1.4em;\n");
      out.write("} \n");
      out.write("body {\n");
      out.write("  padding-top:25px;\n");
      out.write("  background-color:#f4f4f4;\n");
      out.write("  margin-left:10px;\n");
      out.write("  margin-right:10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("button {\n");
      out.write("  margin-top:15px;\n");
      out.write("  margin-bottom:25px;\n");
      out.write("  background-color:#2ABCA7;\n");
      out.write("  padding: 6px 30px;\n");
      out.write("  -ms-border-radius: 5px;\n");
      out.write("  -o-border-radius: 5px;\n");
      out.write("  border-radius: 5px;\n");
      out.write("  border: 1px solid #2ABCA7;\n");
      out.write("  -webkit-transition: .5s;\n");
      out.write("  transition: .5s;\n");
      out.write("  display: inline-block;\n");
      out.write("  cursor: pointer;\n");
      out.write("  width:10%;\n");
      out.write("  color:#000000;\n");
      out.write("}\n");
      out.write("\n");
      out.write("input, textarea {\n");
      out.write("  font-size: 1em;\n");
      out.write("  padding: 15px 10px 10px;\n");
      out.write("  font-family: 'Source Sans Pro',arial,sans-serif;\n");
      out.write("  border: 1px solid #cecece;\n");
      out.write("  background: #d7d7d7;\n");
      out.write("  color:#000000;\n");
      out.write("  \n");
      out.write("}\n");
      out.write(".myTable {\n");
      out.write("background-color:#eee;\n");
      out.write("border-collapse:collapse;\n");
      out.write("width:42%;\n");
      out.write("\n");
      out.write("}\n");
      out.write(".myTable th {\n");
      out.write("background-color:#000;\n");
      out.write("color:white;\n");
      out.write(" }\n");
      out.write(".myTable td,\n");
      out.write(".myTable th {\n");
      out.write("    padding:5px;\n");
      out.write("    border:1px solid #000;\n");
      out.write("}\n");
      out.write("            \n");
      out.write(" </style>\n");
      out.write(" \n");
      out.write("         <div class=\"head\"> <h1>Project Tracker</h1></div>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("          ");
      out.write("\n");
      out.write("          <table class=\"myTable\" style=\"margin-top:60px; margin-left:800px;\">\n");
      out.write("                        <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Tasks</th>\n");
      out.write("                            <th>TimeStamp</th>\n");
      out.write("                            <th>Status</th>\n");
      out.write("                            <th>Comments</th>\n");
      out.write("                        </tr>\n");
      out.write("                        </thead>\n");
      out.write("               ");
      out.write("\n");
      out.write("                        <tbody>\n");
      out.write("                           <tr>\n");
      out.write("                               <td> abcd</td>\n");
      out.write("                               <td>abcd</td>\n");
      out.write("                               <td> abcd </td>\n");
      out.write("                               <td>abcd</td>\n");
      out.write("                               \n");
      out.write("                           </tr>\n");
      out.write("                        ");
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                        \n");
      out.write("                    </table>\n");
      out.write("                      ");
      out.write("\n");
      out.write("        \n");
      out.write("       <div style=\"margin-top:-60px;\">\n");
      out.write("           \n");
      out.write("        <form name=\"Task Tracker\" action=\"AddTask\" method=\"post\">\n");
      out.write("        <input type=\"text\" name=\"taskTextField\" placeholder=\"Enter your task\"><br><br>\n");
      out.write("        <textarea name=\"commentsTextField\" cols=\"50\" rows=\"5\" placeholder=\"Enter additional comments\"></textarea><br>\n");
      out.write("        <button name=\"submit\" type=\"submit\" >Add Task </button><br><br>\n");
      out.write("        \n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("    </div>\n");
      out.write("    <div >\n");
      out.write("        <form name=\"tech_selection\" action=\"Status_Update\" method=\"POST\">\n");
      out.write("            <div >\n");
      out.write("            \n");
      out.write("  \n");
      out.write("      ");
      out.write("\n");
      out.write("      <table class=\"myTable\" >\n");
      out.write("                <thead>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Select</th>\n");
      out.write("                    <th>Tasks</th>\n");
      out.write("                    <th>Status</th>\n");
      out.write("                </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                  ");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                <td><input type=\"radio\" name=myradio value=\"abcd\"</td>    \n");
      out.write("                <td>abcd</td>\n");
      out.write("                <td>abcd</td>\n");
      out.write("                </tr>\n");
      out.write("      ");
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("                </table>\n");
      out.write("           </select><br><br>\n");
      out.write("           <textarea name=\"comments\" cols=\"50\" rows=\"5\" placeholder=\"Enter comments\"></textarea><br>\n");
      out.write("           <button name=\"Resume\" type=\"submit\">Resume</button>\n");
      out.write("           <button name=\"Pause\" type=\"submit\">Pause</button>   \n");
      out.write("           <button name=\"Stop\" type=\"submit\">Stop</button>\n");
      out.write("           <button name=\"Finish\" type=\"submit\">Finish</button>\n");
      out.write("         ");
      out.write("\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("                \n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
