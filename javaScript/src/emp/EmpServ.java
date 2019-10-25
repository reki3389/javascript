package emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpServ
 */
@WebServlet("/EmpServ")
public class EmpServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpServ() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 깨질 때 UTF-8로 변경.
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		EmpDAO dao = new EmpDAO();
		dao.getEmpList();
		List<Employee> list = dao.getEmpList();
		out.println("{\"data\":[");
		int cnt = 0;
		for (Employee emp : list) {
			System.out.println(emp.getFirstName() + "," + emp.getLastName());
			out.println("{\"firstName\":\"" + emp.getFirstName() + "\",\"lastName\":\"" + emp.getLastName()
					+ "\",\"email\":\"" + emp.getEmail() + "\"}");
			cnt++;
			if (list.size() != cnt) {
				out.println(",");
			}
		}
		out.println("]}");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
