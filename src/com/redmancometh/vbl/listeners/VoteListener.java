package com.redmancometh.vbl.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.redmancometh.vbl.VotifierBL;
import com.redmancometh.vbl.events.CancellableVotifierEvent;
import com.vexsoftware.votifier.model.VotifierEvent;

public class VoteListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVote(VotifierEvent event)
    {
        CancellableVotifierEvent e = new CancellableVotifierEvent(event.getVote());
        Bukkit.getPluginManager().callEvent(e);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCancellableVote(CancellableVotifierEvent event)
    {
        String username = event.getVote().getUsername();
        VotifierBL.instance().cfg().getPrefixes().forEach((prefix) ->
        {
            if (username.startsWith(prefix)) event.setCancelled(true);
        });
    }
}
