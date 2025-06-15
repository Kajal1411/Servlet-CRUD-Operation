package myPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<html><body>");
		
		String rollno=request.getParameter("rollno");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mob=request.getParameter("mob");
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		String education=request.getParameter("education");
		
		ServletContext context=getServletContext();
		String url=(String) context.getAttribute("url");
		String username=(String) context.getAttribute("username");
		String password=(String) context.getAttribute("password");
		
		int  r=Integer.parseInt(rollno);
							
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con=DriverManager.getConnection(url,username,password);
			

			Statement st=con.createStatement();
			ResultSet RS=st.executeQuery("select * from servletCRUD where rollno="+rollno);
			if(!RS.next())
			{
				out.print("<h2>Given Roll Number is not exist!!!</h2>");
				RequestDispatcher rd=request.getRequestDispatcher("/first.html");
				rd.include(request, response);
			}	
			else
			{
				PreparedStatement pst=con.prepareStatement("update servletCRUD set rollno=?,name=?,email=?,mob=?,address=?,gender=?,education=? where rollno="+r);
				pst.setInt(1,r);
				pst.setString(2, name);
				pst.setString(3, email);
				pst.setString(4, mob);
				pst.setString(5, address);
				pst.setString(6, gender);
				pst.setString(7, education);
				int i=pst.executeUpdate();
				if(i>0)
				{
					out.print("<h2>Data is Updated Successfully!!!</h2>");
					RequestDispatcher rd=request.getRequestDispatcher("/first.html");
					rd.include(request, response);
				}
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		out.print("</body></html>");
	}
}
