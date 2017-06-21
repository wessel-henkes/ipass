package nl.hu.v1wac.firstapp.model;

public class Opstelling {
	private String veldpositie;
	private int slagvolgorde;
	private int speler_id;
	private int team_id;
	private int wedstrijd_id;
	private int id;
	
	public Opstelling(String veldpositie, int slagvolgorde, int speler_id, int team_id, int wedstrijd_id, int id){
		this.veldpositie = veldpositie;
		this.slagvolgorde = slagvolgorde;
		this.speler_id = speler_id;
		this.team_id = team_id;
		this.wedstrijd_id = wedstrijd_id;
		this.id = id;
	}
	
	public Opstelling(String veldpositie, int slagvolgorde, int speler_id, int team_id, int wedstrijd_id){
		this.veldpositie = veldpositie;
		this.slagvolgorde = slagvolgorde;
		this.speler_id = speler_id;
		this.team_id = team_id;
		this.wedstrijd_id = wedstrijd_id;
	}
	
	public Opstelling(int speler_id, int team_id, int wedstrijd_id){
		this.speler_id = speler_id;
		this.team_id = team_id;
		this.wedstrijd_id = wedstrijd_id;
	}
	public Opstelling(int slagvolgorde, int speler_id, int team_id, int wedstrijd_id ){ //getSlagvolgordeByWedstrijdByTeam, setSlagvolgorde()
		this.slagvolgorde = slagvolgorde;
		this.speler_id = speler_id;
		this.team_id = team_id;
		this.wedstrijd_id = wedstrijd_id;
	}

	public Opstelling(String veldpositie, int speler_id, int team_id, int wedstrijd_id ){ //setVeldpositie
		this.veldpositie = veldpositie;
		this.speler_id = speler_id;
		this.team_id = team_id;
		this.wedstrijd_id = wedstrijd_id;
	}

	public String getVeldpositie() {
		return veldpositie;
	}
	public void setVeldpositie(String veldpositie) {
		this.veldpositie = veldpositie;
	}
	public int getSlagvolgorde() {
		return slagvolgorde;
	}
	public void setSlagvolgorde(int slagvolgorde) {
		this.slagvolgorde = slagvolgorde;
	}
	public int getSpeler_id() {
		return speler_id;
	}
	public void setSpeler_id(int speler_id) {
		this.speler_id = speler_id;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getWedstrijd_id() {
		return wedstrijd_id;
	}
	public void setWedstrijd_id(int wedstrijd_id) {
		this.wedstrijd_id = wedstrijd_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Opstelling [veldpositie=" + veldpositie + ", slagvolgorde=" + slagvolgorde + ", speler_id=" + speler_id
				+ ", team_id=" + team_id + ", wedstrijd_id=" + wedstrijd_id + ", id=" + id + "]";
	}
}
