package test.aage.testcase1.fagsystem;

import test.aage.testcase1.domain.KundeStatus;
import test.aage.testcase1.domain.KundeStatusVerdi;
import test.aage.testcase1.domain.NyKundeInfo;

public class KundeFagSystem { // Mock oppførsel, bruker kundens navn til å styre oppførsel

	public KundeStatus opprettKunde(NyKundeInfo kunde) {

		if ("Nyopprettet".equals(kunde.getNavn())) {
			return new KundeStatus("12345", KundeStatusVerdi.NYOPPRETTET, "dummydata");
		}
		
		if ("Oppdatert".equals(kunde.getNavn())) {
			return new KundeStatus("12345", KundeStatusVerdi.OPPDATERT, "dummydata");
		}
		
		if ("Sperret".equals(kunde.getNavn())) {
			return new KundeStatus("12345", KundeStatusVerdi.SPERRET, "dummydata");
		}
		
		if ("Exception".equals(kunde.getNavn())) {
			throw new RuntimeException("Kall til KundeFagsystem feiler");
		}
		
		return null;
	}
}
