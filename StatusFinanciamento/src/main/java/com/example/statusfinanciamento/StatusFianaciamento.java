package com.example.statusfinanciamento;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

@WebServlet(name = "StatusFianaciamento", value = "/StatusFianaciamento")
public class StatusFianaciamento extends HttpServlet {
    Metodos metodos = new Metodos();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        DecimalFormat deci = new DecimalFormat("#.##");

        String usuario = request.getParameter("usuario");
        double salarioBruto = Double.parseDouble(request.getParameter("salario-bruto"));
        double financiamento = Double.parseDouble(request.getParameter("financiamento"));

        double salarioLiquido = metodos.calcularImpostos(salarioBruto);
        String statusFinanciamento = "";
        if(salarioLiquido*3 < financiamento){
            statusFinanciamento= "Negado";
        }else{
            statusFinanciamento="Liberado";
        }

        PrintWriter out = response.getWriter();
        out.println("<html lang=\"pt-br\">");
        out.println("<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Status finanaciamento</title>\n" +
                "  <style>\n" +
                "    *{\n" +
                "      color: #3A2B17;\n" +
                "      background-color: rgb(255, 243, 228);\n" +
                "      font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\n"+
                "    }\n" +
                "    p{\n"+
                "      font-size: 15px;\n"+
                "     }\n"+
                "    #verde{\n"+
                "       color: green;\n"+
                "     }\n"+
                "    #vermelho{\n"+
                "       color: rgb(219, 19, 19);;\n"+
                "     }\n"+
                "  </style>\n" +
                "</head>");
        out.println("<body>");
        out.println("<h1>"+"Status do Financiamento"+"</h1>");
        out.println("<p>Nome do usuário: <strong>"+usuario+"</strong></p>");
        out.println("<p>Salário bruto: <strong>R$"+deci.format(salarioBruto)+"</strong></p>");
        out.println("<p>Salário líquido: <strong>R$"+deci.format(salarioLiquido)+"</strong></p>");
        out.println("<p>Financiamento: <strong>R$"+deci.format(financiamento)+"</strong></p>");
        if(statusFinanciamento.equalsIgnoreCase("Liberado")){
            out.println("<p>Status do financiamento: <strong id=\"verde\">"+statusFinanciamento+"</strong></p>");
        }else{
            out.println("<p>Status do financiamento: <strong id=\"vermelho\">"+statusFinanciamento+"</strong></p>");
        }
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
