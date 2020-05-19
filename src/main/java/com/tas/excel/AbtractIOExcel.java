package com.tas.excel;

import com.tas.common.Loggable;
import com.tas.common.Path;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class AbtractIOExcel implements  Loggable {


    protected XSSFCellStyle createCellStyle(XSSFWorkbook workbook) {
        XSSFFont xssfFont = workbook.createFont();
        xssfFont.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();

        style.setFont(xssfFont);
        return style;
    }

    protected  List<Header> getHeadderFromXML(File file) throws ParserConfigurationException, SAXException, IOException {
        List<Header> headers = new ArrayList<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList nodeList = document.getDocumentElement().getElementsByTagName("head");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Header header=new Header();
            Element element=(Element)node;
            header.setName(element.getAttribute("name"));
            header.setType(CellTypeWrapper.getCellTypeFromName(element.getAttribute("type")));
            header.setValue(element.getTextContent().trim());
            headers.add(header);

        }
        return headers;
    }

    public static void main(String[] args)  throws  NoSuchFieldException,IllegalAccessException{
//        File file = new File(Path.POSITION_XML);
//        try {
//            List<Header> list = getHeadderFromXML(file);
//            for (Header s : list
//            ) {
//                System.out.println(s.getValue());
//                System.out.println(s.getType().getCode());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Class<PositionEntity> entityClassL=PositionEntity.class;
//        PositionEntity entity=new PositionEntity();
//        entity.setName("Hello");
//        entity.setDescription("chào mọi người");
//        Field name=entityClassL.getDeclaredField("name");
//        name.setAccessible(true);
//        String s=(String)name.get(entity);
//        System.out.println(s);
//        Field[] field=entityClassL.getDeclaredFields();
//        for (Field  field1:field
//             ) {
//            System.out.println(field1.getName());
//        }




    }
    public Field[] getListFeild(Class<T> aClass){
            return aClass.getDeclaredFields();


    }
}