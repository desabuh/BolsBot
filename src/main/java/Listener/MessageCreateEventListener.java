package Listener;

import command.CommandInvoker;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public class MessageCreateEventListener implements EventListener<MessageCreateEvent>{
	
	static final String PARSE_MESSAGE_CHAR = "#";
	
	
	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		return Mono.justOrEmpty(event.getMessage())
						.map(Message::getContent)
						.filter(content -> content.startsWith(PARSE_MESSAGE_CHAR))
						.map(content -> content.substring(1))
						.flatMap(content -> CommandInvoker.executeCommand(content, event))
						.then();
	}

	@Override
	public Class<MessageCreateEvent> getEventType() {
		return MessageCreateEvent.class;
	}
	

}
