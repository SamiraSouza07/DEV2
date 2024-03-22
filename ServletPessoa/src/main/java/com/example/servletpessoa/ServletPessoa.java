package com.example.servletpessoa;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletPessoa", value = "/ServletPessoa")
public class ServletPessoa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String nome = request.getParameter("nome");
        int idade = Integer.parseInt(request.getParameter("idade"));
        Pessoa pessoa = new Pessoa(nome,idade);
        Gson gson = new Gson();
        String json = gson.toJson(pessoa);


        PrintWriter out = response.getWriter();
        out.println(json);
//        out.println("<html><body>");
//        out.println("<h1>Servlet Pessoa</h1>");
//        out.println("<p>Nome: "+nome+"</p>");
//        out.println("<p>Idade:"+idade+"</p>");
//        out.println("<p>JSON: "+json+"</p>");
//        out.println("</body></html>");

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
