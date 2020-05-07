package com.tas.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestApachePoint {

    private static List<Student> init() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Student student = new Student();
            student.setAge((int) (Math.random() * 25) + 1);
            student.setName("Name " + i);
            list.add(student);
        }
        return list;
    }

    private static CellStyle createStyleForTitle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void main(String[] args) throws Exception {

        // not limit row
        Workbook workbook = new SXSSFWorkbook(-1);
        Sheet sheet = workbook.createSheet("Test Sheet");

        //Row 0 = Header
        Row rowHeader = sheet.createRow(0);
        CellStyle cellStyle = createStyleForTitle(workbook);
        for (HeaderEnum e : HeaderEnum.values()) {
            Cell cell = rowHeader.createCell(e.getIndex() - 1);
            cell.setCellValue(e.getLabel());
            cell.setCellStyle(cellStyle);
        }

        // Row 1->n : Content
        List<Student> list = init();
        for (int i = 0; i < list.size(); i++) {
            // 1 Object = 1 Row
            Row row = sheet.createRow(i + 1);
            for (HeaderEnum e : HeaderEnum.values()) {
                switch (e) {
                    case AGE:
                        row.createCell(e.getIndex() - 1).setCellValue(list.get(i).getAge());
                        break;
                    case NAME:
                        row.createCell(e.getIndex() - 1).setCellValue(list.get(i).getName());
                        break;
                    default:
                        break;
                }
            }
        }

        String nameFile = new Date().toString();

        FileOutputStream fos = new FileOutputStream("test.xlsx");
        workbook.write(fos);
        fos.close();
        workbook.close();

    }
}

class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

enum HeaderEnum {
    NAME(1, "Họ và tên"), AGE(2, "Tuổi");

    private int index;
    private String label;

    private HeaderEnum(int index, String label) {
        this.index = index;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String lable) {
        this.label = lable;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
