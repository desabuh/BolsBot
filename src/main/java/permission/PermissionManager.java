package permission;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import DiscBot.GuildAudioManager;
import discord4j.common.util.Snowflake;

public class PermissionManager {
	
	 private static final Map<Snowflake, Permission> PERMISSION_POOL = new ConcurrentHashMap<>();
	
	
}
