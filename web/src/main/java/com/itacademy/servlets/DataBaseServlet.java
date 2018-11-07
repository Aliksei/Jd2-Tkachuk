//package com.itacademy.servlets;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/databaseInterface")
//public class DataBaseServlet extends HttpServlet {
//
//    private final CountryService countryService = CountryServiceImpl.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("countries", countryService.getAllCountries());
//        getServletContext().getRequestDispatcher("/WEB-INF/jsp/databaseInterface.jsp").forward(req, resp);
//    }
//}
