package com.tas.service;

import com.tas.common.Loggable;
import com.tas.common.Path;
import com.tas.entity.PositionEntity;
import com.tas.excel.impl.IOExcelImpl;
import com.tas.repository.PositionRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PositionSevice implements Loggable {
    @Autowired
    PositionRepository repository;
    public void getEcxel(HttpServletResponse response){
     try {
         response.setContentType("application/octet-stream:UTF-8");
         response.setHeader("Content-Disposition", "attachment; filename= Position_"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+".xlsx");
         response.setHeader("charset", "UTF-8");
         response.setStatus(200);
         ClassLoader classLoader=ClassLoader.getSystemClassLoader();

         File fileHeader= ResourceUtils.getFile(Path.POSITION_XML);

         Workbook workbook= IOExcelImpl.getInstance().exportExcel(repository, PositionEntity.class,fileHeader);

         OutputStream outputStream= response.getOutputStream();
         workbook.write(outputStream);
         outputStream.flush();
         outputStream.close();
         workbook.close();

     } catch (NoSuchFieldException e){
        e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
     } catch (IOException exception) {
         exception.printStackTrace();
     }

    }
    public  File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}

