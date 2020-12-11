package DiscBot;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import reactor.core.publisher.Mono;


public final class AudioTrackScheduler extends AudioEventAdapter{
	
	private final List<AudioTrack> queue;
	private final AudioPlayer player;
	
	public AudioTrackScheduler(final AudioPlayer player) {
		this.queue = Collections.synchronizedList(new LinkedList<>());
		this.player = player;
	}
	
	public List<AudioTrack> getQueue() {
		return this.queue;
	}
	
	public boolean play(final AudioTrack track) {
		return this.play(track, false);
	}
	
	public boolean play(final AudioTrack track, final boolean force) {
		final boolean playing = player.startTrack(track, !force);
		
		if(!playing) {
			this.queue.add(track);
		}
		return playing;
	}
	
	public boolean skip() {
		return !this.queue.isEmpty() && this.play(queue.remove(0), true);
	}
	
	public Mono<Void> pause() {
		if(!this.player.isPaused()) {
			this.player.setPaused(true);
		}
		return Mono.empty();
	}
	
	public void resume() {
		if(this.player.isPaused()) {
			this.player.setPaused(false);
		}
	}
	
	public void stop() {
		this.player.destroy();
	}
	
	
	@Override
	public void onTrackEnd(final AudioPlayer player, final AudioTrack track, final AudioTrackEndReason endReason) {
		if(endReason.mayStartNext) {
			this.skip();
		}
	}
	
	
	
	
	
	
}
