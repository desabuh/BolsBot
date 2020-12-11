package Entity;

import DiscBot.GuildAudioManager;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.VoiceChannel;
import reactor.core.publisher.Mono;

public class VoiceChannelAccess implements VoiceChannelBehavior{
	
	private GatewayDiscordClient client;
	private Snowflake voiceChannelId;
	
	
	
	public VoiceChannelAccess(final GatewayDiscordClient client, final Snowflake voiceChannelId) {
		this.client = client;
		this.voiceChannelId = voiceChannelId;
	}

	public Mono<Void> joinVoiceChannel() {
		return Mono.justOrEmpty(client.getChannelById(voiceChannelId))
										.cast(VoiceChannel.class)
										.filter(x -> !isJoinedChannel())
										.flatMap(channel ->   Mono.just(GuildAudioManager.of(channel.getGuildId()).getProvider())
												.map(provider -> channel.join(spec -> spec.setProvider(provider))))
										.then(); 											
	}


	public Mono<Void> disconnectFromVoiceChannel() {
		return Mono.justOrEmpty(client)
					.map(GatewayDiscordClient::getVoiceConnectionRegistry)
					.filter(x -> x.getVoiceConnection(this.getGuilIdFromChannelId(voiceChannelId, client)).hasElement().block())
					.flatMap(x -> x.disconnect(voiceChannelId))
					.then();
	}


	private boolean isJoinedChannel() {
		return client.getVoiceConnectionRegistry()
					.getVoiceConnection(this.getGuilIdFromChannelId(voiceChannelId, client))
					.hasElement()
					.block();
		
	}
	
	private Snowflake getGuilIdFromChannelId(Snowflake channelId,GatewayDiscordClient client) {
		return client.getChannelById(channelId)
				.cast(VoiceChannel.class)
				.map(VoiceChannel::getGuildId)
				.block();
	}
	

}
