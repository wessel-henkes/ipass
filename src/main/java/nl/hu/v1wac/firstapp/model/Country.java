package nl.hu.v1wac.firstapp.model;

public class Country {
	private String code;
	private String name;
	private String continent;
	private String region; 
	private int surfacearea;
	private int indepyear; 
	private int population; 
	private int lifeexpectancy; 
	private int gnp; 
	private int gnpold; 
	private String localname; 
	private String governmentform; 
	private String headofstate; 
	private String code2; 
	private double latitude; 
	private double longitude; 
	private String capital;
	
	
	public Country(String code,String name,String continent,String region, int surfacearea, int indepyear,int population, int lifeexpectancy, int gnp, int gnpold, String localname,String governmentform,String headofstate,String code2,double lat,double lon, String capital){
		this.code = code;
		this.name=name;
		this.continent = continent;
		this.region=region;
		this.surfacearea= surfacearea;
		this.indepyear=indepyear;
		this.population = population;
		this.lifeexpectancy=lifeexpectancy;
		this.gnp=gnp;
		this.gnpold=gnpold;
		this.localname=localname;
		this.governmentform=governmentform;
		this.headofstate=headofstate;
		this.code2=code2;
		this.latitude=lat;
		this.longitude= lon; 
		this.capital = capital;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getSurfacearea() {
		return surfacearea;
	}
	public void setSurfacearea(int surfacearea) {
		this.surfacearea = surfacearea;
	}
	public int getIndepyear() {
		return indepyear;
	}
	public void setIndepyear(int indepyear) {
		this.indepyear = indepyear;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getLifeexpectancy() {
		return lifeexpectancy;
	}
	public void setLifeexpectancy(int lifeexpectancy) {
		this.lifeexpectancy = lifeexpectancy;
	}
	public int getGnp() {
		return gnp;
	}
	public void setGnp(int gnp) {
		this.gnp = gnp;
	}
	public int getGnpold() {
		return gnpold;
	}
	public void setGnpold(int gnpold) {
		this.gnpold = gnpold;
	}
	public String getLocalname() {
		return localname;
	}
	public void setLocalname(String localname) {
		this.localname = localname;
	}
	public String getGovernmentform() {
		return governmentform;
	}
	public void setGovernmentform(String governmentform) {
		this.governmentform = governmentform;
	}
	public String getHeadofstate() {
		return headofstate;
	}
	public void setHeadofstate(String headofstate) {
		this.headofstate = headofstate;
	}
	public String getCode2() {
		return code2;
	}
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	@Override
	public String toString() {
		return "Country [code:" + code + ", name:" + name + ", continent:" + continent + ", region:" + region
				+ ", surfacearea:" + surfacearea + ", indepyear:" + indepyear + ", population:" + population
				+ ", lifeexpectancy:" + lifeexpectancy + ", gnp:" + gnp + ", gnpold:" + gnpold + ", localname:"
				+ localname + ", governmentform:" + governmentform + ", headofstate:" + headofstate + ", code2:" + code2
				+ ", latitude:" + latitude + ", longitude:" + longitude + ", capital:" + capital + "]";
	}


}
