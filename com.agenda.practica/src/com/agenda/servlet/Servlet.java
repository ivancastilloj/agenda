package com.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Agenda","root","");
			Statement statement = connect.createStatement();
			ResultSet resultSet;
		if (request.getParameter("nombre")!=null || request.getParameter("apellido")!=null || request.getParameter("telefono")!=null ) {
			
			String resultado= " status = '1' ";
			if (request.getParameter("nombre")!="") {
				resultado=resultado+  "and nombre='" + request.getParameter("nombre") +"'";
				
				System.out.println(resultado);
			}
			
			if (request.getParameter("apellido")!="") {
				resultado= resultado+"and apellido='" + request.getParameter("apellido") +"'";

				System.out.println(resultado);
			}
			
			if (request.getParameter("telefono")!="") {
				resultado=  resultado+"and telefono='" + request.getParameter("telefono") +"'";

				System.out.println(resultado);
			}
			
			resultSet = statement.executeQuery("select * from Agenda.contactos Where"+resultado);	
			
//			String nombre= request.getParameter("nombre");
//			
//			resultSet = statement.executeQuery("select * from Agenda.contactos Where Nombre='" + nombre +"'");
//		else if (request.getParameter("apellido")!=null) {
//			
//String apellido= request.getParameter("apellido");
//			
//			resultSet = statement.executeQuery("select * from Agenda.contactos Where Apellido='" + apellido +"'");
//		
//		}else if (request.getParameter("telefono")!=null) {
//			
//			String telefono= request.getParameter("telefono");
//						
//						resultSet = statement.executeQuery("select * from Agenda.contactos Where Telefono='" + telefono +"'");
//		}else if (request.getParameter("nombre")!=null && request.getParameter("apellido")!=null) {
//			
//			String nombre= request.getParameter("nombre");
//			String apellido= request.getParameter("apellido");			
//						resultSet = statement.executeQuery("select * from Agenda.contactos Where Nombre='" + nombre +"' and Apellido='" +apellido+"'");
//					
					
		
		}else{
			resultSet = statement.executeQuery("select * from Agenda.contactos");
		}
		out.println("<head> <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\"></head>");
			out.println("<table border=\"1\"><th>id</th><th>nombre</th><th>apellido</th><th>telefono</th><th>direccion</th><th>mail</th>");
			while(resultSet.next()) {
				out.println("<tr>");
				out.println("<td>" + resultSet.getInt("id") + "</td>");
                out.println("<td>" + resultSet.getString("nombre") + "</td>");
            	out.println("<td>" + resultSet.getString("apellido") + "</td>");
				out.println("<td>" + resultSet.getString("telefono") + "</td>");
				out.println("<td>" + resultSet.getString("direccion") + "</td>");
				out.println("<td>" + resultSet.getString("mail") + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
