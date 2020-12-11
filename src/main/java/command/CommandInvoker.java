package command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.math3.util.Pair;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;


public class CommandInvoker {
	public static Map<String,CommandType> COMMANDS = new HashMap<>();
	public static String COMMAND_SPLITTER_CHAR = " ";
	
	public static void setCommand(final Pair<String,CommandType> command) {
		CommandInvoker.COMMANDS.putIfAbsent(command.getKey(),command.getValue());
	}
	
	public static Mono<Void> executeCommand(final String commandName, MessageCreateEvent triggerEvent) {
		Optional<CommandType> command = Optional.ofNullable(COMMANDS.get(commandName.split(COMMAND_SPLITTER_CHAR)[0]));
		if(command.isPresent()) {
			return command.get()
			    .getCommand()
			    .execute(triggerEvent);
		}
		return Mono.empty();
	}
	
}
