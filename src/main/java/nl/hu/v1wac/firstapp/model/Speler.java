package nl.hu.v1wac.firstapp.model;

public class Speler {
	private int id;
	private String naam;
	private int team_id;
	
	
	public Speler(int id, String naam, int team_id){
		this.id = id;
		this.naam = naam;
		this.team_id = team_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String voornaam) {
		this.naam = voornaam;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	
	@Override
	public String toString() {
		return "Speler [id=" + id + ", naam=" + naam + ",  team_id=" + team_id
				+ "]";
	}
}
