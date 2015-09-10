package collector;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

@ApplicationPath("/collector")
public class Collector extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

		classes.add(CollectorService.class);
		classes.add(JacksonFeature.class);
		return classes;
	}

	public Set<Object> getSingletons() {
		Set<Object> singletons = new LinkedHashSet<Object>();

		return singletons;
	}

}
