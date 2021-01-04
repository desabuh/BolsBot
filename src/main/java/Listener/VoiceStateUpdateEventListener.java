package Listener;


import discord4j.core.event.domain.VoiceStateUpdateEvent;
import reactor.core.publisher.Mono;

public class VoiceStateUpdateEventListener implements EventListener<VoiceStateUpdateEvent>{

	@Override
	public Mono<Void> execute(VoiceStateUpdateEvent event) {
		if(this.isFromBot(event)) {
			if(event.getCurrent().getChannelId().isPresent() && event.getOld().isEmpty()) {
				System.out.println("ENTRO");
			}
			else if(event.getCurrent().getChannelId().isEmpty() && event.getOld().isPresent()) {
				System.out.println("ESCO");
			}
		}
		return Mono.empty();
	}
	
	private boolean isFromBot(VoiceStateUpdateEvent event) {
		return event.getCurrent().getUserId().equals(
				event.getClient().getSelfId());
	}

	@Override
	public Class<VoiceStateUpdateEvent> getEventType() {
		return VoiceStateUpdateEvent.class;
	}

}
