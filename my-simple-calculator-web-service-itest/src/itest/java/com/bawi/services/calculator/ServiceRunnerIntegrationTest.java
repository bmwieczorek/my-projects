package com.bawi.services.calculator;

import org.junit.Assert;
import org.junit.Test;

import com.bawi.itests.connection.UrlConnectionChecker;
import com.bawi.services.calculator.runner.ServiceRunner;

public class ServiceRunnerIntegrationTest {

	private UrlConnectionChecker connectionChecker = UrlConnectionChecker
			.createWithTimeOut(5);

	@Test
	public void shouldStartServiceRunner() throws Exception {
		Thread serviceRunner = createServiceRunnerThread();
		serviceRunner.start();

		String url = "http://localhost:7890/calculator?wsdl";
		boolean isServiceUp = connectionChecker.isUp(url);

		Assert.assertTrue(isServiceUp);

		// Thread jetty = createMavenJettyThread();
		// jetty.start();
	}

	private Thread createServiceRunnerThread() {
		Thread serviceRunner = new Thread(new Runnable() {
			@Override
			public void run() {
				ServiceRunner.main(null);
			}

		});
		return serviceRunner;
	}

	// private Thread createMavenJettyThread() {
	// Thread serviceRunner = new Thread(new Runnable() {
	// @Override
	// public void run() {
	// try {
	// startMavenJettyRun();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// });
	// return serviceRunner;
	// }

	// private void startMavenJettyRun() throws IOException {
	// String s = null;
	// Process mavenProcess = Runtime.getRuntime().exec("mvn cargo:start");
	// BufferedReader stdIn = new BufferedReader(new InputStreamReader(
	// mavenProcess.getInputStream()));
	// BufferedReader stdOut = new BufferedReader(new InputStreamReader(
	// mavenProcess.getErrorStream()));
	// while ((s = stdIn.readLine()) != null) {
	// System.out.println(s);
	// }
	//
	// while ((s = stdOut.readLine()) != null) {
	// System.err.println(s);
	// }
	// }
}
