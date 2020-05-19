package com.tas.test;

import com.tas.common.Path;
import com.tas.entity.PositionEntity;
import com.tas.excel.impl.IOExcelImpl;
import com.tas.repository.PositionRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        PositionRepository repository=(PositionRepository) context.getBean(PositionRepository.class);
        File file1 = new File(Path.POSITION_XML);

        File file = new File("C:/demo/employee.xls");

        file.getParentFile().mkdirs();
        Workbook  workbook= null;
        try {
            workbook = IOExcelImpl.getInstance().exportExcel(repository, PositionEntity.class,file1);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try (FileOutputStream outFile = new FileOutputStream(file)) {
            workbook.write(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Created file: " + file.getAbsolutePath());

    }

}
