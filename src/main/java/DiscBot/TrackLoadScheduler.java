package DiscBot;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import discord4j.common.util.Snowflake;

public final class TrackLoadScheduler implements AudioLoadResultHandler {

    private final AudioPlayer player;
    private final AudioTrackScheduler scheduler;

    public TrackLoadScheduler(final Snowflake guildId) {
    	this.player = GuildAudioManager.of(guildId).getAudioPlayer();
    	this.scheduler = GuildAudioManager.of(guildId).getAudioTrackScheduler();
    }

    @Override
    public void trackLoaded(final AudioTrack track) {
        // LavaPlayer found an audio source for us to play
        player.playTrack(track);
    }

    @Override
    public void playlistLoaded(final AudioPlaylist playlist) {
    	/*when a playlist is loaded, add all tracks to the AudioTrackScheduler internale queue(AudioAdapter that intercept  AudioTrack events for the current Player)*/
    	  playlist.getTracks()
    	  			.forEach(x -> this.scheduler.play(x));
    }

    @Override
    public void noMatches() {
        // LavaPlayer did not find any audio to extract
    }

    @Override
    public void loadFailed(final FriendlyException exception) {
        // LavaPlayer could not parse an audio source for some reason
    }
}