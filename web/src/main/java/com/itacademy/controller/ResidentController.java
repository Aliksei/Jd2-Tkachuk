package com.itacademy.controller;

import com.itacademy.dto.ResidentDto;
import com.itacademy.dto.ResidentFilterDto;
import com.itacademy.service.resident.ResidentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResidentController {

    private final ResidentService residentService;

    @Autowired
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/residents")
    public String showSearchFilter(Model model) {
        model.addAttribute("filter", new ResidentFilterDto());
        return "residents";
    }

    @PostMapping("/residents")
    public String search(@ModelAttribute("filter") ResidentFilterDto filter,
                         @RequestParam("currentPage") Integer currentPage,
                         @RequestParam("recordsPerPage") Integer recordsPerPage,
                         Model model) {
        List<ResidentDto> residents = residentService.getByFilter(filter);
        if (recordsPerPage == null){
            recordsPerPage = 25;
        }
        int start = (currentPage - 1) * recordsPerPage;
        int count;
        if (start + recordsPerPage > residents.size()) {
            count = residents.size();
            residents = residents.subList(start, residents.size());
        } else {
            count = residents.size();
            residents = residents.subList(start, start + recordsPerPage);
        }

        int numberOfPages = count / recordsPerPage + (count % recordsPerPage == 0 ? 0 : 1);
        model.addAttribute("residents", residents);
        model.addAttribute("filter", filter);
        model.addAttribute("noOfPages", numberOfPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("recordsPerPage", recordsPerPage);
        return "residents";
    }
}
