package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDBUtil studentDBUtil;

	@Resource(name = "jdbc/web_student_tracker")

	private DataSource dataSource;

	@Override
	public void init() throws ServletException {

		super.init();

		// create our student db util, and pass in the conn pool(=datasource)

		try {
			studentDBUtil = new StudentDBUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1 list the students, in MVC fashion

		listStudents(request, response);

		// 2

	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from DB Util
		
		List<Student> students = studentDBUtil.getStudents();

		// add students to the request
		
		request.setAttribute("STUDENT_LIST", students);

		// send to JSP page (view)
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_students.jsp");
		dispatcher.forward(request, response);
	}

}
