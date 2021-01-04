package guiceModules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import guiceModules.annotation.client;

public class GatewayClientModule extends AbstractModule{
	
	
	@Provides
	@Singleton
	@client
	static GatewayDiscordClient provideGateway() {
		return DiscordClientBuilder.create("")
		         .build()
		         .login()
		         .block();
	}

}
