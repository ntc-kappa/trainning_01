package com.tas.controller;

import com.tas.dto.DeviceDto;
import com.tas.service.CategoryService;
import com.tas.service.DeviceService;
import com.tas.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/manager-tranning/devices", method = RequestMethod.GET)
    public ModelAndView deviceListPage(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
        ModelAndView mav = new ModelAndView("device/list-device");
        mav.addObject("devices", deviceService.getAll(page - 1));
        mav.addObject("totalPage", deviceService.getTotalPage());
        return mav;
    }

    @RequestMapping(value = {"/manager-tranning/devices/", "/manager-tranning/devices/{id}"}, method = RequestMethod.GET)
    public ModelAndView deviceEditsPage(@PathVariable(required = false) Integer id) {
        DeviceDto result = new DeviceDto();
        ModelAndView mav = new ModelAndView("device/edits-device");
        if (id != null) {
            result = deviceService.getById(id);
        }
        mav.addObject("categories", categoryService.getAll());
        mav.addObject("device", result);
        return mav;
    }

    @RequestMapping(value = {"/manager-tranning/devices/"}, method = RequestMethod.POST)
    public String deviceEditsPostPage(@Valid @ModelAttribute(name = "device") final DeviceDto deviceDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "device/edits-device";
        }
        DeviceDto result = deviceService.edits(deviceDto);
        return "redirect:/manager-tranning/devices";
    }

    @RequestMapping(value = "/manager-tranning/exportDeviceCSV", method = RequestMethod.GET)
    public void exportDeviceExcel(HttpServletResponse response) {
        ExcelUtil.exportDeviceExcel(deviceService.getAll(), response);
    }

}
