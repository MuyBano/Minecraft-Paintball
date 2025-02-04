package me.gorgeousone.paintball.command.lobby;

import me.gorgeousone.paintball.arena.PbArena;
import me.gorgeousone.paintball.arena.PbArenaHandler;
import me.gorgeousone.paintball.cmdframework.argument.ArgType;
import me.gorgeousone.paintball.cmdframework.argument.ArgValue;
import me.gorgeousone.paintball.cmdframework.argument.Argument;
import me.gorgeousone.paintball.cmdframework.command.ArgCommand;
import me.gorgeousone.paintball.game.PbLobby;
import me.gorgeousone.paintball.game.PbLobbyHandler;
import me.gorgeousone.paintball.util.StringUtil;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LobbyUnlinkArenaCommand extends ArgCommand {

	private final PbLobbyHandler lobbyHandler;
	private final PbArenaHandler arenaHandler;

	public LobbyUnlinkArenaCommand(PbLobbyHandler lobbyHandler, PbArenaHandler arenaHandler) {
		super("unlink-arena");
		this.addArg(new Argument("lobby name", ArgType.STRING));
		this.addArg(new Argument("arena names...", ArgType.STRING));

		this.lobbyHandler = lobbyHandler;
		this.arenaHandler = arenaHandler;
	}
	
	@Override
	protected void executeArgs(CommandSender sender, List<ArgValue> argValues, Set<String> usedFlags) {
		String lobbyName = argValues.get(0).get();
		PbLobby lobby = lobbyHandler.getLobby(lobbyName);

		if (lobby == null) {
			StringUtil.msg(sender, "Lobby %s does not exits!", lobbyName);
			return;
		}
		for (int i = 1; i < argValues.size(); ++i) {
			String arenaName = argValues.get(i).get();
			PbArena arena = arenaHandler.getArena(arenaName);

			if (arena == null) {
				StringUtil.msg(sender, "Arena %s does not exits!", arenaName);
				continue;
			}
			try {
				lobby.unlinkArena(arena);
				StringUtil.msg(sender, "Arena %s is now detached from lobby %s.", arenaName, lobbyName);
			} catch (IllegalArgumentException e) {
				sender.sendMessage(e.getMessage());
			}
		}
	}
	@Override
	protected List<String> onTabComplete(CommandSender sender, String[] stringArgs) {
		if (stringArgs.length == 1) {
			return lobbyHandler.getLobbies().stream().map(PbLobby::getName).collect(Collectors.toList());
		} else if (stringArgs.length == 2) {
			PbLobby lobby = lobbyHandler.getLobby(stringArgs[0]);

			if (lobby != null) {
				return lobby.getArenas().stream().map(PbArena::getName).collect(Collectors.toList());
			}
		}
		return new LinkedList<>();
	}
}
