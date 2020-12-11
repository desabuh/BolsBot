package command;

import java.util.Arrays;


import DiscBot.TrackLoadScheduler;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;
import static DiscBot.MainDisc.PLAYER_MANAGER;


public class PlayCommand implements Command{
	
	
	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		  return Mono.justOrEmpty(event.getMessage().getContent())
				  .map(content -> Arrays.asList(content.split(" ")))
				  .doOnNext(command -> PLAYER_MANAGER.loadItem(command.get(1), new TrackLoadScheduler(event.getGuildId().get())))
				  .then(); 
	}

}
