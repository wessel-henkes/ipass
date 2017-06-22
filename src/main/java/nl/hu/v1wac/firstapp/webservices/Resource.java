package nl.hu.v1wac.firstapp.webservices;


import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Opstelling;
import nl.hu.v1wac.firstapp.model.Speler;
import nl.hu.v1wac.firstapp.model.Wedstrijd;
import nl.hu.v1wac.firstapp.persistence.OpstellingDAO;
import nl.hu.v1wac.firstapp.persistence.SpelerDAO;
import nl.hu.v1wac.firstapp.persistence.TeamDAO;
import nl.hu.v1wac.firstapp.persistence.UserDAO;
import nl.hu.v1wac.firstapp.persistence.WedstrijdDAO;


@Path("/app")
public class Resource {
	
	@POST
	@Produces("application/json")
	@Path("/team")
	public String getTeam(@FormParam("username") String username,@FormParam("password") String password) {
		System.out.println("username="+username+"&password="+password);
		UserDAO dao = new UserDAO();
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		String teamID = dao.findTeamForUsernameAndPassword(username, password);
		job.add("team_id",teamID);
		return job.build().toString();
	}		
	
	@POST
	@Produces("application/json")
	@Path("/role")
	public String getRole(@FormParam("username") String username,@FormParam("password") String password) {
		System.out.println("username="+username+"&password="+password);
		UserDAO dao = new UserDAO();
		
		JsonObjectBuilder job = Json.createObjectBuilder();
		String role = dao.findRoleForUsernameAndPassword(username, password);
		job.add("role",role);
		return job.build().toString();
	}		
	
	@POST
	@RolesAllowed({"user","admin"})
	@Produces("application/json")
	@Path("/wedstrijden")
	public String getWedstrijden(@FormParam("team_id") int team_id) {
		System.out.println("team_id="+team_id);
		WedstrijdDAO dao = new WedstrijdDAO();
		TeamDAO tdao = new TeamDAO();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Wedstrijd w : dao.getAllWedstrijden(team_id)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			job.add("id", w.getId());
			job.add("competitie_id", w.getCompetitie_id());
			job.add("team_uit_id", w.getTeam_uit());
			
			System.out.println(tdao.getTeamName(w.getTeam_uit())+" team_uit="+w.getTeam_uit());
			job.add("team_uit", 	tdao.getTeamName(w.getTeam_uit()));
			job.add("team_thuis_id", w.getTeam_thuis());
			job.add("team_thuis", 	tdao.getTeamName(w.getTeam_thuis()));
			if(	w.getDatum() ==null)						{job.add("datum","-");}				else{job.add("datum", w.getDatum());}
			if(w.getTijd() ==null)							{job.add("tijd","-");}				else{job.add("tijd", w.getTijd());}
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		System.out.println(array.toString());
		return array.toString();
	}
	
