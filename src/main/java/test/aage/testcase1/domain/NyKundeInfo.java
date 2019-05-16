package test.aage.testcase1.domain;

public class NyKundeInfo {  // Data som trengs for å opprette en ny kunde

    private String fnr; 
    private String navn; 
    private String adresse; 
    private String annetRart;
    
	public String getFnr() {
		return fnr;
	}

	public void setFnr(String fnr) {
		this.fnr = fnr;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getAnnetRart() {
		return annetRart;
	}

	public void setAnnetRart(String annetRart) {
		this.annetRart = annetRart;
	}

	@Override
	public String toString() {
		return "NyKundeInfo [fnr=" + fnr + ", navn=" + navn + ", adresse=" + adresse + ", annetRart=" + annetRart + "]";
	} 
}
