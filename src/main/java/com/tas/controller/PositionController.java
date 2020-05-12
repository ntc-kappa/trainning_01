package com.tas.controller;

import com.tas.common.Loggable;
import com.tas.common.PageConfig;
import com.tas.entity.CategoryEntity;
import com.tas.entity.PositionEntity;
import com.tas.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

@Controller
public class PositionController implements Loggable {
    @Autowired
    PositionRepository repository;
        @RequestMapping(value = {"/position/{numberPage}"}, method = RequestMethod.GET)
    public String homePosition(@PathVariable("numberPage") int numberPage, HttpServletRequest request, Model model) {
        String notify = request.getParameter("notify");
        model.addAttribute("notify", notify);
        Pageable pageable = new PageRequest(numberPage, PageConfig.SIZE);
        Page<PositionEntity> page =repository.findAll(pageable);
        model.addAttribute("list", page.getContent());

        model.addAttribute("before", numberPage > 0 ? numberPage - 1 : 0);

        model.addAttribute("after", numberPage == page.getTotalPages() ? numberPage : numberPage + 1);


        return "position/list-position";
    }

    @RequestMapping("/position/add")
    public String addPosition(Model model){
        model.addAttribute("category",new PositionEntity() );
        return "position/add-position";
    }
    @PostMapping("/position/add/data")
    public RedirectView addPositionData(@ModelAttribute("category")PositionEntity entity, Model model){
        repository.save(entity);

        String notify = "thêm vị trí thành công";
        model.addAttribute("notify", notify);
        return new RedirectView("/trainning_01_war_exploded/position/0");
    }
    @RequestMapping("/position/update/{id}")
    public  String updatePosition(@PathVariable("id") Integer id,Model model){

        PositionEntity entity=repository.findOne(id);
        model.addAttribute("category",entity);


        return "position/edit-position";
    }
    @RequestMapping("/position/update/data")
    public  RedirectView updatePositionData( @ModelAttribute("category")PositionEntity entity, Model model ){
        repository.save(entity);
        String notify = "update vị trí thành công";
        model.addAttribute("notify", notify);
        return new RedirectView("/trainning_01_war_exploded/position/0");
    }
    @RequestMapping(value = "/positon/delete",method = RequestMethod.POST)
    public  RedirectView deleteCategory(HttpServletRequest request, Model model, Principal  principal){
        String notify;
        String list=request.getParameter("list");
        getLogger().info(principal.getName()+" request delete "+ list);
        StringTokenizer tokenizer=new StringTokenizer(list,"+");

        Set<Integer> set=new HashSet<>();
        while (tokenizer.hasMoreTokens()){
            Integer integer=Integer.valueOf(tokenizer.nextToken());
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
            getLogger().info(principal.getName()+" delete "+ list +" successfull");
            notify    = "Delete thành công";
        }else {
            notify    = "vui lòng chọn ít nhất 1 category";
            getLogger().info(principal.getName()+" delete "+ list +" falure");
        }



        model.addAttribute("notify", notify);
        return new RedirectView("/trainning_01_war_exploded/position/0");
    }
    @RequestMapping(value = "/position/search")
    public  String searchPositionData(Model model ,HttpServletRequest request){
        String name= request.getParameter("name");
        List<PositionEntity> entities=repository.findByNameContains(name);
        model.addAttribute("list",entities);

        return "position/list-position";
    }
}
