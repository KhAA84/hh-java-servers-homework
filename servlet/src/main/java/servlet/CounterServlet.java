package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import counter.Counter;

@WebServlet
public class CounterServlet extends HttpServlet {

    private final Counter counterInstance = counter.Counter.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().print(counterInstance.getCounter());
        resp.setStatus(HttpServletResponse.SC_OK);    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().print("Counter value has been incremented to " + counterInstance.changeCounter(1));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int value;
        try {
            value = Integer.parseInt(req.getHeader("Subtraction-Value"));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        resp.getWriter().print("Counter value has been changed to " + counterInstance.changeCounter(-value));
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
