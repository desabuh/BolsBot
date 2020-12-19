package command.music;

import DiscBot.GuildAudioManager;
import command.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class SkipTrackCommand implements Command{

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		return Mono.justOrEmpty(event.getGuildId())
					.map(GuildAudioManager::of)
					.map(manager -> manager.getAudioTrackScheduler())
					.map(scheduler -> scheduler.skip())
					.doOnError(e -> e.printStackTrace())
					.then();	
	}

}
