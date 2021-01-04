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
	public static final AudioPlayerManager PLAYER_MANAGER = new DefaultAudioPlayerManager();
	public static final String TOKEN = "";
	
	
	
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
		
		AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
		playerManager.getConfiguration().setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
		AudioSourceManagers.registerRemoteSources(PLAYER_MANAGER);

		
		
		GatewayDiscordClient client = DiscordClientBuilder.create(TOKEN)
		         .build()
		         .login()
		         .block();
		
		EventListenerRegistry.register(client, new MessageCreateEventListener());
		EventListenerRegistry.register(client, new VoiceStateUpdateEventListener());
		EventListenerRegistry.register(client, new ReactionAddEventListener());

		 
		 client.onDisconnect().block();
		
		
	}
}
