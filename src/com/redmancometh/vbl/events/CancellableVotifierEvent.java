package com.redmancometh.vbl.events;

import org.bukkit.event.Cancellable;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

public class CancellableVotifierEvent extends VotifierEvent implements Cancellable
{
    private boolean cancelled = false;

    public CancellableVotifierEvent(Vote vote)
    {
        super(vote);
    }

    @Override
    public boolean isCancelled()
    {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean arg0)
    {
        this.cancelled = arg0;
    }

}
