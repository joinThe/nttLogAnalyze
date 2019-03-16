package sample;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class CreateExcelFile {

    public CreateExcelFile() {
    }

    public CreateExcelFile(String fileName) {
        this.fileName = fileName;
    }

    String fileName = "New.xlsx";
    File file;
    OPCPackage opcPackage = null;

    public boolean create(ArrayList<excelLine> list) throws IOException, InvalidFormatException {
        file = new File(fileName);
        OutputStream outputStream = new FileOutputStream(file);
        opcPackage = OPCPackage.create(outputStream);


        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Sheet");
        for ( int i = 0;i < list.size() - 1;i++ ) {
            byte c=0;


            Row row = sheet.createRow(i);
//
//            for ( int val: list.get(i).getArray() ) {
//                row.createCell(c).setCellValue(val);
//            }

            for ( int j = 0;j <4 ;j++ ) {
                row.createCell(j).setCellValue(c);
            }

            // CellAddress cell= row.createCell(1);//.setCellValue("23");
        }


        workbook.write(outputStream);
        outputStream.close();
        workbook.close();


        return true;
    }

}
