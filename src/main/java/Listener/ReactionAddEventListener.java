package Listener;

import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

public class ReactionAddEventListener implements EventListener<ReactionAddEvent>{

	@Override
	public Mono<Void> execute(ReactionAddEvent event) {
		return event.getChannel()
				.map(x -> x.createEmbed(spec ->
					spec.setColor(Color.DARK_GOLDENROD)
						.setDescription("CIAO")
				).block())
			    .then();
	}

	@Override
	public Class<ReactionAddEvent> getEventType() {
		return ReactionAddEvent.class;
	}

}
