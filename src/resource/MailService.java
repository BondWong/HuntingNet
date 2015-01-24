package resource;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mail")
public class MailService {
	
	@SuppressWarnings("rawtypes")
	@Path("/create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response mail(Map messageTriple) {
		return Response.ok().build();
	}
}
