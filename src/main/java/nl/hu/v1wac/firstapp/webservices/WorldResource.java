package nl.hu.v1wac.firstapp.webservices;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.ServiceProvider;
import nl.hu.v1wac.firstapp.model.CountryService;

@Path("/countries")
public class WorldResource {
	@GET
	@Produces("application/json")
	public String getCountries() {
		CountryService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Country c : service.getAllCountries()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			if (c.getCode() == null)						{job.add("code","-");}else{job.add("code", c.getCode());}
			if(c.getName() ==null)							{job.add("name","-");}else{job.add("name", c.getName());}
			if(c.getContinent() ==null)						{job.add("continent","-");}else{job.add("continent", c.getContinent());}
			if(c.getRegion() ==null)						{job.add("region","-");}else{job.add("region", c.getRegion());}
			job.add("surfacearea", c.getSurfacearea());
			job.add("population", c.getPopulation());
			if(new Integer(c.getIndepyear()) ==null)		{job.add("indepyear","-");}else{job.add("indepyear", c.getIndepyear());}
			if(new Integer(c.getLifeexpectancy()) ==null)	{job.add("lifeexpectancy","-");}else{job.add("lifeexpectancy", c.getLifeexpectancy());}
			if(new Integer(c.getGnp()) ==null)				{job.add("gnp","-");}else{job.add("gnp", c.getGnp());}
			if(new Integer(c.getGnpold()) == null)			{job.add("gnpold","-");}else{job.add("gnpold", c.getGnpold());	}
			if(c.getLocalname() ==null)						{job.add("localname","-");}else{job.add("localname", c.getLocalname());			}
			if(c.getGovernmentform() ==null)				{job.add("governmentform","-");}else{job.add("governmentform", c.getGovernmentform());}
			if(c.getHeadofstate() ==null)					{job.add("headofstate","-");}else{job.add("headofstate", c.getHeadofstate());}
			if(c.getCode2() ==null)							{job.add("code2","-");}else{job.add("code2", c.getCode2());}
			if(new Double(c.getLatitude()) ==null)			{job.add("latitude","-");}else{job.add("latitude", c.getLatitude());}
			if(new Double(c.getLongitude())==null)			{job.add("longitude","-");}else{job.add("longitude", c.getLongitude());}
			if(c.getCapital() ==null)						{job.add("capital","-");}else{job.add("capital", c.getCapital());}
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getSurface() {
		
		CountryService service = ServiceProvider.getWorldService();
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Country c : service.get10LargestSurfaces()) {
			JsonObjectBuilder job = Json.createObjectBuilder();	
			if (c.getCode() == null)						{job.add("code","-");}else{job.add("code", c.getCode());}
			if(c.getName() ==null)							{job.add("name","-");}else{job.add("name", c.getName());}
			if(c.getContinent() ==null)						{job.add("continent","-");}else{job.add("continent", c.getContinent());}
			if(c.getRegion() ==null)						{job.add("region","-");}else{job.add("region", c.getRegion());}
			job.add("surfacearea", c.getSurfacearea());
			job.add("population", c.getPopulation());
			if(new Integer(c.getIndepyear()) ==null)		{job.add("indepyear","-");}else{job.add("indepyear", c.getIndepyear());}
			if(new Integer(c.getLifeexpectancy()) ==null)	{job.add("lifeexpectancy","-");}else{job.add("lifeexpectancy", c.getLifeexpectancy());}
			if(new Integer(c.getGnp()) ==null)				{job.add("gnp","-");}else{job.add("gnp", c.getGnp());}
			if(new Integer(c.getGnpold()) == null)			{job.add("gnpold","-");}else{job.add("gnpold", c.getGnpold());	}
			if(c.getLocalname() ==null)						{job.add("localname","-");}else{job.add("localname", c.getLocalname());			}
			if(c.getGovernmentform() ==null)				{job.add("governmentform","-");}else{job.add("governmentform", c.getGovernmentform());}
			if(c.getHeadofstate() ==null)					{job.add("headofstate","-");}else{job.add("headofstate", c.getHeadofstate());}
			if(c.getCode2() ==null)							{job.add("code2","-");}else{job.add("code2", c.getCode2());}
			if(new Double(c.getLatitude()) ==null)			{job.add("latitude","-");}else{job.add("latitude", c.getLatitude());}
			if(new Double(c.getLongitude())==null)			{job.add("longitude","-");}else{job.add("longitude", c.getLongitude());}
			if(c.getCapital() ==null)						{job.add("capital","-");}else{job.add("capital", c.getCapital());}

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String getPopulation() {
		
		CountryService service = ServiceProvider.getWorldService();
		
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Country c : service.get10LargestPopulations()) {
			JsonObjectBuilder job = Json.createObjectBuilder();	
			if (c.getCode() == null)						{job.add("code","-");}else{job.add("code", c.getCode());}
			if(c.getName() ==null)							{job.add("name","-");}else{job.add("name", c.getName());}
			if(c.getContinent() ==null)						{job.add("continent","-");}else{job.add("continent", c.getContinent());}
			if(c.getRegion() ==null)						{job.add("region","-");}else{job.add("region", c.getRegion());}
			job.add("surfacearea", c.getSurfacearea());
			job.add("population", c.getPopulation());
			if(new Integer(c.getIndepyear()) ==null)		{job.add("indepyear","-");}else{job.add("indepyear", c.getIndepyear());}
			if(new Integer(c.getLifeexpectancy()) ==null)	{job.add("lifeexpectancy","-");}else{job.add("lifeexpectancy", c.getLifeexpectancy());}
			if(new Integer(c.getGnp()) ==null)				{job.add("gnp","-");}else{job.add("gnp", c.getGnp());}
			if(new Integer(c.getGnpold()) == null)			{job.add("gnpold","-");}else{job.add("gnpold", c.getGnpold());	}
			if(c.getLocalname() ==null)						{job.add("localname","-");}else{job.add("localname", c.getLocalname());			}
			if(c.getGovernmentform() ==null)				{job.add("governmentform","-");}else{job.add("governmentform", c.getGovernmentform());}
			if(c.getHeadofstate() ==null)					{job.add("headofstate","-");}else{job.add("headofstate", c.getHeadofstate());}
			if(c.getCode2() ==null)							{job.add("code2","-");}else{job.add("code2", c.getCode2());}
			if(new Double(c.getLatitude()) ==null)			{job.add("latitude","-");}else{job.add("latitude", c.getLatitude());}
			if(new Double(c.getLongitude())==null)			{job.add("longitude","-");}else{job.add("longitude", c.getLongitude());}
			if(c.getCapital() ==null)						{job.add("capital","-");}else{job.add("capital", c.getCapital());}

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getCountrie(@PathParam("id") String id) {
		
		CountryService service = ServiceProvider.getWorldService();
		JsonObjectBuilder job = Json.createObjectBuilder();
		Country c = service.getCountryByCode(id);
		
		if (c != null){
			if (c.getCode() == null)						{job.add("code","-");}else{job.add("code", c.getCode());}
			if(c.getName() ==null)							{job.add("name","-");}else{job.add("name", c.getName());}
			if(c.getContinent() ==null)						{job.add("continent","-");}else{job.add("continent", c.getContinent());}
			if(c.getRegion() ==null)						{job.add("region","-");}else{job.add("region", c.getRegion());}
			job.add("surfacearea", c.getSurfacearea());
			job.add("population", c.getPopulation());
			if(new Integer(c.getIndepyear()) ==null)		{job.add("indepyear","-");}else{job.add("indepyear", c.getIndepyear());}
			if(new Integer(c.getLifeexpectancy()) ==null)	{job.add("lifeexpectancy","-");}else{job.add("lifeexpectancy", c.getLifeexpectancy());}
			if(new Integer(c.getGnp()) ==null)				{job.add("gnp","-");}else{job.add("gnp", c.getGnp());}
			if(new Integer(c.getGnpold()) == null)			{job.add("gnpold","-");}else{job.add("gnpold", c.getGnpold());	}
			if(c.getLocalname() ==null)						{job.add("localname","-");}else{job.add("localname", c.getLocalname());			}
			if(c.getGovernmentform() ==null)				{job.add("governmentform","-");}else{job.add("governmentform", c.getGovernmentform());}
			if(c.getHeadofstate() ==null)					{job.add("headofstate","-");}else{job.add("headofstate", c.getHeadofstate());}
			if(c.getCode2() ==null)							{job.add("code2","-");}else{job.add("code2", c.getCode2());}
			if(new Double(c.getLatitude()) ==null)			{job.add("latitude","-");}else{job.add("latitude", c.getLatitude());}
			if(new Double(c.getLongitude())==null)			{job.add("longitude","-");}else{job.add("longitude", c.getLongitude());}
			if(c.getCapital() ==null)						{job.add("capital","-");}else{job.add("capital", c.getCapital());}

		}else{
			throw new WebApplicationException("No such Country code!");

		}
		return job.build().toString();
	}
	
	@POST
	@RolesAllowed("user")
	@Produces("application/json")
	public String createCountry(@FormParam("code") String code,@FormParam("name") String name,
		@FormParam("continent") String continent,@FormParam("region") String region,@FormParam("surfacearea") int surfacearea,
		@FormParam("indepyear") int indepyear,@FormParam("population") int population,
		@FormParam("lifeexpectancy") int lifeexpectancy,@FormParam("gnp") int gnp,@FormParam("gnpold") int gnpold,
		@FormParam("localname") String localname,@FormParam("governmentform") String governmentform,
		@FormParam("headofstate") String headofstate,@FormParam("code2") String code2,@FormParam("latitude") double lat,
		@FormParam("longitude") double lon,@FormParam("capital") String capital) {
		System.out.println("er is iets");
		CountryService service = ServiceProvider.getWorldService();
		Country newCountry = new Country(code,name,continent,region,surfacearea,indepyear,population,lifeexpectancy,gnp,gnpold,localname,governmentform,headofstate,code2,lat,lon,capital);
		service.insertCountry(newCountry);
		CountryService.countrys.add(newCountry);
		System.out.println(newCountry.toString());
		return CountryToJson(newCountry).build().toString();
	}

	
	private JsonObjectBuilder CountryToJson(Country c) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		if (c.getCode() == null)						{job.add("code","-");}else{job.add("code", c.getCode());}
		if(c.getName() ==null)							{job.add("name","-");}else{job.add("name", c.getName());}
		if(c.getContinent() ==null)						{job.add("continent","-");}else{job.add("continent", c.getContinent());}
		if(c.getRegion() ==null)						{job.add("region","-");}else{job.add("region", c.getRegion());}
		job.add("surfacearea", c.getSurfacearea());
		job.add("population", c.getPopulation());
		if(new Integer(c.getIndepyear()) ==null)		{job.add("indepyear","-");}else{job.add("indepyear", c.getIndepyear());}
		if(new Integer(c.getLifeexpectancy()) ==null)	{job.add("lifeexpectancy","-");}else{job.add("lifeexpectancy", c.getLifeexpectancy());}
		if(new Integer(c.getGnp()) ==null)				{job.add("gnp","-");}else{job.add("gnp", c.getGnp());}
		if(new Integer(c.getGnpold()) == null)			{job.add("gnpold","-");}else{job.add("gnpold", c.getGnpold());	}
		if(c.getLocalname() ==null)						{job.add("localname","-");}else{job.add("localname", c.getLocalname());			}
		if(c.getGovernmentform() ==null)				{job.add("governmentform","-");}else{job.add("governmentform", c.getGovernmentform());}
		if(c.getHeadofstate() ==null)					{job.add("headofstate","-");}else{job.add("headofstate", c.getHeadofstate());}
		if(c.getCode2() ==null)							{job.add("code2","-");}else{job.add("code2", c.getCode2());}
		if(new Double(c.getLatitude()) ==null)			{job.add("latitude","-");}else{job.add("latitude", c.getLatitude());}
		if(new Double(c.getLongitude())==null)			{job.add("longitude","-");}else{job.add("longitude", c.getLongitude());}
		if(c.getCapital() ==null)						{job.add("capital","-");}else{job.add("capital", c.getCapital());}

		return job;
	}
	
	
	@DELETE
	@RolesAllowed("user")
	@Path("{code}")
	public Response deleteCountry(@PathParam("code") String code) {
		System.out.println("delete in progres");
		Country found = null;
		CountryService service = ServiceProvider.getWorldService();
		found = service.getCountryByCode(code);
		System.out.println(found);
		Response out = Response.status(Response.Status.NOT_FOUND).build();
		if (found == null) {
			out = Response.status(Response.Status.NOT_FOUND).build();
		} else if (service.deleteCountry(found)){
			CountryService.countrys.remove(found);
			//System.out.println(service.getCountryByCode(code));
			out = Response.ok().build();
		} else{
			out = Response.status(Response.Status.NOT_FOUND).build();
		}
		return out;
	}
	
	@PUT
	@RolesAllowed("user")
	@Path("{code3}")
	@Produces("application/json")
	public String updateCountry(
		@PathParam("code3") String code,
		@FormParam("name") String name2,
		@FormParam("continent") String continent,
		@FormParam("region") String region,
		@FormParam("surfacearea") int surfacearea,
		@FormParam("indepyear") int indepyear,
		@FormParam("population") int population,
		@FormParam("lifeexpectancy") int lifeexpectancy,
		@FormParam("gnp") int gnp,
		@FormParam("gnpold") int gnpold,
		@FormParam("localname") String localname,
		@FormParam("governmentform") String governmentform,
		@FormParam("headofstate") String headofstate,
		@FormParam("code2") String code2,
		@FormParam("latitude") double lat,
		@FormParam("longitude") double lon,
		@FormParam("capital") String capital) {
		
			System.out.println("updating"+ indepyear);	
			System.out.println("\n formparams  "+ code+" , "+name2+" , "+continent+" , "+region+" , "+surfacearea+" , "+
			indepyear+" , "+population+" , "+lifeexpectancy+" , "+gnp+" , "+gnpold+" , "+localname+" , "+governmentform+" , "+
			headofstate+" , "+code2+" , "+lat+" , "+lon+" , "+capital);
			
			CountryService service = ServiceProvider.getWorldService();
			
			Country found = null;
			found = service.getCountryByCode(code);
			System.out.println("found = "+found);
			
			found.setName(name2);
			found.setContinent(continent);
			found.setRegion(region);
			found.setSurfacearea(surfacearea);
			found.setIndepyear(indepyear);
			found.setPopulation(population);
			found.setLifeexpectancy(lifeexpectancy);
			found.setGnp(gnp);
			found.setGnpold(gnpold);
			found.setLocalname(localname);
			found.setGovernmentform(governmentform);
			found.setHeadofstate(headofstate);
			found.setCode2(code2);
			found.setLatitude(lat);
			found.setLongitude(lon); 
			found.setCapital(capital);
			
			System.out.println("new found = "+found);
			
			service.updateCountry(found);
			
			System.out.println("updated");
			
			return CountryToJson(found).build().toString();
		}
	
}