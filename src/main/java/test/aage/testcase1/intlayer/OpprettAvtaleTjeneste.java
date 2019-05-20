package test.aage.testcase1.intlayer;

import org.springframework.beans.factory.annotation.Autowired;

import test.aage.testcase1.domain.AvtaleStatus;
import test.aage.testcase1.domain.KundeStatus;
import test.aage.testcase1.domain.NyAvtaleInfo;
import test.aage.testcase1.domain.NyKundeInfo;
import test.aage.testcase1.fagsystem.AvtaleFagSystem;
import test.aage.testcase1.fagsystem.BrevSystem;
import test.aage.testcase1.fagsystem.KundeFagSystem;
import test.aage.testcase1.domain.AvtaleStatusVerdi;

public class OpprettAvtaleTjeneste {

	@Autowired
	private KundeFagSystem kundeFagSystem;
	
	@Autowired
	private AvtaleFagSystem avtaleFagSystem;
	
	@Autowired
	private BrevSystem brevSystem;
	
	/*
	 * For å bli brukbar må denne tjenesten håndtere følgende i hvert tjenestekall:
	 * 
	 * - Konvertere input-objekter til fagsystemets input-objekter før fagsystemet kalles,
	 *   og konvertere fagsystemets retur-objekt før retur.
	 *   
	 * - Håndtere avvik fra normaloppførsel i fagsystemet og gi ønsket retur for disse.
	 *   Dersom fagsystem-kallet ikke er et "trygt", synkront kall, må dette pakkes inn og man må håndtere f.eks. timeout.
	 * 
	 * - Dersom fagsystemet har begrenset kapasitet, kan man måtte strupe trafikken og f.eks. bruke en thread pool
	 * 
	 * Til de to siste punktene kan man f.eks. anvende Hystrix.
	 */
	public KundeStatus opprettKunde(NyKundeInfo kunde) {
		return kundeFagSystem.opprettKunde(kunde);
	}

	public AvtaleStatus opprettAvtale(NyAvtaleInfo avtale) {
		return avtaleFagSystem.opprettAvtale(avtale);
	}
	
	public void sendAvtale(NyAvtaleInfo avtale, AvtaleStatus avtaleStatus) {
		brevSystem.sendAvtale(avtale, avtaleStatus);
	}
	
	public void oppdaterAvtaleStatus(String avtaleNr, AvtaleStatusVerdi avtaleStatusVerdi) {
		avtaleFagSystem.oppdaterAvtaleStatus(avtaleNr, avtaleStatusVerdi);
	}
}
