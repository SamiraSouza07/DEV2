package com.example.operacoesmatematicas;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

@WebServlet(name = "OperacoesServlet", value = "/OperacoesServlet")
public class OperacoesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DecimalFormat deci = new DecimalFormat("#.##");
        String num1Strg = request.getParameter("numero1");
        String num2Strg = request.getParameter("numero2");

        //convertendo
        double numero1 = Double.parseDouble(num1Strg);
        double numero2 = Double.parseDouble(num2Strg);

        //calculos
        double soma = numero1+numero2;
        double subtracao = numero1-numero2;
        double multiplicacao = numero1*numero2;
        double divisao = numero1/numero2;

        //resposta
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Resultado das quatro operações matemáticas básicas</h1>");
        out.println("<h2>Soma</h2>");
        out.println("<p>"+numero1+" + "+numero2+" = "+deci.format(soma)+"</p>");
        out.println("<h2>Subtração</h2>");
        out.println("<p>"+numero1+" - "+numero2+" = "+deci.format(subtracao)+"</p>");
        out.println("<h2>Multiplicação</h2>");
        out.println("<p>"+numero1+" * "+numero2+" = "+deci.format(multiplicacao)+"</p>");
        out.println("<h2>Divisão</h2>");
        out.println("<p>"+numero1+" / "+numero2+" = "+deci.format(divisao)+"</p>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
