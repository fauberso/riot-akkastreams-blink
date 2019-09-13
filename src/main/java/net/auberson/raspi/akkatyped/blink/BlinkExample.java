package net.auberson.raspi.akkatyped.blink;

import java.time.Duration;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import riot.GPIO;
import riot.GPIO.State;

/**
 * Example code using Akka Stream that blinks a LED on a Raspberry Pi (connect
 * the LED to GND/Pin 6 and GPIO07/Pin 7, with a resistor): A timer is started
 * that sends a 'Toggle' message every half second. A Raspberry Pi GPIO pin
 * receives this message, and toggles the GPIO.
 */
public class BlinkExample {

	public static void main(String[] args) throws InterruptedException {
		Util.dumpInfo();
		System.out.println();

		ActorSystem system = ActorSystem.create("BlinkExample");
		Materializer mat = ActorMaterializer.create(system);

		// You can easily make Flows, Sinks or Actors out of GPIO Pins:
		Flow<State, State, NotUsed> gpio7 = GPIO.out(7).initiallyLow().asFlow(system);
		ActorRef gpio8 = system.actorOf(GPIO.out(8).initiallyLow().asProps());
		Sink<GPIO.State, NotUsed> gpio9 = GPIO.out(9).initiallyLow().asSink(system);
		// GPIO 8 and 9 are not used, but the underlying actor will be initialized,
		// meaning the GPIO pin will become LOW.

		// Let's set up a timer: Send a GPIOState.TOGGLE object every 500 millis
		Source<GPIO.State, ?> timerSource = Source.tick(Duration.ZERO, Duration.ofMillis(500), GPIO.State.TOGGLE);

		// Define the streams: On each timer tick, toggle the LED on GPIO7.
		timerSource.via(gpio7).runWith(Sink.ignore(), mat);

		System.out.println("Blinking led on " + gpio7);

		// Wait forever
		Thread.currentThread().join();
	}

}
