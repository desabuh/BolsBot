package command;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public abstract class AbstractCommand implements Command{
	
	
	
	@Override
	public abstract Mono<Void> execute(MessageCreateEvent event);
	
	public boolean requestedPermission(MessageCreateEvent event) {
		
	}
	
}
