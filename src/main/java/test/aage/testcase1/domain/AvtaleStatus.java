package test.aage.testcase1.domain;

public class AvtaleStatus { // Avtaledata returnert etter opprettelse av avtale

	private final String avtaleNr;
    private KundeStatus kunde;
    private AvtaleStatusVerdi status;
    private final String masseAnnet;
    
	public AvtaleStatus(String avtaleNr, KundeStatus kunde, AvtaleStatusVerdi status, String masseAnnet) {
		this.avtaleNr = avtaleNr;
		this.kunde = kunde;
		this.status = status;
		this.masseAnnet = masseAnnet;
	}
	public String getAvtaleNr() {
		return avtaleNr;
	}
	public KundeStatus getKunde() {
		return kunde;
	}
	public AvtaleStatusVerdi getStatus() {
		return status;
	}
	public void setStatus(AvtaleStatusVerdi avtaleStatusVerdi) {
		this.status = avtaleStatusVerdi;
	}
	public void setKunde(KundeStatus kunde) {
		this.kunde = kunde;
	}
	public String getMasseAnnet() {
		return masseAnnet;
	}
	@Override
	public String toString() {
		return "AvtaleStatus [avtaleNr=" + avtaleNr + ", kunde=" + kunde + ", status=" + status + ", masseAnnet="
				+ masseAnnet + "]";
	}
}
