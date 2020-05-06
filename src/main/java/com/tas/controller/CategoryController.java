package com.tas.controller;

import com.tas.common.PageConfig;
import com.tas.entity.CategoryEntity;
import com.tas.repository.CategoryRepository;
import com.tas.service.UserEntityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository repository;
//    @@Autowired
//    private UserEntityDetailService userEntityDetailService;
    @RequestMapping(value = {"/category/{numberPage}"}, method = RequestMethod.GET)
    public String homeCategory(@PathVariable("numberPage") int numberPage, HttpServletRequest request, Model model) {
        String notify = request.getParameter("notify");
        model.addAttribute("notify", notify);
        Pageable pageable = new PageRequest(numberPage, PageConfig.SIZE);
        Page<CategoryEntity> page = repository.findAll(pageable);

//        for (CategoryEntity entity : page.getContent()
//        ) {
//            System.out.println(entity.getId());
//        }
//        System.out.println(page.getTotalPages());
        model.addAttribute("list", page.getContent());

        model.addAttribute("before", numberPage > 0 ? numberPage - 1 : 0);
//        model.addAttribute("before", 2);
        model.addAttribute("after", numberPage == page.getTotalPages() ? numberPage : numberPage + 1);
//        System.out.println(userEntityDetailService.);

        return "category-dir/category";
    }

    @RequestMapping(value = {"/delete-category/{id}"}, method = RequestMethod.GET)
    public RedirectView deleteCategory(@PathVariable("id") Integer id, Model model) {
        repository.delete(id);
        System.out.println("delete id =" + id);
        String notify = "Xóa id " + id + " thành công";
        model.addAttribute("notify", notify);

        return new RedirectView("/trainning_01_war_exploded/category/0");
    }
    @RequestMapping(value = {"update-category/{id}"})
    public String UpadateCategory(){
        return "update-category";
    }
}
