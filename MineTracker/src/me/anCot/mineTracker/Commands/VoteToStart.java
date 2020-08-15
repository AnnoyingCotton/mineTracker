package me.anCot.mineTracker.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.anCot.mineTracker.Lobby;
import me.anCot.mineTracker.LobbyHandler;

public class VoteToStart implements CommandExecutor {
	//this class handles the votetostart command for all of the games on the server. finds the game the player is in and then adds a vote to their lobby!!
	
	LobbyHandler handle;
	
	
	public int playersNeeded = 2;
	
	public VoteToStart(LobbyHandler handle) {
		this.handle = handle;
	}

	@Override
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {
		
		Player player = (Player) sender;
		
		
		if(player instanceof Player) {
			
			if(args.length > 0)
			{
				if(args[0].equalsIgnoreCase("mt")) {
					voteSkipmt(player);
				}
			}
			
		}
		else {
			sender.sendMessage("Player must run this command!");
		}
		
		
		
		return true;
	}
	
	public void voteSkipmt(Player p) {
		
		Lobby lob = handle.lobbies.get(handle.playerInLobby.get(p.getName()));
		
		if(!lob.playersVoted.contains(p.getName()) && !lob.gameStart) {
			
			p.sendMessage("Voted to start game!");
			
			lob.playersVoted.add(p.getName());
			
			lob.votesToStart++;
			
			if(lob.votesToStart == lob.players && lob.players >= playersNeeded) {
				
				lob.startGame();
				
			}
		}
		
		
	}

}
