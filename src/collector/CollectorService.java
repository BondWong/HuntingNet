package collector;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/service")
public class CollectorService {

	@Path("/{name: .{1,}}/{place: .{1,}}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@PathParam("name") String name,
			@PathParam("place") String place) {
		EntityManager em = EntityManagerFactoryUtil.getInstance()
				.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Domain domain = new Domain(name, place);
		em.persist(domain);
		em.getTransaction().commit();
		return Response.ok(
				new GenericEntity<Map<String, Object>>(domain.toRep()) {
				}).build();
	}

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchAll() {
		EntityManager em = EntityManagerFactoryUtil.getInstance()
				.getEntityManagerFactory().createEntityManager();
		TypedQuery<Domain> query = em.createQuery(
				"SELECT d FROM Domain d", Domain.class);
		List<Domain> results = query.getResultList();
		List<Map<String, Object>> resultReps = new LinkedList<>();
		for (Domain domain : results)
			resultReps.add(domain.toRep());
		return Response.ok(
				new GenericEntity<List<Map<String, Object>>>(resultReps) {
				}).build();
	}

}
