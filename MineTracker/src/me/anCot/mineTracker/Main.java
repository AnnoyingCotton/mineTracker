package me.anCot.mineTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import me.anCot.mineTracker.Commands.FindLobby;
import me.anCot.mineTracker.Commands.VoteToStart;
import me.anCot.mineTracker.Events.EventsClass;

public class Main extends JavaPlugin{
	
	public LobbyHandler handle;
	
	public EventsClass events;
	
	//plugin set up. establishes the lobby handler and the lobbies on startup!!
	public void onEnable() {
		
				
		handle = new LobbyHandler();
		
		handle.players = new HashSet<String>();
		
		handle.playerq = new PriorityQueue<String>();
		
		handle.playerInLobby = new HashMap<String,Integer>();
		
		handle.lobbies = new ArrayList<Lobby>();
		
		for(int i=0;i<handle.maxLobbies;i++) {
			handle.createLobby(i);
		}
		
		this.getCommand("findlobby").setExecutor((CommandExecutor)new FindLobby(handle));
		
		this.getCommand("votestart").setExecutor((CommandExecutor)new VoteToStart(handle));
		
		getServer().getPluginManager().registerEvents(new EventsClass(handle), this);
		
	}
	
	public void onDisable() {
		
	}
	
}
