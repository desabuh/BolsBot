package command;

import DiscBot.GuildAudioManager;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class PauseCommand implements Command{

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		return Mono.justOrEmpty(event.getGuildId())
				.map(GuildAudioManager::of)
				.map(manager -> manager.getAudioTrackScheduler())
				.map(scheduler -> scheduler.pause())
				.doOnError(e -> e.printStackTrace())
				.then();	
	}

}
