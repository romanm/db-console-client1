package org.designpattern.observer1;

import java.util.Observer;


/**
 * @author roman
 * @see http://en.wikipedia.org/wiki/Observer_pattern
 */
public class MyApp {
	public static void main(String[] args) {
		System.out.println("Enter Text >");
		// create an event source - reads from stdin
		final EventSource eventSource = new EventSource();
		// create an observer
		final Observer responseHandler = new ResponseHandler();
		// subscribe the observer to the event source
		eventSource.addObserver(responseHandler);
		// starts the event thread
//		Thread thread = new Thread(eventSource);
//		eventSource.setThread(thread);
//		thread.start();
		eventSource.getThread().start();
}	}
