<%-- 
    Document   : index
    Created on : 27 Dec, 2016, 12:01:45 PM
    Author     : svasudev
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Page</title>
         <style type="text/css">
.head {
background-color:#2ABCA7;
color:#FAFAFA;
}
h1 {
text-align:center;
padding:18px 0 18px 0;
font-size: 1.4em;
} 
body {
  padding-top:25px;
  background-color:#f4f4f4;
  margin-left:10px;
  margin-right:10px;
}

button {
  margin-top:15px;
  margin-bottom:25px;
  background-color:#2ABCA7;
  padding: 6px 30px;
  -ms-border-radius: 5px;
  -o-border-radius: 5px;
  border-radius: 5px;
  border: 1px solid #2ABCA7;
  -webkit-transition: .5s;
  transition: .5s;
  display: inline-block;
  cursor: pointer;
  width:20%;
  color:#000000;
}

input, textarea {
  font-size: 1em;
  padding: 15px 10px 10px;
  font-family: 'Source Sans Pro',arial,sans-serif;
  border: 1px solid #cecece;
  background: #d7d7d7;
  color:#000000;
  
}
.myTable {
background-color:#eee;
border-collapse:collapse;
width:42%;

}
.myTable th {
background-color:#000;
color:white;
 }
.myTable td,
.myTable th {
    padding:5px;
    border:1px solid #000;
}
            
 </style>
        <div class="head"> <h1>Project Tracker</h1></div>
    </head>
    <body>
        <div>
            <div style=" position:absolute; left:800px; width:80%; ">
            <%  
            HashMap<String, String> all_tasks=(HashMap)request.getAttribute("alltasks");
            if(all_tasks.size()!=0 && all_tasks.keySet()!=null)
            { %>
                    <table class="myTable" style="margin-top:50px; margin-left:10px;">
                        <thead>
                        <tr>
                            <th>Tasks</th>
                            <th>TimeStamp</th>
                            <th>Status</th>
                            <th>Comments</th>
                        </tr>
                        </thead>
                        <%
                for(String report_task:all_tasks.keySet())
                {
                    String[] tokens_from_alltasks;
                    String task_status_comments=all_tasks.get(report_task);
                    tokens_from_alltasks=task_status_comments.split("\\:");
                    System.out.println(report_task.toString()); 
        %>
                        <tbody>
                           <tr>
                               <td><%= tokens_from_alltasks[0] %></td>
                               <td><%= report_task %></td>
                               <td><%= tokens_from_alltasks[1] %></td>
                               <td><%= tokens_from_alltasks[2] %></td>
                               
                           </tr>
                           <% } %>
                        </tbody>
                        
                    </table>
                        <% } %>
        </div>
        <div style=" position:absolute; left:40px; width:50%; margin-top:50px;">
        <table>   
        <form name="Task Tracker" action="AddTask" method="post">
        <tr><input type="text" name="taskTextField" placeholder="Enter your task"></tr><br><br>
        <tr><textarea name="commentsTextField" cols="50" rows="5" placeholder="Enter additional comments"></textarea></tr><br>
        <tr><button name="submit" type="submit" >Add Task </button></tr><br><br>
        
        </form>
            </table>
    
    
        <form name="tech_selection" action="Status_Update" method="POST">
          
            
  
        <%  
            HashMap<String, String> tasks=(HashMap)request.getAttribute("activetasks");
            if(tasks.size()!=0 && tasks.keySet()!=null)
            { %>
            <table class="myTable" >
                <thead>
                <tr>
                    <th>Select</th>
                    <th>Tasks</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                    <%
                for(String task:tasks.keySet())
                {
                    String status=tasks.get(task);
                    System.out.println(task.toString()); 
        %>
                <tr>
                <td><input type="radio" name=myradio value="<%= task %>"</td>    
                <td><%= task %></td>
                <td><%= status %></td>
                </tr>
        <%         
                }
                String str= request.getContextPath();
            
         %>
                </tbody>
                </table>
           </select><br><br>
           <textarea name="comments" cols="50" rows="5" placeholder="Enter comments"></textarea><br>
           <button name="Resume" type="submit">Resume</button>
           <button name="Pause" type="submit">Pause</button>   
           <button name="Stop" type="submit">Stop</button>
           <button name="Finish" type="submit">Finish</button>
           <% } %>
        </form>
    </div>
     </div>           
        
    </body>
</html>
