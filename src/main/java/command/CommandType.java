package command;

import java.util.function.Supplier;

import command.connection.DisconnectCommand;
import command.connection.JoinCommand;
import command.music.PauseCommand;
import command.music.PlayCommand;
import command.music.ResumeCommand;
import command.music.SkipTrackCommand;
import command.music.StopTrackCommand;

public enum CommandType {
	
	
	/*comandi che lavorano con il channel corrente*/
	JOIN(JoinCommand::new),
	DISCONNECT(DisconnectCommand::new),
	
	/*comandi che lavorano con l'audioplayer corrente*/
	PLAY(PlayCommand::new),
	SKIP(SkipTrackCommand::new),
	RESUME(ResumeCommand::new),
	PAUSE(PauseCommand::new),
	STOP(StopTrackCommand::new);
	
	
	private final Supplier<Command> commandSupplier;
	
	CommandType(final Supplier<Command> commandSupplier) {
		this.commandSupplier = commandSupplier;
	}
	
	public Command getCommand() {
		return this.commandSupplier.get();
	}
	
	public String getCommandName() {
		return this.toString();
	}
	
	
}