	@POST
	@RolesAllowed({"user","admin"})
	@Produces("application/json")
	@Path("/spelers")
	public String getSpelers(@FormParam("team_id") int team_id) {
		SpelerDAO dao = new SpelerDAO();
		System.out.println(team_id);
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Speler s : dao.getSpelersByTeam(team_id)) {
			System.out.println(s);
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("id", s.getId());
			job.add("naam", s.getNaam());
//			job.add("team_id", s.getTeam_id());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	@POST
	@RolesAllowed("admin")
	@Produces("application/json")
	@Path("/opstelling")
	public String getAanwezigByTeam(@FormParam("wedstrijd_id") int wedstrijd_id, @FormParam("team_id") int team_id) {
		OpstellingDAO dao = new OpstellingDAO();
		SpelerDAO sdao = new SpelerDAO();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Opstelling o : dao.getSlagvolgordeByWedstrijdByTeam(wedstrijd_id, team_id)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("speler_id", o.getSpeler_id());
			job.add("speler_naam", sdao.getSpelerNaam(o.getSpeler_id()));
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@RolesAllowed("admin")
	@Produces("application/json")
	@Path("/opstelling/slagvolgorde")
	public String getSlagvolgordeByTeam(@FormParam("wedstrijd_id") int wedstrijd_id, @FormParam("team_id") int team_id) {
		OpstellingDAO dao = new OpstellingDAO();
		SpelerDAO sdao = new SpelerDAO();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Opstelling o : dao.getSlagvolgordeByWedstrijdByTeam(wedstrijd_id, team_id)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("slagvolgorde", o.getSlagvolgorde());
			job.add("speler_id", o.getSpeler_id());
			job.add("speler_naam", sdao.getSpelerNaam(o.getSpeler_id()));
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@RolesAllowed({"user","admin"})
	@Produces("application/json")
	@Path("/opstelling/veldpositie")
	public String getVeldpositieByTeam(@FormParam("wedstrijd_id") int wedstrijd_id, @FormParam("team_id") int team_id) {
		OpstellingDAO dao = new OpstellingDAO();
		SpelerDAO sdao = new SpelerDAO();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Opstelling o : dao.getVeldpositieByWedstrijdByTeam(wedstrijd_id, team_id)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("slagvolgorde", o.getVeldpositie());
			job.add("speler_id", o.getSpeler_id());
			job.add("speler_naam", sdao.getSpelerNaam(o.getSpeler_id()));
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@PUT
	@RolesAllowed("admin")
	@Produces("application/json")
	@Path("/opstelling/aanwezig")
	public Response createOpstelling(@FormParam("wedstrijd_id") int wedstrijd_id,@FormParam("speler_id_arr[]") List<Integer> speler_id_arr,
	@FormParam("team_id") int team_id) {
		System.out.println("speler_id_arr="+speler_id_arr);
		Response out = Response.status(Response.Status.CONFLICT).build();
		OpstellingDAO dao = new OpstellingDAO();
		if (dao.insertAanwezigen(wedstrijd_id,speler_id_arr,team_id) == true){
			out = Response.ok().build();
		}else{
			out = Response.status(Response.Status.CONFLICT).build();
		};
		
		return out;
	}
	
	@PUT
	@RolesAllowed("admin")
	@Produces("application/json")
	@Path("/opstelling/slagvolgorde")
	public Response setSlagvolgorde(@FormParam("wedstrijd_id") int wedstrijd_id,
		@FormParam("speler_id_arr[]") List<Integer> speler_id_arr,@FormParam("team_id") int team_id) {
			Response out = Response.status(Response.Status.CONFLICT).build();
			System.out.println("setting slagvolgorde");	
			OpstellingDAO dao = new OpstellingDAO();	
			if (dao.setSlagvolgorde(wedstrijd_id,speler_id_arr,team_id) == true){
				out = Response.ok().build();
			}else{
				out = Response.status(Response.Status.CONFLICT).build();
			};
		
			System.out.println("updated");
			return out;
	}
	
	@PUT
	@RolesAllowed("admin")
	@Path("/opstelling/veldpositie")
	@Produces("application/json")
	public Response setVeldpositie(@FormParam("wedstrijd_id") int wedstrijd_id,
			@FormParam("speler_id_arr[]") List<Integer> speler_id_arr,@FormParam("team_id") int team_id) {
				Response out = Response.status(Response.Status.CONFLICT).build();
				System.out.println("setting Veldpositie");	
				OpstellingDAO dao = new OpstellingDAO();	
				if (dao.setSlagvolgorde(wedstrijd_id,speler_id_arr,team_id) == true){
					out = Response.ok().build();
				}else{
					out = Response.status(Response.Status.CONFLICT).build();
				};
			
				System.out.println("updated");
				return out;
		}

	@DELETE
	@RolesAllowed("admin")
	@Path("/opstelling")
	public Response deleteOpstellingByWedstrijd(@FormParam("wedstrijd_id") int wedstrijd_id) {
		System.out.println("delete in progres");
		OpstellingDAO dao = new OpstellingDAO();
		Response out = Response.status(Response.Status.NOT_FOUND).build();
		if (dao.deleteOpstellingByWedstrijd(wedstrijd_id)){
			out = Response.ok().build();
		}
		return out;
	}
	
}
