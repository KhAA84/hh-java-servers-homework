package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import counter.Counter;

import java.io.IOException;
import java.util.Arrays;

@WebServlet
public class ClearCounterServlet extends HttpServlet {

    private final Counter counterInstance = counter.Counter.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCookies() == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (!Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("hh-auth"))
                .anyMatch(cookie -> cookie.getValue().length() > 10)) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        counterInstance.clearCounter();
        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().print("Counter value has been set to zero");
    }
}
