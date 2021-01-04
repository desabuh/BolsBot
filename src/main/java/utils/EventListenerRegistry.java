package utils;




import Listener.EventListener;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;

public class EventListenerRegistry {
	
	@SuppressWarnings("unchecked")
	public static <T extends Event> void register(GatewayDiscordClient client, EventListener<T> listener) {
		client.getEventDispatcher().on((Class<T>) listener.getEventType())
									.flatMap(event -> listener.execute(event))
									.subscribe();				
	}
	
}
