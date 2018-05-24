package ro.db.vendor.main;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ListenerInfo;
import io.undertow.servlet.api.ServletInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;
import ro.db.vendor.config.web.server.ContextLoaderListenerInstanceFactory;
import ro.db.vendor.config.web.server.DispatcherServletInstanceFactory;

import javax.servlet.ServletException;
import java.util.concurrent.Semaphore;

public class DeploySpringInUndertow {

	public static void main(String[] args) throws ServletException,
			InterruptedException {
		DeploySpringInUndertow deploySpringInUndertow = new DeploySpringInUndertow();
		deploySpringInUndertow.configureUndertow();
		server.start();
		semaphore.acquire();
	}

	private void configureUndertow() throws ServletException {

		DeploymentInfo servletBuilder = Servlets.deployment()
				.setClassLoader(DeploySpringInUndertow.class.getClassLoader())
				.setContextPath(CONTEXT_PATH).setDeploymentName(PKG_NAME)
				.addServlets(createDispatcherServlet())
				.addListener(createContextLoaderListener());

		DeploymentManager manager = Servlets.defaultContainer().addDeployment(
				servletBuilder);
		manager.deploy();
		PathHandler path = Handlers.path(Handlers.redirect(CONTEXT_PATH))
				.addPrefixPath(CONTEXT_PATH, manager.start());

		server = Undertow.builder().addHttpListener(8090, "localhost")
				.setHandler(path).build();

	}

	private ListenerInfo createContextLoaderListener() {
		// contextHandler.addEventListener(new ContextLoaderListener(context));
		return new ListenerInfo(ContextLoaderListener.class,
				new ContextLoaderListenerInstanceFactory());
	}

	private ServletInfo createDispatcherServlet() {

		// contextHandler.addServlet(new ServletHolder(dispatcherServlet),
		// MAPPING_URL);
		return Servlets.servlet("DispatcherServlet", DispatcherServlet.class,
				new DispatcherServletInstanceFactory()).addMapping(MAPPING_URL);
	}

	private static final Semaphore semaphore = new Semaphore(0);

	private static final String MAPPING_URL = "/*";
	private static final String CONTEXT_PATH = "/db-vendor";
	private static final String PKG_NAME = "db-vendor.war";

	private static Undertow server;
	private static final Logger log = LoggerFactory
			.getLogger(DeploySpringInUndertow.class);
}