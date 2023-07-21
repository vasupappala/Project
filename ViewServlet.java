package com.sathya.userlist;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sathya.login.UserDao;
import com.sathya.userapp.UserData;


@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// calling DAO Layer
			ResultSet set = UserDao.listOfUser();
			if (set != null) {
				List<UserData> users = new ArrayList<>();
				while (set.next()) {
					UserData user = new UserData(set.getString(1), set.getString(2), set.getString(3), set.getString(4),
							set.getString(5), set.getString(6),set.getString(7),set.getString(8),set.getString(9));
					users.add(user);
				}
				request.setAttribute("users", users);
				RequestDispatcher dispatcher = request.getRequestDispatcher("viewsuccess.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
