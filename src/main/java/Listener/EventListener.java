package Listener;

import discord4j.core.event.domain.Event;
import reactor.core.publisher.Mono;

public interface EventListener<E extends Event> {
	
	Mono<Void> execute(E event);
	
	Class<?> getEventType();
	
}
