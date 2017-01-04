
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sarsh
 */
public class TaskAdder {
    static void addtask(String task,String comments,String username,String excel_path)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();

        FileInputStream file=null;
        try {
            file = new FileInputStream(new File(excel_path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaskFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook=null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(TaskFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rownum=sheet.getLastRowNum();
                //Blank workbook
        Row row = sheet.createRow(rownum+1);
        
        Cell usernameCell = row.createCell(0);
        usernameCell.setCellValue(username);
        Cell taskCell = row.createCell(1);
        taskCell.setCellValue(task);
        Cell statusCell = row.createCell(2);
        statusCell.setCellValue("In-Progress");
        Cell timestampCell = row.createCell(3);
        timestampCell.setCellValue(df.format(dateobj).toString());
        Cell commentsCell = row.createCell(5);
        commentsCell.setCellValue(comments);
    
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(excel_path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaskAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            workbook.write(out);
        } catch (IOException ex) {
            Logger.getLogger(TaskAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(TaskAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    static void addAllTask(String task,String comments,String username,String excel_path)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();

        FileInputStream file=null;
        try {
            file = new FileInputStream(new File(excel_path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaskFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook=null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(TaskFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(1);
        int rownum=sheet.getLastRowNum();
                //Blank workbook
        Row row = sheet.createRow(rownum+1);
        
        Cell usernameCell = row.createCell(0);
        usernameCell.setCellValue(username);
        Cell taskCell = row.createCell(1);
        taskCell.setCellValue(task);
        Cell statusCell = row.createCell(2);
        statusCell.setCellValue("Task Created");
        Cell timestampCell = row.createCell(3);
        timestampCell.setCellValue(df.format(dateobj).toString());
        Cell commentsCell = row.createCell(4);
        commentsCell.setCellValue(comments);
    
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(excel_path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaskAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            workbook.write(out);
        } catch (IOException ex) {
            Logger.getLogger(TaskAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(TaskAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
