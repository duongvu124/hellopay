package util;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vuthaiduong on 12/29/16.
 */
public class ExcelDataReader {
    public static void main(String[] args) throws Exception {
        getDataFromExl("data/data.xlsx", "UserInfo");
    }

    public static Object[][] getDataFromExl(String testDataFile, String sheetName) throws Exception {
        List<List<String>> data = new ArrayList();

        FileInputStream file = new FileInputStream(new File(testDataFile));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> rowIterator = sheet.iterator();
        System.out.println("SheetName: " + sheet.getSheetName());
        boolean flagEof = false;
        while (rowIterator.hasNext() & flagEof == false) {
            Row row = rowIterator.next();

                if (!row.getCell(0).getStringCellValue().equalsIgnoreCase("eof")) {
                    List<String> dataRow = new ArrayList<String>();
                    int colCount = row.getLastCellNum();
                    for (int i = 0; i < colCount; i++) {
                        if (row.getCell(i) == null) {
                            dataRow.add("");
                        } else {
                            String str;
                            if (row.getCell(i).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                str = String.valueOf(row.getCell(i).getNumericCellValue());
                            } else {
                                str = row.getCell(i).getStringCellValue();
                            }
                            dataRow.add(str);
                        }
                    }
                    data.add(dataRow);
                } else {
                    flagEof = true;
                }
        }
        return convertListToArray(data);
        }

    private static Object[][] convertListToArray(List<List<String>> data) {
        Object[][] retObjArr = new Object[data.size()-1][data.get(0).size()];
        for (int i = 1; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                retObjArr[i-1][j] = data.get(i).get(j);
            }
        }
        return retObjArr;
    }
}
