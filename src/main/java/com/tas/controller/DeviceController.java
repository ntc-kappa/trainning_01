package com.tas.controller;

import com.tas.dto.DeviceDto;
import com.tas.service.CategoryService;
import com.tas.service.DeviceService;
import com.tas.utils.ExcelUtil;
import com.tas.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.List;

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
    public String deviceEditsPostPage(@Valid @ModelAttribute(name = "device") final DeviceDto deviceDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "device/edits-device";
        }
        DeviceDto result = deviceService.edits(deviceDto);
        return "redirect:/manager-tranning/devices";
    }

    @RequestMapping(value = "/manager-tranning/exportDeviceCsv", method = RequestMethod.GET)
    public void exportDeviceExcel(HttpServletResponse response) {
        List<DeviceDto> list = deviceService.getAll();
        ExcelUtil excelUtil = new ExcelUtil();
        boolean checked = excelUtil.exportExcel(list, response);
        //LOG CHECKED
    }

    @RequestMapping(value = "/manager-tranning/upload-device", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String importDeviceExcel(@RequestParam(name = "file") MultipartFile multipartFile) {
        File file = FileUtil.toFile(multipartFile);
        ExcelUtil excelUtil = new ExcelUtil();
        List<DeviceDto> list = excelUtil.importFileExcel(file, DeviceDto.class);
        if (list != null) {
            long numberRecords = deviceService.saveS(list);
            //LOG
        } else {
            return "redirect:/manager-tranning/devices?error=";
        }
        return "redirect:/manager-tranning/devices";
    }

}
