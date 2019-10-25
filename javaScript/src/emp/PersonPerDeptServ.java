package emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/PersonPerDeptServ")
public class PersonPerDeptServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PersonPerDeptServ() {
		super();

	}

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	         request.setCharacterEncoding("UTF-8");
	            response.setCharacterEncoding("UTF-8");
	         
	            
	            EmpDAO dao = new EmpDAO();
	            
	            Map<String,Integer> list = dao.getPersonPerDept();
	            
	            Set<Map.Entry<String,Integer>> mapSet = list.entrySet();
	                  
	            JSONArray ary = new JSONArray();

	            JSONObject obj = new JSONObject();
	            
	            for(Map.Entry<String,Integer> map : mapSet) {
	               
	               obj = new JSONObject();
	               obj.put("name",map.getKey());
	               obj.put("data",map.getValue());
	               ary.add(obj);
	            }
	            
	            PrintWriter out = response.getWriter();
	            out.println(ary);   
	   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
