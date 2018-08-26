package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {

	private DataSource dataSource;

	public StudentDBUtil(DataSource theDataSource) {

		dataSource = theDataSource;
	}

	public List<Student> getStudents() throws Exception {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1 get a connection
			myConn = dataSource.getConnection();

			// 2 create a sql statement

			String sql = "select * from student order by last_name";
			myStmt = myConn.createStatement();

			// 3 execute query, and store the result in the Result Set myRs
			myRs = myStmt.executeQuery(sql);

			// 4 process result set

			while (myRs.next()) {

				// 1. retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// 2.create new student object
				Student tempStudent = new Student(id, firstName, lastName, email);

				// 3.add it to the list of students
				students.add(tempStudent);

			}

			return students;

		} finally {
			// 5 close JDBC objects
			
			close(myConn,myStmt,myRs);

		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if(myRs!=null) {
				myRs.close();
			}
			if(myStmt!=null) {
				myStmt.close();
			}
			if(myConn!=null) {
				myConn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
