package command;


import DiscBot.GuildAudioManager;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import reactor.core.publisher.Mono;

public class JoinCommand implements Command{

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		
		System.out.println(event.getGuildId().get());
		
		if(this.isBot(event)) {
			return Mono.empty();
		}
		
		return Mono.justOrEmpty(event.getMember()) 
						.flatMap(Member::getVoiceState)
						.flatMap(VoiceState::getChannel)
						.flatMap(channel -> channel.join(spec -> spec.setProvider(GuildAudioManager.of(channel.getGuildId()).getProvider())))
						.then(); 
						
	}
	
	private boolean isBot(MessageCreateEvent event) {
		return event.getMember().isEmpty() || event.getMember().get().isBot();
	}
	
	
	
}
