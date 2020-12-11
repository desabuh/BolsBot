package DiscBot;





import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.util.Pair;
import org.apache.log4j.BasicConfigurator;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;

import Listener.MessageCreateEventListener;
import Listener.ReactionAddEventListener;
import Listener.VoiceStateUpdateEventListener;
import command.CommandInvoker;
import command.CommandType;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import utils.EventListenerRegistry;




public class MainDisc {	
	
	
	private static final Map<String, Command> commands = new HashMap<>();
	// Creates AudioPlayer instances and translates URLs to AudioTrack instances
	public static final AudioPlayerManager PLAYER_MANAGER = new DefaultAudioPlayerManager();
	public static final String TOKEN = "NzE4OTM4MDc3OTY3Njc5NTU4.XtwJQw.bahhVirka5s-9BsJthFisOGqRuU";
	
	
	
	static {
		commands.put("ping", event -> event.getMessage().getChannel()
				.flatMap(channel -> channel.createMessage("Hey!"))
				.then());	
		
		CommandInvoker.setCommand(new Pair<>("join",CommandType.JOIN));
		CommandInvoker.setCommand(new Pair<>("disconnect",CommandType.DISCONNECT));
		CommandInvoker.setCommand(new Pair<>("play",CommandType.PLAY));
		CommandInvoker.setCommand(new Pair<>("skip",CommandType.SKIP));
		CommandInvoker.setCommand(new Pair<>("resume",CommandType.RESUME));
		CommandInvoker.setCommand(new Pair<>("pause",CommandType.PAUSE));
		CommandInvoker.setCommand(new Pair<>("stop",CommandType.STOP));
		
	}
	
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		// Creates AudioPlayer instances and translates URLs to AudioTrack instances
		AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
		// This is an optimization strategy that Discord4J can utilize. It is not important to understand
		playerManager.getConfiguration().setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
		// Allow playerManager to parse remote sources like YouTube links
		AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER);
		// Create an AudioPlayer so Discord4J can receive audio data
	/////////////////	final AudioPlayer player = playerManager.createPlayer();
		// We will be creating LavaPlayerAudioProvider in the next step
	////////////////	AudioProvider provider = new LavaPlayerAudioProvider(player); 
		
		
		GatewayDiscordClient client = DiscordClientBuilder.create(TOKEN)
		         .build()
		         .login()
		         .block();
		
		EventListenerRegistry.register(client, new MessageCreateEventListener());
		EventListenerRegistry.register(client, new VoiceStateUpdateEventListener());
		EventListenerRegistry.register(client, new ReactionAddEventListener());
		
		
		
		/*
		
		
		commands.put("join", event -> Mono.justOrEmpty(event.getMember())
			    .flatMap(Member::getVoiceState)
			    .flatMap(VoiceState::getChannel)
			    // join returns a VoiceConnection which would be required if we were
			    // adding disconnection features, but for now we are just ignoring it.
			    .flatMap(channel -> channel.join(spec -> spec.setProvider(provider)))
			    .then());
						
		
		
		commands.put("disconnect",event -> Mono.justOrEmpty(event.getClient())
				.map(GatewayDiscordClient::getVoiceConnectionRegistry)
				.flatMap(x -> x.disconnect(event.getGuildId().get()))
				.then());
		
			
		
		commands.put("autokick", event -> event.getMember()
												.map(member -> member.kick())
												.orElse(Mono.empty()));
		
		 		
		 		
		
		final AudioLoadResultHandler scheduler = new TrackScheduler(player);
		
		
		commands.put("play", event -> Mono.justOrEmpty(event.getMessage().getContent())
		    .map(content -> Arrays.asList(content.split(" ")))
		    .doOnNext(command -> PLAYER_MANAGER.loadItem(command.get(1), scheduler))
		    .then());
		
		
		commands.put("stop", event -> Mono.just(player)
										.doOnSuccess(x -> x.stopTrack())
										.then());
		
		commands.put("pause", event -> Mono.just(player)
										.filter(x -> !x.isPaused())
										.doOnSuccess(x -> x.setPaused(true))
										.then());	
		
		commands.put("load", event -> Mono.justOrEmpty(player)
										.filter(x -> x.isPaused())
										.doOnSuccess(x -> x.setPaused(false))
										.doOnError(x -> x.getMessage())
										.then());	
					
		

		 GatewayDiscordClient client = DiscordClientBuilder.create("NzE4OTM4MDc3OTY3Njc5NTU4.XtwJQw.S1jGS7PxGSMRyrGsmcepaduZIhs")
	         .build()
	         .login()
	         .block();
		 
		 EventListenerRegistry.register(client, new VoiceStateUpdateEventListener());

		 
		 client.getEventDispatcher().on(ReadyEvent.class)
		 	.subscribe(event -> {
		 		User self = event.getSelf();
		 		System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
		 	});
		  
		 
		 
		 client.getEventDispatcher().on(MessageCreateEvent.class)
		    .flatMap(event -> Mono.just(event.getMessage().getContent())
		        .flatMap(content -> Flux.fromIterable(commands.entrySet())
		            .filter(entry -> content.startsWith('#' + entry.getKey()))
		            .flatMap(entry -> entry.getValue().execute(event))
		            .next()))
		    .subscribe(); 


		 	*/
		 
		 client.onDisconnect().block();
		
		
	}
}
