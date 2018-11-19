package com.itacademy.servlets;

import com.itacademy.dto.ResidentDto;
import com.itacademy.dto.ResidentFilterDto;
import com.itacademy.database.entity.enum_.Gender;
import com.itacademy.service.resident.ResidentService;
import com.itacademy.service.resident.ResidentServiceImpl;
import com.itacademy.spring.SpringContextLoader;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/residents", name = "Residents")
public class ResidentServlet extends HttpServlet {

    private final ResidentService residentService = (ResidentService) SpringContextLoader.getBean(ResidentServiceImpl.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String gender = req.getParameter("gender");
        Integer limit = parseLimit(req.getParameter("limit"));
        Integer offset = parseOffset(req.getParameter("offset"));

        int currentPage = Integer.valueOf(req.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(req.getParameter("recordsPerPage"));

        ResidentFilterDto filter = new ResidentFilterDto();
        if (firstName != null && !firstName.isEmpty()) {
            filter.setFirstNamePredicate(firstName);
        }
        if (secondName != null && !secondName.isEmpty()) {
            filter.setSecondNamePredicate(secondName);
        }
        if (limit != null) {
            filter.setLimit(limit);
        }
        if (offset != null) {
            filter.setOffset(offset);
        }
        if (gender != null && !gender.isEmpty()) {
            filter.setGenderPredicate(Gender.valueOf(gender));
        }

        List<ResidentDto> filterResult = residentService.getByFilter(filter);

                int start = (currentPage - 1) * recordsPerPage;
        int count;
        if (start + recordsPerPage > filterResult.size()) {
            count = filterResult.size();
            req.setAttribute("residents", filterResult.subList(start, filterResult.size()));
        } else {
            count = filterResult.size();
            req.setAttribute("residents", filterResult.subList(start, start + recordsPerPage));
        }

        int numberOfPages = count / recordsPerPage + (count % recordsPerPage == 0 ? 0 : 1);

        req.setAttribute("firstName", firstName);
        req.setAttribute("secondName", secondName);
        req.setAttribute("gender", gender);
        req.setAttribute("limit", limit);
        req.setAttribute("offset", offset);

        req.setAttribute("noOfPages", numberOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.getRequestDispatcher("/WEB-INF/jsp/residentSearch.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/residentSearch.jsp").forward(req, resp);
    }

    private int parseLimit(String value) {
        Integer result;
        if (value.isEmpty()) {
            return 100;
        }
        try {
            result = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            result = 100;
        }
        return result;
    }

    private int parseOffset(String value) {
        Integer result;
        if (value.isEmpty()) {
            return 0;
        }
        try {
            result = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            result = 0;
        }
        return result;
    }
}
