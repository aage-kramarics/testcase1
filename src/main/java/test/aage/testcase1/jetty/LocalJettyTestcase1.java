package test.aage.testcase1.jetty;

import javax.naming.NamingException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class LocalJettyTestcase1 {
	
	private static final int PORT_WHEN_STARTED_STANDALONE = 9090; // Brukes når main() i denne klassen kalles

    public LocalJettyTestcase1() {
    	int port = PORT_WHEN_STARTED_STANDALONE;
		System.out.println("Starting LocalJettyTestcase1 port: "+port+"...");
		try {
			Server server = setupServer(port);
	        start(server, false);
			System.out.println("LocalJettyTestcase1 started");
		} catch (Exception e) {
			System.out.println("Exception when running LocalJettyTestcase1: " + e);
		}
    }
    
    public static void main( String[] args ) throws Exception {
    	new LocalJettyTestcase1();
    }

	private void start(Server server, boolean rejoin) throws Exception, InterruptedException {
		server.start();
        if (rejoin) {
        	server.join();
        }
	}

	private Server setupServer(int port) throws NamingException {
		
		Server server = new Server(port);
        server.setHandler(setupWebappContext());
		return server;
	}

	private WebAppContext setupWebappContext() throws NamingException { 
		WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setResourceBase("src/main/webapp");
        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", "^$");
        webapp.setAttribute("org.eclipse.jetty.server.webapp.WebInfIncludeJarPattern", "^$");        
		return webapp;
	}
}