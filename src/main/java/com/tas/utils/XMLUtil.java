package com.tas.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class XMLUtil {

    public static Map<String, String> getAllFiled(File file, String header) {
        Map<String, String> map = new TreeMap<>();
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("header");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.getAttribute("name").equals(header)) {
                    NodeList nodeListChild = element.getElementsByTagName("field");
                    for (int j = 0; j < nodeListChild.getLength(); j++) {
                        Element elementChild = (Element) nodeListChild.item(j);
                        map.put(elementChild.getTextContent(), elementChild.getAttribute("type"));
                    }
                    return map;
                }
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        File file = new File("xml/header-excel.xml");
        Map<String, String> map = getAllFiled(file, "SINHVIEN");
        System.out.println(map);
    }
}
