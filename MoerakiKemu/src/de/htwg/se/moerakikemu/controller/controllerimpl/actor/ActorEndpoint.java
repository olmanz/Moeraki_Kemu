package de.htwg.se.moerakikemu.controller.controllerimpl.actor;

import java.awt.Point;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.htwg.se.moerakikemu.controller.controllerimpl.actor.msg.CheckSquareRequest;
import de.htwg.se.moerakikemu.controller.controllerimpl.actor.msg.CheckSquareResponse;
import de.htwg.se.moerakikemu.modellayer.IField;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class ActorEndpoint {
	private ActorRef squareActor;
	private int fieldLength;
	private IField field;

	public ActorEndpoint(IField gameField, int fieldLength) {
		this.field = gameField;
		this.fieldLength = fieldLength;
		ActorSystem sys = ActorSystem.create();
		squareActor = sys.actorOf(Props.create(SquareActor.class));
	}

	public int[] getPoints(int x, int y) {
		CheckSquareResponse response = (CheckSquareResponse) askSquareActor(
				new CheckSquareRequest(new Point(x, y), field, fieldLength));
		return response.getResult();
	}

	public Object askSquareActor(Object msgObject) {
		final Timeout timeout = new Timeout(5, TimeUnit.SECONDS);
		final Future<Object> future = Patterns.ask(squareActor, msgObject, timeout);
		try {
			final Object result = Await.result(future, timeout.duration());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
