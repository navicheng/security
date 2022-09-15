package gcxy.security.utils;

import gcxy.security.bean.Exceltemplate;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

import java.util.concurrent.CopyOnWriteArrayList;

//解析数据表
public class PoiUtils {
    public static CopyOnWriteArrayList<Exceltemplate> poi(String path) throws IOException {
        CopyOnWriteArrayList<Exceltemplate> obj = new CopyOnWriteArrayList<>();

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(path);

        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

        CellReference cellReference = new CellReference("A4");

        boolean flag = false;
        for (int i = cellReference.getRow(); i <= sheet.getLastRowNum(); ) {//poi的bug getLastRowNum方法获得的最后一行如果有格式则会出现错误导致数据录入失败，这里获得真实行数
            Row r = sheet.getRow(i);
            if (r == null) {
                // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                continue;
            }
            flag = false;
            for (Cell c : r) {
                if (c.getCellType() != Cell.CELL_TYPE_BLANK) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                i++;
                continue;
            } else {//如果是空白行（即可能没有数据，但是有一定格式）
                if (i == sheet.getLastRowNum())//如果到了最后一行，直接将那一行remove掉
                    sheet.removeRow(r);
                else//如果还没到最后一行，则数据往上移一行
                    sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
            }
        }

        int lastRowNum = sheet.getLastRowNum();


        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);//获取行

            if (row != null) {
                CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

                short cellNum = row.getLastCellNum();
                for (int j = 0; j < cellNum; j++) {
                    XSSFCell cell = row.getCell(j);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        String value = cell.getStringCellValue();
                        list.add(value);
                    }
                }
                Exceltemplate exceltemplate = new Exceltemplate(
                        list.get(0).replace("/n", "").replace("/r", ""),//categoryName
                        list.get(1).replace("\n", "").replace("/r", ""),//categoryId
                        list.get(4).replace("\n", "").replace("/r", ""),//chapterName
                        list.get(5).replace("\n", "").replace("/r", ""),//chapterId
                        list.get(6).replace("\n", "").replace("/r", ""),//typeName
                        list.get(7).replace("\n", "").replace("/r", ""),//typeId
                        list.get(8).replace("\n", "").replace("/r", ""),//dangerSourceName
                        list.get(9).replace("\n", "").replace("/r", ""),//examinationContentId
                        list.get(10).replace("\n", "").replace("/r", ""),//content
                        list.get(12).replace("\n", "").replace("/r", ""),//minScore
                        list.get(13).replace("\n", "").replace("/r", ""),//maxScore
                        list.get(14).replace("\n", "").replace("/r", ""),//score
//                        list.get(14).replace("\n", "").replace("/r", ""),
//                        list.get(16).replace("\n","").replace("/r",""),
                        list.get(11).replace("\n","").replace("/r","")//regulationId

                );

                obj.add(exceltemplate);
            }


        }
        return obj;
    }
}
