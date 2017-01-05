
import java.io.File;
import java.io.FileInputStream;
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
 * @author sarsh
 */
public class Authenticator {
    static boolean authenticate(String username,String path)
    {
        int i=0;
        try
        {
            FileInputStream f_input = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(f_input);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rows_number= sheet.getLastRowNum();
            System.out.println(rows_number);
            XSSFRow row_user = null;
            XSSFCell cell_user = null;
            for(int iterator=1;iterator<=rows_number;iterator++)
            {
                row_user = sheet.getRow(iterator);
                cell_user = row_user.getCell(0);
                String valid_username=cell_user.getStringCellValue();
                if(username.equals(valid_username))
                {
                   System.out.println("valid user"); 
                   return true;
                }
            }
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}