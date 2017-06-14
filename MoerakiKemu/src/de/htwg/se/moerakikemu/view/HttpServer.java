package de.htwg.se.moerakikemu.view;

import java.util.concurrent.CompletionStage;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.ContentTypes;
import akka.http.javadsl.model.HttpEntities;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.State;
import de.htwg.se.util.observer.ObserverObserver;

public class HttpServer extends AllDirectives implements ObserverObserver {
	private IController controller;
	private Object runner = new Object();
	private static final Logger LOGGER = (Logger) LogManager.getLogger(HttpServer.class);

	public HttpServer(IController controller) {
		this.controller = controller;
		controller.attatch(this);

		ActorSystem system = ActorSystem.create();
		ActorMaterializer materializer = ActorMaterializer.create(system);

		Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = this.createRoute().flow(system, materializer);
		CompletionStage<ServerBinding> binding = Http.get(system).bindAndHandle(routeFlow,
				ConnectHttp.toHost("localhost", 8080), materializer);

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (runner) {
						runner.wait();
					}
				} catch (InterruptedException e) {
					LOGGER.error(e.getMessage());
				}
				binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
			}
		});
		t.start();

	}

	private Route createRoute() {
		int edgeLength = controller.getEdgeLength();
		int coordlength = (int) Math.ceil(Math.log10(edgeLength));
		return route(path("", () -> get(() -> {
			return complete(HttpEntities.create(ContentTypes.TEXT_HTML_UTF8, "<h1>Welcome to HTWG Moeraki Kemu</h1>"));
		})),

				path("new", () -> get(() -> {
					controller.newGame();
					return fieldToHtml();
				})), path("show", () -> get(() -> {
					return fieldToHtml();
				})),

				path(PathMatchers.segment("occupy").slash(
						PathMatchers.segment(Pattern.compile("\\d{" + 2 * coordlength + "}"))), (value) -> get(() -> {
							int xCoordinate = (int) (Integer.valueOf(value) / Math.pow(10, coordlength));
							int yCoordinate = Integer.valueOf(value) % (int) Math.pow(10, coordlength);
							System.out.println(xCoordinate);
							System.out.println(yCoordinate);
							if (xCoordinate <= edgeLength && yCoordinate <= edgeLength)
								controller.occupy(xCoordinate - 1, yCoordinate - 1);
							return fieldToHtml();
						})));
	}

	private Route fieldToHtml() {
		return complete(HttpEntities.create(ContentTypes.TEXT_HTML_UTF8,
				"<h1>HTWG Moeraki Kemu</h1>" + controller.fieldToHtml()));
	}

	@Override
	public void update() {
		final State controllerState = controller.getState();
		if (controllerState.equals(State.GAME_FINISHED)) {
			synchronized (runner) {
				runner.notify();
			}
		}
	}

}
