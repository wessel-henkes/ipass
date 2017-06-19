package nl.hu.v1wac.firstapp.model;

public class Wedstrijd {
	private int id;
	private int competitie_id;
	private int team_uit;
	private int team_thuis;
	private String datum;
	private String tijd;
	
	public Wedstrijd(int id, int competitie_id, int team_uit, int team_thuis, String datum, String tijd){
		this.id = id;
		this.competitie_id = competitie_id;
		this.team_uit = team_uit;
		this.team_thuis = team_thuis;
		this.datum = datum;
		this.tijd = tijd;
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
	public int getTeam_uit() {
		return team_uit;
	}
	public void setTeam_uit(int team_uit) {
		this.team_uit = team_uit;
	}
	public int getTeam_thuis() {
		return team_thuis;
	}
	public void setTeam_thuis(int team_thuis) {
		this.team_thuis = team_thuis;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getTijd() {
		return tijd;
	}
	public void setTijd(String tijd) {
		this.tijd = tijd;
	}
	
	@Override
	public String toString() {
		return "Wedstrijd [id=" + id + ", competitie_id=" + competitie_id + ", team_uit=" + team_uit + ", team_thuis="
				+ team_thuis + ", datum=" + datum + ", tijd=" + tijd + "]";
	}
}
