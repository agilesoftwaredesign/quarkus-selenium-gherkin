package de.agilesoftwaredesign.qsg;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

@Path("/forms")
@Produces(MediaType.APPLICATION_JSON)
public class FormResource {
    private static final Logger LOG = Logger.getLogger(FormResource.class.getName());

    @GET
    @Operation(summary = "xxx")
    public Response simpleGet(@QueryParam("one") String one, @QueryParam("two") String two) {
        LOG.info("called: simpleGet");
        Map<String, String> a = new HashMap<>();
        a.put("hi", "welcome");
        a.put("one", one == null ? "no-one" : one);
        a.put("two", two == null ? "no-two" : two);
        return Response.ok().entity(a).build();
    }

}
