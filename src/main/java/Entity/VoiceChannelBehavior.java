package Entity;

import reactor.core.publisher.Mono;

public interface VoiceChannelBehavior {
	
	public Mono<Void> joinVoiceChannel();
	
	public Mono<Void> disconnectFromVoiceChannel();
	
}
