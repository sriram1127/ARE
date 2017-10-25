import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class ARServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String support = req.getParameter("support");
		String confidence = req.getParameter("confidence");
		String str = req.getParameter("decisionattribute");

		String decisionAttribute = str.substring(str.indexOf('-') + 1);
		String decisionAttributeFrom = req.getParameter("decisionvaluefrom");
		String decisionAttributeTo = req.getParameter("decisionvalueto");
		String attributes[] = req.getParameter("attributes").split(",");
		String stableattributes[] = req.getParameterValues("stableattribute");
		int stableIndex[] = new int[stableattributes.length];
		for (int i = 0; i < stableattributes.length; i++) {
			stableIndex[i] = Integer.parseInt(stableattributes[i].split("-")[0]);
		}
		String files = getServletContext().getRealPath("/WEB-INF/" + req.getParameter("datafile"));

		ARE.SetAttributeNames(attributes);
		ARE.SetDataFilePath(files);
		ARE.SetSupportThreshold(Integer.parseInt(support));
		ARE.SetConfidenceThreshold(Integer.parseInt(confidence));
		ARE.SetDecisionAttribute(decisionAttribute);
		ARE.SetDecisionFromValue(decisionAttributeFrom);
		ARE.SetDecisionToValue(decisionAttributeTo);
		ARE.SetStableAttribute(stableIndex);

		try {
			ARE.aras();
			req.getServletContext().getRequestDispatcher("/info.html").forward(req, resp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
