package resource;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.CounterDto;

@Path("/counter")
public class CounterResource {

    private static final Counter counter = new Counter();

    private static final ObjectMapper mapper;
    static {
        mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() throws JsonProcessingException {
        CounterDto data = new CounterDto(LocalDateTime.now(), counter.getCounter());
        String s = mapper.writeValueAsString(data);
        return Response.ok(s).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response post() {
        return  Response.ok("Counter value has been incremented to " + counter.changeCounter(1)).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@Context HttpServletRequest req) {
        int value;
        try {
            value = Integer.parseInt(req.getHeader("Subtraction-Value"));
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok("Counter value has been changed to " + counter.changeCounter(-value)).build();
    }

    @POST
    @Path("/clear")
    @Produces(MediaType.TEXT_PLAIN)
    public Response clearCounter (@Context HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (!Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("hh-auth"))
                .anyMatch(cookie -> cookie.getValue().length() > 10)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.ok("Counter value has been set to zero").build();

    }

}
