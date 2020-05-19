package com.tas.service.impl;

import com.tas.common.Path;
import com.tas.converter.CategoryConverter;
import com.tas.dto.CategoryDto;
import com.tas.entity.CategoryEntity;
import com.tas.entity.PositionEntity;
import com.tas.excel.impl.IOExcelImpl;
import com.tas.repository.CategoryRepository;
import com.tas.service.CategoryService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        return categoryConverter.toListDto(entities);
    }
    @Autowired
    private  CategoryRepository repository;
    public void setExcel(HttpServletResponse response){
        try {
            response.setContentType("application/octet-stream:UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename= Category.xlsx");
            response.setHeader("charset", "UTF-8");
            response.setStatus(200);

            File fileHeader= ResourceUtils.getFile(Path.CATEGORY_XML);

            Workbook workbook= IOExcelImpl.getInstance().exportExcel(repository, CategoryEntity.class,fileHeader);

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
}
