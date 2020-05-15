package com.tas.utils;

import com.tas.dto.DeviceDto;
import com.tas.validator.annotation.ColumnExcel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelUtil {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private <T> T init(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    public <T> Method findMethodByAnnotation(ColumnExcel columnExcel, T t) {
        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ColumnExcel && annotation == columnExcel) {
                    String nameField = field.getName();
                    nameField = ((nameField.charAt(0) + "").toUpperCase() + nameField.substring(1, nameField.length()));
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.getName().equals("set" + nameField)) {
                            return method;
                        }
                    }
                }
            }
        }
        return null;
    }

    public <T> Map<String, ColumnExcel> getHeaderByClass(Class<T> clazz) {
        Map<String, ColumnExcel> result = new TreeMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ColumnExcel) {
                    ColumnExcel columnExcel = (ColumnExcel) annotation;
                    result.put(field.getName(), columnExcel);
                }
            }
        }
        return result;
    }

    public <T> boolean isEqualsHeaderClassAndFile(Class<T> clazz, Row row) {
        int count = 0;
        Map<String, ColumnExcel> map = this.getHeaderByClass(clazz);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String title = cell.toString().trim();
            for (Map.Entry<String, ColumnExcel> entry : map.entrySet()) {
                if (title.equalsIgnoreCase(entry.getValue().title().trim())
                        && cell.getColumnIndex() == entry.getValue().col()) {
                    count++;
                    break;
                }
            }
        }
        return count == map.size();
    }

    public <T> T convertRowToT(Row row, Class<T> clazz) {
        T result = this.init(clazz);
        Map<String, ColumnExcel> map = this.getHeaderByClass(clazz);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            for (Map.Entry<String, ColumnExcel> entry : map.entrySet()) {
                if (entry.getValue().col() == cell.getColumnIndex()) {
                    try {
                        Method method = this.findMethodByAnnotation(entry.getValue(), result);
                        switch (entry.getValue().type()) {
                            case _STRING:
                                method.invoke(result, cell.getStringCellValue());
                                break;
                            case _DATE:
                                method.invoke(result, cell.getDateCellValue());
                                break;
                            case _DOLLARS:
                                method.invoke(result, cell.getStringCellValue());
                                break;
                            case _DOUBLE:
                                method.invoke(result, cell.getNumericCellValue());
                                break;
                            case _INTEGER:
                                break;
                            case _FORMULA:
                                break;
                            case _BOOLEAN:
                                break;
                            default:
                                break;
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return result;
    }

    public <T> List<T> importFileExcel(File file, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            // CHECK HEADER
            if (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                boolean checked = this.isEqualsHeaderClassAndFile(clazz, row);
                if (!checked) {
                    return null;
                }
            }

            // CHECK ROW
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                T element = this.convertRowToT(row, clazz);
                result.add(element);
            }
            workbook.close();
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private CellStyle createStyleForTitle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private void createHeader(Row row, Class<?> clazz, CellStyle cellStyle) {
        Map<String, ColumnExcel> map = this.getHeaderByClass(clazz);
        for (Map.Entry<String, ColumnExcel> entry : map.entrySet()) {
            Cell cell = row.createCell(entry.getValue().col());
            cell.setCellValue(entry.getValue().title());
            cell.setCellStyle(cellStyle);
        }
    }

    private <T> void createData(Row row, T t, Class<?> clazz) {
        Map<String, ColumnExcel> result = this.getHeaderByClass(clazz);
        for (Map.Entry<String, ColumnExcel> entry : result.entrySet()) {
            String nameMethod = entry.getKey();
            nameMethod = ("get" + (nameMethod.charAt(0) + "").toUpperCase()
                    + nameMethod.substring(1, nameMethod.length()));
            Cell cell = row.createCell(entry.getValue().col());
            try {
                Method method = t.getClass().getDeclaredMethod(nameMethod);
                switch (entry.getValue().type()) {
                    case _STRING:
                        String value1 = (String) method.invoke(t, null);
                        cell.setCellValue(value1);
                        break;
                    case _DATE:
                        Date value2 = (Date) method.invoke(t, null);
                        cell.setCellValue(value2);
                        break;
                    case _DOLLARS:
                        break;
                    case _DOUBLE:
                        double value4 = (double) method.invoke(t, null);
                        cell.setCellValue(value4);
                        break;
                    case _INTEGER:
                        int value5 = (int) method.invoke(t, null);
                        break;
                    case _FORMULA:
                        break;
                    case _BOOLEAN:
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <T> boolean exportExcel(List<T> list, HttpServletResponse response) {
        response.setContentType("application/octet-stream:UTF-8"); // or can use text/csv
        response.setHeader("Content-Disposition", "attachment; filename=device_" + sdf.format(new Date()) + ".xlsx");
        response.setHeader("charset", "UTF-8");

        Class<?> clazz = list.get(0).getClass();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet 1");
        CellStyle cellStyle = this.createStyleForTitle(workbook);

        // CREATE HEADER
        Row row = sheet.createRow(0);
        this.createHeader(row, clazz, cellStyle);

        // CREATE ROW
        int i = 1;
        for (T element : list) {
            Row record = sheet.createRow(i);
            this.createData(record, element, clazz);
            i++;
        }

        try {
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            workbook.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        ExcelUtil excelUtil = new ExcelUtil();
        List<DeviceDto> list = excelUtil.importFileExcel(new File("device_07-05-2020.xlsx"), DeviceDto.class);
        System.out.println("LIST SIZE EXCEL :" + list.size());
    }

}

