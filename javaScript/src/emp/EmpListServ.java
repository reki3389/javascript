package emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@WebServlet("/EmpListServ")
public class EmpListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpListServ() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
	
		EmpDAO dao = new EmpDAO();
		JSONArray inAry = new JSONArray();
		JSONArray outAry = new JSONArray();
		List<Employee> list = dao.getJsonData();
		for(Employee emp : list) {
			inAry = new JSONArray();
			inAry.add(emp.getFirstName());
			inAry.add(emp.getLastName());
			inAry.add(emp.getSalary());
			inAry.add(emp.getHireDate());
			inAry.add(emp.getEmail());
			inAry.add(emp.getJobId());
			outAry.add(inAry);
		}

		JSONObject obj = new JSONObject();
		obj.put("data", outAry);
		PrintWriter out = response.getWriter();
		out.println(obj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
