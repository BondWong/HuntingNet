package resource;
import helper.SMSUtil;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import utils.ExecutorUtil;

@Path("/mail")
public class MailService {
	private final static String PHONENUMBER = "13750046461";

	@SuppressWarnings("rawtypes")
	@Path("/create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response mail(final Map messageTriple) {
		ExecutorUtil eu = ExecutorUtil.createInstance();

		eu.execute(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				try {
					SMSUtil.send(PHONENUMBER,
							"客户称呼：" + messageTriple.get("name") + "\n客户邮箱："
									+ messageTriple.get("addr") + "\n客户信息:\n"
									+ messageTriple.get("message"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		return Response.ok().build();
	}
}
