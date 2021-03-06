package controller;
import connection.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.SendMail;


public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; // internally id maintained for class
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PreparedStatement ps;
		response.setContentType("text/html");     // type of response given by the server
		
//		String Eid = request.getParameter("EmployeeId");
		String Fname = request.getParameter("FirstName");      // names given as data parameter of ajax call
		String Lname = request.getParameter("LastName");
		String Q1 = request.getParameter("Q1");
		String A1 = request.getParameter("A1");
		String Q2 = request.getParameter("Q2");
		String A2 = request.getParameter("A2");
		String Role = request.getParameter("Role");
		String mailid = request.getParameter("mailid");
		String pwd = request.getParameter("pwd");
		PrintWriter out= response.getWriter();
		
		
		try {
			Connection con=null;
			con = ConnectionManager.getConnection();
			ps=con.prepareStatement("Select Email from `user` where Email=?");
			ps.setString(1, mailid);
				
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				out.println("User with email"+ mailid+"  already exists");
				
			}
			else{																												
					
			ps = con.prepareStatement("insert into `user`(Fname,Lname,Q1,A1,Q2,A2,Role,Email,Password) values(?,?,?,?,?,?,?,?,?)");  // user is the table name no of ?= no of columns
																													
						//ps.setString(1, Eid); // 0,1,2,3 depicts the serial order of question marks in table. All question markks are in 1 to 1 corrospondence with column names
						ps.setString(1, Fname);
						ps.setString(2, Lname);
						ps.setString(3, Q1);
						ps.setString(4, A1);
						ps.setString(5, Q2);
						ps.setString(6, A2);
						ps.setString(7, Role);
						ps.setString(8, mailid);
						ps.setString(9, pwd);
						
						int i = ps.executeUpdate();
						if (i > 0) {
							SendMail.sendMail(mailid, Fname, "Dear" +Fname+" Your account has been created. Welcome to TechQuo-Technical Discussion Forum" );
							System.out.println("You are successfully registered...");
							
							out.println("Registeration Successful");
						}
	
		}
		}
							
		catch(Exception se)
        {
            se.printStackTrace();
        }	
	}
	
}