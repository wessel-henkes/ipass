package nl.hu.v1wac.firstapp.model;

public class ServiceProvider {
	private static CountryService worldService = new CountryService();

	public static CountryService getWorldService() {
		return worldService;
	}
}