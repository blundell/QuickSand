package com.blundell.quicksand.viscosity;

/**
 * Java command pattern
 */
public interface Viscosity {

    /**
     * @param currentDuration how long the animation is at the moment
     * @param viewCount       how many times the animation has been seen before (excluding this upcoming anim)
     * @return Return how long you want the animation to last
     */
    long calculateDuration(long currentDuration, long viewCount);

}
