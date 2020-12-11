package DiscBot;

import java.util.Map;


import java.util.concurrent.ConcurrentHashMap;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import discord4j.common.util.Snowflake;

public final class GuildAudioManager {

	  private static final Map<Snowflake, GuildAudioManager> MANAGERS = new ConcurrentHashMap<>();

	  public static GuildAudioManager of(final Snowflake id) {
	    return MANAGERS.computeIfAbsent(id, ignored -> new GuildAudioManager());
	  }

	  private final AudioPlayer player;
	  private final AudioTrackScheduler scheduler;
	  private final LavaPlayerAudioProvider provider;

	  private GuildAudioManager() {
	    player = MainDisc.PLAYER_MANAGER.createPlayer();
	    scheduler = new AudioTrackScheduler(player);
	    provider = new LavaPlayerAudioProvider(player);

	    player.addListener(scheduler);
	  }
	  
	  
	  
	  public AudioPlayer getAudioPlayer() {
		  return this.player;
	  }
	  
	  public AudioTrackScheduler getAudioTrackScheduler() {
		  return this.scheduler;
	  }
	  
	  public LavaPlayerAudioProvider getProvider()  {
		  return this.provider;
	  }
	}
