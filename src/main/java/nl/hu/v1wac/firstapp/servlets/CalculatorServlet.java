package nl.hu.v1wac.firstapp.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/CalculatorServlet.do")
public class CalculatorServlet extends HttpServlet {
	
 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 int number1 = Integer.parseInt(req.getParameter("number1"));
	 int number2 = Integer.parseInt(req.getParameter("number2"));
	 String teken = req.getParameter("teken");
	 double antwoord = 3;
	 
		if (teken.equals("+")){
			 antwoord = number1 + number2; 
		 }else if (teken.equals("-")){
			 antwoord = number1 - number2; 
		 }else if (teken.equals("*")){
			 antwoord = number1 * number2; 
		 }else if (teken.equals("/")){
			 antwoord = number1 / number2; 
		 }
	 	
	 PrintWriter out = resp.getWriter();
	 resp.setContentType("text/html");
	 
	 out.println("<!DOCTYPE html>");
	 out.println("<html>");
	 out.println(" <title>Dynamic Example</title>");
	 out.println(" <body>");
	 out.println(" <h2>Antwoord</h2>");
	 out.println(" <h2>" + number1 +" "+ teken +" "+ number2 +" = "+ antwoord + "</h2>");
	 out.println(" </body>");
	 out.println("</html>");
 }
}