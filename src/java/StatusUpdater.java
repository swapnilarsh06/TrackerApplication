
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author svasudev
 */
public class StatusUpdater {

    static boolean updateStatus(String path, String username, String task, int optionChosen) {
        File myFile = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(myFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StatusUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException ex) {
            Logger.getLogger(StatusUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return false;
        }
        Iterator ite1 = sheet.rowIterator();
        if (ite1 == null) {
            return false;
        }
        XSSFRow myRow = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        df.format(dateobj);
        if (ite1.hasNext()) {
            ite1.next();
        }
        while (ite1.hasNext()) {
            myRow = (XSSFRow) ite1.next();
            XSSFCell usernameCell = myRow.getCell(0);
            String sheet_userid = null;
            if (usernameCell.getStringCellValue() != null) {
                sheet_userid = usernameCell.getStringCellValue();
            } else {
                return false;
            }
            System.out.println("sheet_userid=" + sheet_userid);
            XSSFCell taskCell = myRow.getCell(1);
            if (taskCell == null) {
                return false;
            }
            String sheet_task = taskCell.getStringCellValue();
            System.out.println("sheet_task=" + sheet_task);
            if (sheet_task == null) {
                return false;
            }
            if (sheet_userid.equals(username) && sheet_task.equals(task)) {
                break;
            }
        }
        if (optionChosen == 1) {                        //Resume is pressed.
            XSSFCell statusCell = myRow.getCell(2);
            String status = null;
            if (statusCell != null) {
                status = statusCell.getStringCellValue();
                if (status.equalsIgnoreCase("Paused") || status.equalsIgnoreCase("Deferred")) {
                    XSSFCell timestampCell = myRow.getCell(3);
                    timestampCell.setCellValue(df.format(dateobj));
                    XSSFCell status_cell = myRow.getCell(2);
                    status_cell.setCellValue("In-Progress");
                }
                else if(status.equalsIgnoreCase("In-Progress"))       //trying to Resume an in-progress task.
                {
                    return true;
                }
                else {                                                //trying to resume a finished task or invalid status task.
                    return false;
                }
            } else {
                return false;
            }
        } else if (optionChosen == 2) {                               //Pause is pressed
            XSSFCell statusCell = myRow.getCell(2);
            if(statusCell!=null)
            {
                String status=statusCell.getStringCellValue();
                if(status!=null)
                {
                
                    if(status.equalsIgnoreCase("Paused"))
                        return true;
                    else if(status.equalsIgnoreCase("In-Progress"))
                    {
                        XSSFCell timestampCell = myRow.getCell(3);
                        String dateInString = timestampCell.getStringCellValue();
                        Date date_obj = null;
                        try {
                            date_obj = df.parse(dateInString);
                        } catch (ParseException ex) {
                            Logger.getLogger(StatusUpdater.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("date value of sheet in pause button=" + dateobj.toString());

                        Date obj = new Date();
                        df.format(obj);
                        long diff = date_obj.getTime() - obj.getTime();
                        long divisor = 60 * 60 * 1000;
                        double diffHours = ((double) diff / (double) divisor);                        
                        //XSSFCell cell2=myRow.getCell(4);
                        XSSFCell totalTimeCell = null;
                        if (myRow.getCell(4) == null) {     
                            totalTimeCell = myRow.createCell(4);
                            totalTimeCell.setCellValue(Double.toString(diffHours));
                        } else {
                            totalTimeCell = myRow.getCell(4);
                            double timeSpent = Double.parseDouble(totalTimeCell.getStringCellValue());
                            timeSpent += diffHours;
                            totalTimeCell.setCellValue(String.valueOf(timeSpent));
                        }
                        statusCell.setCellValue("Paused");
                    }
                    else if(status.equalsIgnoreCase("Deferred"))
                    {
                        statusCell.setCellValue("Paused");                                                
                    }
                    else 
                        return false;
                }
                else
                    return false;
            }
            else
            {
                return false;
            }
        } else if (optionChosen == 3) {                     //Stop is pressed
            XSSFCell statusCell = myRow.getCell(2);
            if(statusCell!=null)
            {
                String status=statusCell.getStringCellValue();
                if(status!=null)
                {
                
                    if(status.equalsIgnoreCase("Paused"))
                        return true;
                    else if(status.equalsIgnoreCase("In-Progress"))
                    {
                        XSSFCell timestampCell = myRow.getCell(3);
                        String dateInString = timestampCell.getStringCellValue();
                        Date date_obj = null;
                        try {
                            date_obj = df.parse(dateInString);
                        } catch (ParseException ex) {
                            Logger.getLogger(StatusUpdater.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("date value of sheet in pause button=" + dateobj.toString());

                        Date obj = new Date();
                        df.format(obj);
                        long diff = date_obj.getTime() - obj.getTime();
                        long divisor = 60 * 60 * 1000;
                        double diffHours = ((double) diff / (double) divisor);                        
                        XSSFCell totalTimeCell = null;
                        if (myRow.getCell(4) == null) {     
                            totalTimeCell = myRow.createCell(4);
                            totalTimeCell.setCellValue(Double.toString(diffHours));
                        } else {
                            totalTimeCell = myRow.getCell(4);
                            double timeSpent = Double.parseDouble(totalTimeCell.getStringCellValue());
                            timeSpent += diffHours;
                            totalTimeCell.setCellValue(String.valueOf(timeSpent));
                        }
                        statusCell.setCellValue("Deferred");
                    }
                    else if(status.equalsIgnoreCase("Paused"))
                    {
                        statusCell.setCellValue("Deferred");                                                
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        } else if (optionChosen == 4) {
            XSSFCell status_cell = myRow.getCell(2);
            if (status_cell.getStringCellValue() == "In-Progress") //logic to calculate the time taken if the task was in-process so far
            {
                XSSFCell timestampCell = myRow.getCell(3);
                String dateInString = timestampCell.getStringCellValue();
                Date date_obj = null;
                try {
                    date_obj = df.parse(dateInString);
                } catch (ParseException ex) {
                    Logger.getLogger(StatusUpdater.class.getName()).log(Level.SEVERE, null, ex);
                }
                Date obj = new Date();
                df.format(obj);
                long fv = date_obj.getTime();
                long sv = obj.getTime();
                long diff = sv - fv;
                long divisor = 60 * 60 * 1000;
                double diffHours = ((double) diff / (double) divisor);

                XSSFCell cell2 = null;
                if (myRow.getCell(4) == null) {
                    cell2 = myRow.createCell(4);
                    cell2.setCellValue(Double.toString(diffHours));
                } else {
                    cell2 = myRow.getCell(4);
                    double timeSpent = Double.parseDouble(cell2.getStringCellValue());
                    timeSpent += diffHours;

                    cell2.setCellValue(String.valueOf(timeSpent));
                }
            }
            status_cell.setCellValue("Completed");

        } else {
            System.out.println("Invalid value for optionChosen");
        }
        try {
            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(StatusUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Debug one");
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(myFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StatusUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Debug two");
        try {
            workbook.write(fileOut);
        } catch (IOException ex) {
            Logger.getLogger(StatusUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fileOut.close();
        } catch (IOException ex) {
            Logger.getLogger(StatusUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    static void addStatusUpdate(String path, String username, String task, String comments, int optionChosen) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();

        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaskFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(TaskFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(1);
        int rownum = sheet.getLastRowNum();
        //Blank workbook
        Row row = sheet.createRow(rownum + 1);

        Cell usernameCell = row.createCell(0);
        usernameCell.setCellValue(username);
        Cell taskCell = row.createCell(1);
        taskCell.setCellValue(task);
        Cell statusCell = row.createCell(2);
        switch (optionChosen) {
            case 1:
                statusCell.setCellValue("Resumed");
                break;
            case 2:
                statusCell.setCellValue("Paused");
                break;
            case 3:
                statusCell.setCellValue("Deferred");
                break;
            case 4:
                statusCell.setCellValue("Completed");
                break;
        }
        Cell timestampCell = row.createCell(3);
        timestampCell.setCellValue(df.format(dateobj).toString());
        Cell commentsCell = row.createCell(4);
        commentsCell.setCellValue(comments);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(path));
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
