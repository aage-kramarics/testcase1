package test.aage.testcase1.domain;

public class KundeStatus { // Kundedata returnert etter opprettelse av kunde

    private final String kundeNr;
    private final KundeStatusVerdi status;
    private final String masseAnnet;
	public KundeStatus(String kundeNr, KundeStatusVerdi status, String masseAnnet) {
		this.kundeNr = kundeNr;
		this.status = status;
		this.masseAnnet = masseAnnet;
	}
	public String getKundeNr() {
		return kundeNr;
	}
	public KundeStatusVerdi getStatus() {
		return status;
	}
	public String getMasseAnnet() {
		return masseAnnet;
	}
	@Override
	public String toString() {
		return "KundeStatus [kundeNr=" + kundeNr + ", status=" + status + ", masseAnnet=" + masseAnnet + "]";
	}
}
