package com.tas.excel.impl;

import com.tas.excel.AbtractIOExcel;
import com.tas.excel.Header;
import com.tas.exception.NotSupportTypeException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOExcelImpl extends AbtractIOExcel {
    private IOExcelImpl() {
    }

    private static class SingletonHelper {
        private static final IOExcelImpl INSTANCE = new IOExcelImpl();
    }

    public static IOExcelImpl getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private XSSFWorkbook workbook;


    public <K, T extends JpaRepository> XSSFWorkbook exportExcel(T t, Class<K> kClass, File file) throws SAXException, NoSuchFieldException {
        List<K> entities = t.findAll();
        workbook = new XSSFWorkbook();
        if (entities.size() <= 0) {
            return workbook;
        }


        XSSFSheet xssfSheet = workbook.createSheet("Position");
        int rowNum = 0;

        Cell cell;
        Row row;
        List<Header> listHeader = null;
        try {
            listHeader = getHeadderFromXML(file);
        } catch (ParserConfigurationException e) {
            getLogger().error(e.getMessage());

        } catch (SAXException e1) {
            getLogger().error(e1.getMessage());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        XSSFCellStyle style = createCellStyle(workbook);


        row = xssfSheet.createRow(rowNum);
        for (int i = 0; i < listHeader.size(); i++) {
            Header header = listHeader.get(i);
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(header.getValue());
            cell.setCellStyle(style);
        }
        Field[] fields;
        fields = kClass.getDeclaredFields();
        for (K entity : entities
        ) {
            rowNum++;
            row = xssfSheet.createRow(rowNum);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(rowNum);

            for (int i = 0; i < listHeader.size(); i++) {
                Header header = listHeader.get(i);
                Field field = null;
                try {
                    field = getValue(fields, header.getName());
                } catch (NoSuchFieldException e) {
                    continue;
                }

                cell = row.createCell(i, header.getType().getCode());

                try {
                    setCellValueWrapper(cell, field, entity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


        }

        return workbook;
    }

    public <K, T extends JpaRepository> void importExcel(Workbook workbook, T t, Class<K> kClass, File file) {
        try {
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
            Field[] fields = kClass.getDeclaredFields();
            List<Header> headers = getHeadderFromXML(file);

            Iterator<Row> rowIterator = sheet.iterator();

            mergeHeaderField(fields, headers);
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                 K k = kClass.newInstance();
                for (Header header: headers
                     ) {
                    Cell cell=row.getCell(header.getId());
                    switch (header.getType()) {
                        case _INTEGER:
                            header.getField().setInt(k, (int) cell.getNumericCellValue());
                            getLogger().info((int) cell.getNumericCellValue());
                            break;
                        case STRING:
                            header.getField().set(k, cell.getStringCellValue());
                            getLogger().info(cell.getStringCellValue());
                            break;
                        case _DOUBLE:
                            header.getField().set(k, cell.getNumericCellValue());
                            getLogger().info((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                }
                t.save(k);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public Field getValue(final Field[] fields, String name) throws NoSuchFieldException {
        for (Field field : fields
        ) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        throw new NoSuchFieldException();
    }

    public <K> void setCellValueWrapper(Cell cell, Field field, K k) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(k);
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof java.sql.Date) {
            cell.setCellValue((Date) value);
        } else {
            throw new NotSupportTypeException();
        }
    }

    public List<String> getImportHeader(Row row) {
        List<String> listHeader = new ArrayList<>();
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            listHeader.add(cell.getStringCellValue());
        }
        return listHeader;
    }

    public Header checkCell(String name, List<Header> headers) throws NoSuchFieldException {
        for (Header header : headers
        ) {
            if (header.getValue().equals(name)) {
                return header;
            }
        }
        return null;
    }

    public void mergeHeaderField(Field[] fields, List<Header> headers) {
        for (Header header : headers
        ) {

            for (Field field : fields
            ) {
                if (field.getName().equals(header.getName())) {
                    header.setField(field);
                    header.getField().setAccessible(true);
                    break;
                }

            }
        }
    }
}
