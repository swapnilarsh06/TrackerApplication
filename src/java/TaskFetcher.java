
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
public class TaskFetcher {
    static HashMap<String,String> fetchActiveTasks(String username,String excel_path)
    {
        HashMap<String, String> activetasks=new HashMap<String, String>();
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
        Iterator<Row> rowIterator = sheet.iterator();
        if(rowIterator.hasNext())
            rowIterator.next();                         //skipping the first row of heading
        while (rowIterator.hasNext())                   //loop over all entries in the excel sheet
        {
            Row row = rowIterator.next();
            Cell cell = row.getCell(0);
            if(username.equalsIgnoreCase(cell.getStringCellValue()))
            {
                String task;
                String status;
                String totTimeString=null;
                String latestTimeStamp;
                double totTime;
                task=row.getCell(1).getStringCellValue();
                status=row.getCell(2).getStringCellValue();
                latestTimeStamp=row.getCell(3).getStringCellValue();
                if(status.equalsIgnoreCase("Paused") || status.equalsIgnoreCase("Deferred") )
                {
                    if(row.getCell(4)!=null)
                        totTimeString=row.getCell(4).getStringCellValue();
                }
                else if(status.equalsIgnoreCase("In-Progress"))
                {
                    if(row.getCell(4)!=null)
                        totTimeString=row.getCell(4).getStringCellValue();
                    if(totTimeString!=null)
                    {
                        totTime=Double.parseDouble(totTimeString);                        
                    }
                    else
                    {
                        totTime=0;
                    }
                    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                    Date currentTimestamp = new Date();
                    Date latestTimeStampObj=null;
                    df.format(currentTimestamp);
                    try {
                        latestTimeStampObj=df.parse(latestTimeStamp);
                    } catch (ParseException ex) {
                        Logger.getLogger(TaskFetcher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    long timeDifference=currentTimestamp.getTime()-latestTimeStampObj.getTime();
                    long divisor = 60 * 60 * 1000;
                    double diffHours = ((double) timeDifference / (double) divisor);                        
                    totTime+=diffHours;
                    totTimeString=String.valueOf(totTime);
                }
                if(status!=null && !status.equalsIgnoreCase("completed") && task!=null && totTimeString!=null)
                {                
                    activetasks.put(task, status+":"+totTimeString);
                }
            }
        }
        try {
            workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(TaskFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activetasks;
    }

    static HashMap<String,String> fetchAllTasks(String username,String excel_path)
    {
        HashMap<String, String> alltasks=new HashMap<String, String>();
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
 
        //Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(1);
        Iterator<Row> rowIterator = sheet.iterator();
        if(rowIterator.hasNext())
            rowIterator.next();                         //skipping the first row of heading
        while (rowIterator.hasNext())                   //loop over all entries in the excel sheet
        {
            Row row = rowIterator.next();
            Cell cell = row.getCell(0);
            if(username.equalsIgnoreCase(cell.getStringCellValue()))
            {
                String task;
                String status;
                String timestamp;
                String comments;
                task=row.getCell(1).getStringCellValue();
                status=row.getCell(2).getStringCellValue();
                timestamp=row.getCell(3).getStringCellValue();
                comments=row.getCell(4).getStringCellValue();
                alltasks.put(timestamp, task+":"+status+":"+comments);
            }
        }
        try {
            workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(TaskFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alltasks;
    }
    

}
