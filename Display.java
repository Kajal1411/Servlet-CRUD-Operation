package myPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Display extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<html><body>");
		
		ServletContext context=getServletContext();
		String url=(String) context.getAttribute("url");
		String username=(String) context.getAttribute("username");
		String password=(String) context.getAttribute("password");
									
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection(url,username,password);
			Statement st=con.createStatement();
			ResultSet RS=st.executeQuery("select * from servletCRUD");
			
			out.print("<br><br>");
			out.print("<table border='2px solid black' style='margin-left:26%; background-color:#e6f7ff;'>");
			
			out.print("<tr style='background-color: #003366; color: white;'>");
			out.print("<th>RollNo</th>");
			out.print("<th>Name</th>");
			out.print("<th>Email</th>");
			out.print("<th>Mob</th>");
			out.print("<th>Address</th>");
			out.print("<th>Gender</th>");
			out.print("<th>Education</th>");
			out.print("</tr>");
			
			while(RS.next())
			{
				out.print("<tr style='background-color: #f2f2f2;'>");
				out.print("<td>"+RS.getInt(1)+"</td>");
				out.print("<td>"+RS.getString(2)+"</td>");
				out.print("<td>"+RS.getString(3)+"</td>");
				out.print("<td>"+RS.getString(4)+"</td>");
				out.print("<td>"+RS.getString(5)+"</td>");
				out.print("<td>"+RS.getString(6)+"</td>");
				out.print("<td>"+RS.getString(7)+"</td>");
				out.print("</tr>");		
			}
			out.print("</table>");
			out.print("<br>");
			out.print("<a href='first.html'><button style='background-color:gray; color:white; margin-left:26%'>Back</button></a>");		
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		out.print("</body></html>");
	}
	


}
