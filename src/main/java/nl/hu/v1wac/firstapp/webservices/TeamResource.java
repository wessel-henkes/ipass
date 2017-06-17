package nl.hu.v1wac.firstapp.webservices;


import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.v1wac.firstapp.persistence.UserDAO;


@Path("/app")
public class TeamResource {
	
	@POST
//	@RolesAllowed("user")
	@Produces("application/json")
	@Path("/team")
	public String getTeam(@FormParam("username") String username,@FormParam("password") String password) {
		System.out.println("username="+username+"&password="+password);
		UserDAO dao = new UserDAO();
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		String teamID = dao.findTeamForUsernameAndPassword(username, password);
		job.add("teamID",teamID);
		return job.build().toString();
	}		
}
