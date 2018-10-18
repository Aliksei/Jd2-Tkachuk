package it.academy.servlets;

import by.itacademy.services.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/databaseInterface")
public class DataBaseServlet extends HttpServlet {

    private final ClientServiceImpl clientService = ClientServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", clientService.getAllClients());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/databaseInterface.jsp").forward(req, resp);
    }
}
