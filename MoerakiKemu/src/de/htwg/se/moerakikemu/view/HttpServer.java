package de.htwg.se.moerakikemu.view;

import java.util.concurrent.CompletionStage;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.server.PathMatchers;

import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import de.htwg.se.moerakikemu.controller.IController;
import de.htwg.se.moerakikemu.controller.State;
import de.htwg.se.moerakikemu.controller.controllerimpl.ControllerPlayer;
import de.htwg.se.util.observer.ObserverObserver;

import java.util.regex.Pattern;

public class HttpServer extends AllDirectives implements ObserverObserver, UserInterface {
	private ActorSystem system;
	private ActorMaterializer materializer;
	private IController controller;
	private Flow<HttpRequest, HttpResponse, NotUsed> routeFlow;
	private CompletionStage<ServerBinding> binding;
	private ControllerPlayer controllerPl;

	public HttpServer(IController controller, ControllerPlayer controllerPl) {
		this.controller = controller;
		this.controllerPl = controllerPl; 
		controller.attatch(this);
		this.system = ActorSystem.create("my-system");
		this.materializer = ActorMaterializer.create(this.system);
		// this.httpServer = new HttpServer();
		// this.routeFlow = this.createRoute().flow(system, materializer);
		// this.binding = Http.get(this.system).bindAndHandle(routeFlow,
		// ConnectHttp.toHost("localhost", 8080),
		// materializer);
		// this.binding.thenCompose(ServerBinding::unbind).thenAccept(unbound ->
		// system.terminate());
	}

	private Route createRoute() {
		return route(path("new", () -> get(() -> {
			this.controller.newGame();
			return fieldToHtml();
		})), path(PathMatchers.segment("occupy").slash(PathMatchers.segment(Pattern.compile("\\d+"))),
				(value) -> get(() -> {
					System.out.println(value);
					// this.controller.occupy(xCoordinate, yCoordinate);
					return fieldToHtml();
				})));
	}

	private Route fieldToHtml() {
		return complete(controller.fieldToHtml());
	}

	@Override
	public void update() {
		final State controllerState = controller.getState();
		if (controllerState.equals(State.GAME_FINISHED)) {
			System.out.println("TODO");
		}
	}

	@Override
	public void drawCurrentState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void queryPlayerName() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printMessage(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPoints(int pointsPlayer1, int pointsPlayer2) {
		// TODO Auto-generated method stub
		
	}
}
