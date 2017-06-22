package nl.hu.v1wac.firstapp.model;

public class Team {
	private String naam;
	private int id;
	private int competitie_id;
	
	public Team(String naam, int id, int competitie_id){
		this.naam = naam;
		this.id = id;
		this.competitie_id = competitie_id;
	}
	
	public Team(String naam){
		this.naam = naam;
	}

	public Team() {}

	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompetitie_id() {
		return competitie_id;
	}
	public void setCompetitie_id(int competitie_id) {
		this.competitie_id = competitie_id;
	}
	
	@Override
	public String toString() {
		return "Team [naam=" + naam + ", id=" + id + ", competitie_id=" + competitie_id + "]";
	}
	
}
