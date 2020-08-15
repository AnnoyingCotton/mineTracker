package me.anCot.mineTracker.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.anCot.mineTracker.LobbyHandler;

public class FindLobby implements CommandExecutor {
	
	LobbyHandler handle;
	
	public FindLobby(LobbyHandler handle){
		this.handle = handle;
	}
	
	public int playersInQueue = 0;
	
	//command to put players into the queue and then sort them into a lobby!
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(!handle.players.contains(player.getName())) {
				
				handle.players.add(player.getName());
				
				handle.playerq.add(player.getName());
				
				playersInQueue++;
				
				player.sendMessage("NOW QUEUED!");
				
				handle.assignLobby();
			}
			else {
				player.sendMessage("You are already queued!");
			}
			
		}
		else {
			sender.sendMessage("This command must be run by a player!");
		}
		
		return true;
	}

}
