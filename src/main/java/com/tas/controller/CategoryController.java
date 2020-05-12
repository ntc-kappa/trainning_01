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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository repository;

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
//    @RequestMapping(value = {"update-category/{id}"})
//    public String UpadateCategory(){
//        return "update-category";
//    }
    @RequestMapping("/category/add")
    public String addCategory(Model model){
        model.addAttribute("category",new CategoryEntity());
        return "category-dir/add-category";
    }
    @PostMapping("/category/add/data")
    public RedirectView addCategoryData( @ModelAttribute("category")CategoryEntity entity, Model model){
        repository.save(entity);

        String notify = "thêm category thành công";
        model.addAttribute("notify", notify);
        return new RedirectView("/trainning_01_war_exploded/category/0");
    }
    @RequestMapping("/category/update/{id}")
    public  String updateCategory(@PathVariable("id") Integer id,Model model){

        CategoryEntity entity=repository.findOne(id);
        model.addAttribute("category",entity);


        return "category-dir/edit-category";
    }
    @RequestMapping("/category/update/data")
    public  RedirectView updateCategoryData( @ModelAttribute("category")CategoryEntity entity, Model model ){
        repository.save(entity);
        String notify = "update category thành công";
        model.addAttribute("notify", notify);
        return new RedirectView("/trainning_01_war_exploded/category/0");
    }
    @RequestMapping(value = "/category/delete",method = RequestMethod.POST)
    public  RedirectView deleteCategory( HttpServletRequest request, Model model ){
        String notify;
        String list=request.getParameter("list");
        System.out.println(list);
        StringTokenizer tokenizer=new StringTokenizer(list,"+");
//        Map<Integer,Integer> map=new HashMap<>();
        Set<Integer> set=new HashSet<>();
        while (tokenizer.hasMoreTokens()){
            Integer integer=Integer.valueOf(tokenizer.nextToken());
//            if (map.containsKey(integer)){
//                  map.remove(integer);
//            }else {
//                map
//            }
            if(set.contains(integer)){
                set.remove(integer);
            } else {
                set.add(integer);
            }
        }
        if(set.size() >0){
            String s="";
            for (Integer i:set
                 ) {
                repository.delete(i);
            }

            notify    = "delete category thành công";
        }else {
            notify    = "vui lòng chọn ít nhất 1 category";
        }



        model.addAttribute("notify", notify);
        return new RedirectView("/trainning_01_war_exploded/category/0");
    }
    @RequestMapping(value = "/category/search")
    public  String searchCategoryData(Model model ,HttpServletRequest request){
        String name= request.getParameter("name");
        List<CategoryEntity> entities=repository.findByNameContains(name);
        model.addAttribute("list",entities);

        return "category-dir/category";
    }

}
