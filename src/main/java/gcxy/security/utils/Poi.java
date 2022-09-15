package gcxy.security.utils;

import gcxy.security.bean.Exceltemplate;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Poi {
    public static void main(String args[]) throws IOException {
        List<Exceltemplate> obj = new ArrayList<>();
        String path = "F:\\安全管理检查表.xlsx";
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(path);

        XSSFSheet sheet = xssfWorkbook.getSheetAt(1);

        int lastRowNum = sheet.getLastRowNum();


        for (int i = 1; i <=lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);//获取行

            if (row != null) {
                List<String> list = new ArrayList<>();

                short cellNum = row.getLastCellNum();
                for (int j = 0; j <=cellNum; j++) {
                    XSSFCell cell = row.getCell(j);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        String value = cell.getStringCellValue();
                        list.add(value);
                    }
                }
                //Exceltemplate exceltemplate = new Exceltemplate(list.get(0), list.get(1), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12), list.get(13),list.get(14));
                //obj.add(exceltemplate);

            }


        }
        for (Exceltemplate ex : obj) {
            System.out.println(ex);
        }
    }
}
