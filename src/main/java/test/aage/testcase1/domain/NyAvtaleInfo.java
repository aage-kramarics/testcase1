package test.aage.testcase1.domain;

public class NyAvtaleInfo {  // Data som trengs for å opprette en ny avtale

    private NyKundeInfo kunde;
    private String avtaleType;
    private String masseAnnet;
    
	public NyKundeInfo getKunde() {
		return kunde;
	}

	public void setKunde(NyKundeInfo kunde) {
		this.kunde = kunde;
	}

	public String getAvtaleType() {
		return avtaleType;
	}

	public void setAvtaleType(String avtaleType) {
		this.avtaleType = avtaleType;
	}

	public String getMasseAnnet() {
		return masseAnnet;
	}

	public void setMasseAnnet(String masseAnnet) {
		this.masseAnnet = masseAnnet;
	}

	@Override
	public String toString() {
		return "NyAvtaleInfo [kunde=" + kunde + ", avtaleType=" + avtaleType + ", masseAnnet=" + masseAnnet + "]";
	}
}
