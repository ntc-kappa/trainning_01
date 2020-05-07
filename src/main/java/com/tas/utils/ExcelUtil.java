package com.tas.utils;

import com.tas.dto.DeviceDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    public static void exportDeviceExcel(EnumUtil.DeviceHeader deviceHeader, List<DeviceDto> deviceDtos, HttpServletResponse response) {

        //setResponse
        response.setContentType("application/octet-stream:UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + ("device_" + new Date().getTime() + ".xlsx"));
        response.setHeader("charset", "UTF-8");
        response.setStatus(200);

        Workbook workbook = new SXSSFWorkbook(-1);
        Sheet sheet = workbook.createSheet("Sheet 1");
        Row rowHeader = sheet.createRow(0);
        CellStyle cellStyle = createStyleForTitle(workbook);

        //Row(0) : Header - title
        for (EnumUtil.DeviceHeader e : EnumUtil.DeviceHeader.values()) {
            Cell cell = rowHeader.createCell(e.getIndex() - 1);
            cell.setCellValue(e.getLabel());
            cell.setCellStyle(cellStyle);
        }

        //Row(1-n) : Content
        for (int i = 0; i < deviceDtos.size(); i++) {
            Row row = sheet.createRow(i + 1);
            for (EnumUtil.DeviceHeader e : EnumUtil.DeviceHeader.values()) {
                switch (e) {
                    case CELL_1:
                        row.createCell(e.getIndex() - 1).setCellValue(deviceDtos.get(i).getCode());
                        break;
                    case CELL_2:
                        row.createCell(e.getIndex() - 1).setCellValue(deviceDtos.get(i).getName());
                        break;
                    case CELL_3:
                        row.createCell(e.getIndex() - 1).setCellValue(deviceDtos.get(i).getPrice());
                        break;
                    case CELL_4:
                        row.createCell(e.getIndex() - 1).setCellValue(deviceDtos.get(i).getNameCategory());
                        break;
                    default:
                        break;
                }
            }
        }

        try {
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CellStyle createStyleForTitle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}
