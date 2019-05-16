package test.aage.testcase1.jetty;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.aage.testcase1.domain.NyAvtaleInfo;
import test.aage.testcase1.domain.NyKundeInfo;
import test.aage.testcase1.jetty.HttpClient.Response;

public class IntegrasjonsTestMain {

	public static void main(String[] args) throws IOException {
		System.out.println("Starter IntegrasjonsTest...");
		new LocalJettyTestcase1();		
		System.out.println("Testcase1 server startet.");
		
		HttpClient testClient = new HttpClient("http://localhost:9090/api/rest/avtale");
		
		System.out.println("Tester OK avtale");
		NyAvtaleInfo nyAvtaleInfo = lagNyAvtale("Nyopprettet", "Nyopprettet");
		Response response = testClient.putJson(lagJson(nyAvtaleInfo));
		System.out.println("     " +response);

		
		System.out.println("Tester OK avtale med eksisterende kunde");
		nyAvtaleInfo = lagNyAvtale("Oppdatert", "Nyopprettet");
		response = testClient.putJson(lagJson(nyAvtaleInfo));
		System.out.println("     " +response);
		
		System.out.println("Tester sperret kunde");
		nyAvtaleInfo = lagNyAvtale("Sperret", "Nyopprettet");
		response = testClient.putJson(lagJson(nyAvtaleInfo));
		System.out.println("     " +response);
		
		System.out.println("Tester exception fra KundeFagsystem");
		nyAvtaleInfo = lagNyAvtale("Exception", "Nyopprettet");
		response = testClient.putJson(lagJson(nyAvtaleInfo));
		System.out.println("     " +response);

		
		System.out.println("Tester avtale som feiler ved oppdatering");
		nyAvtaleInfo = lagNyAvtale("Nyopprettet", "FeilerVedOppdatering");
		response = testClient.putJson(lagJson(nyAvtaleInfo));
		System.out.println("     " +response);
		
		System.out.println("Tester avtale som feiler ved sending av brev");
		nyAvtaleInfo = lagNyAvtale("Nyopprettet", "FeilerVedBrevsending");
		response = testClient.putJson(lagJson(nyAvtaleInfo));
		System.out.println("     " +response);
		
		System.out.println("Tester exception fra AvtaleFagsystem");
		nyAvtaleInfo = lagNyAvtale("Nyopprettet", "Exception");
		response = testClient.putJson(lagJson(nyAvtaleInfo));
		System.out.println("     " +response);
	}

	private static String lagJson(NyAvtaleInfo nyAvtaleInfo) throws JsonProcessingException {
		ObjectMapper m = new ObjectMapper();
		return m.writeValueAsString(nyAvtaleInfo);
	}

	private static NyAvtaleInfo lagNyAvtale(String kundeNavn, String avtaleType) {
		NyKundeInfo nyKundeInfo = new NyKundeInfo();
		nyKundeInfo.setFnr("fnr");
		nyKundeInfo.setNavn(kundeNavn);
		nyKundeInfo.setAdresse("adresse");
		nyKundeInfo.setAnnetRart("annetRart");
		NyAvtaleInfo nyAvtaleInfo = new NyAvtaleInfo();
		nyAvtaleInfo.setAvtaleType(avtaleType);
		nyAvtaleInfo.setKunde(nyKundeInfo);
		nyAvtaleInfo.setMasseAnnet("masseAnnet");
		return nyAvtaleInfo;
	}
}
