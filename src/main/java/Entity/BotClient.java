package Entity;

import discord4j.core.GatewayDiscordClient;

public class BotClient {
	VoiceChannelBehavior behavior;
	GatewayDiscordClient client;
	
	
	public BotClient(final GatewayDiscordClient client) {
		this.client = client;
	}
	
	
	
}
