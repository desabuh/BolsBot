package command.connection;

import command.Command;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class DisconnectCommand implements Command{

	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		return Mono.justOrEmpty(event.getClient())
			.map(GatewayDiscordClient::getVoiceConnectionRegistry)
			.flatMap(x -> x.getVoiceConnection(event.getGuildId().get()))
			.flatMap(x -> x.disconnect())
			.then();
	}

}
