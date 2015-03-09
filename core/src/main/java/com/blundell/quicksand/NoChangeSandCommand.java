package com.blundell.quicksand;

public class NoChangeSandCommand implements SandCommand {
    @Override
    public long calculateDuration(long currentDuration, long viewCount) {
        return currentDuration;
    }
}
