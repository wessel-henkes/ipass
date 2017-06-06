package nl.hu.v1wac.firstapp.model;

import java.util.ArrayList;
import java.util.List;
import nl.hu.v1wac.firstapp.persistence.CountryDAO;

public class CountryService {
	private CountryDAO dao = new CountryDAO();
	public static  List<Country> countrys = new ArrayList<Country>();

	public List<Country> getAllCountries() {	
		countrys.clear();
		countrys.addAll(dao.findAll());
		return countrys;
	}
	
	public List<Country> get10LargestPopulations() {
		return dao.find10LargestPopulations();
	}

	public List<Country> get10LargestSurfaces() {
		return dao.find10LargestSurfeces();
	}
	
	public Country getCountryByCode(String code) {
		return dao.findCountryByCode(code);
	}
	
	public Country updateCountry(Country country){
		System.out.println("update in progress");
		return dao.update(country);
	}
	
	public Country insertCountry(Country country){
		System.out.println("insert in progress");
		return dao.insert(country);
	}
	
	public boolean saveCountries(){
		System.out.println("save in progress");
		return dao.save();
	}
	
	public boolean deleteCountry(Country country){
		System.out.println("delete in progress");
		return dao.delete(country);
	}
	
}
