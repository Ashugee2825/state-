
package state;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class StateCntrl2
 */
@WebServlet("/state/StateCntrl2")
public class StateCntrl2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StateCntrl2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opr = request.getParameter("opr");
		if (opr.equals("Save")) {                    // ADD NEW Record

			String code = request.getParameter("code");
			String value = request.getParameter("value");

			System.out.println(code);
			System.out.println(value);

			State state = new State();
			state.setCode(code);
			state.setValue(value);

			StateDBService stateDBService = new StateDBService();
			stateDBService.createState(state);

			response.sendRedirect("saveSuccessState.jsp");
			/* response.sendRedirect("stateDashboard.jsp"); */

		} else if (opr.equals("view")) { // View record
			{
			int id = Integer.parseInt(request.getParameter("id"));
			State state = new State();
			StateDBService stateDBService = new StateDBService();
			state = stateDBService.getstate(id);

			request.setAttribute("state", state);
		    	//response.sendRedirect("viewState.jsp");
			
			RequestDispatcher rd = request.getRequestDispatcher("viewState.jsp");
			rd.forward(request, response);

			// state.displayValues();
			// response.sendRedirect("viewState.jsp");
		}
	}		

		else if (opr.equals("edit")) {            // Edit record
			{
				int id = Integer.parseInt(request.getParameter("id"));
				
				State state = new State();
				StateDBService stateDBService = new StateDBService();
				state = stateDBService.getstate(id);

				request.setAttribute("State", state);
				
				RequestDispatcher rd = request.getRequestDispatcher("editState.jsp");
				rd.forward(request, response);

				// String id=request.getParameter("id");
				// response.sendRedirect("editState.jsp");
			}
		} 
		else if (opr.equals("Update")) { // UPDATE record
			{
				int id = Integer.parseInt(request.getParameter("id"));
				
				String code=request.getParameter("code");
				String value=request.getParameter("value");
				System.out.println(code);
				
				State state = new State();
				state.setId(id);
				state.setCode(code);
				state.setValue(value);
                
				StateDBService stateDBService = new StateDBService();
				stateDBService.updateState(state);
				System.out.println(code);
				System.out.println(value);
				System.out.println(id);
				
				
				// String id= request.getParameter("id");

				response.sendRedirect("updateState.jsp");
			}
		}
		
		
		else if (opr.equals("delete")) { // DELETE record
			{
				int id = Integer.parseInt(request.getParameter("id"));
				State state = new State();
				state.setId(id);
				StateDBService stateDBService = new StateDBService();
				stateDBService.deletestate(id);
                request.setAttribute("state", state);
                
				RequestDispatcher rd = request.getRequestDispatcher("deleteState.jsp");
				rd.forward(request, response);
				// String id= request.getParameter("id");

				// response.sendRedirect("deleteState.jsp");
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opr = request.getParameter("opr");
		if (opr.equals("Save")) {

			String code = request.getParameter("code");
			String value = request.getParameter("value");

			System.out.println(code);
			System.out.println(value);

			State state = new State();
			state.setCode(code);
			state.setValue(value);

			StateDBService stateDBService = new StateDBService();
			stateDBService.createState(state);

			response.sendRedirect("saveSuccessState.jsp");
	}
	}
}
	
	