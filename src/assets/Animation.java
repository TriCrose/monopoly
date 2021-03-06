package assets;

import java.awt.image.BufferedImage;

public class Animation {
	private BufferedImage[] frames;		// Array of frames
	private final int frameTime;		// Length of each frame in milliseconds
	private int animationTime = 0;		// Time since start of animation in milliseconds
	private boolean paused = false;		// If true then the animation is paused
	private boolean loop = true;		// Should the animation loop
	private int frameIndex;				// Current frame index
	
	public Animation(BufferedImage[] frames, int frameTime) {
		this.frames = frames.clone();
		this.frameTime = frameTime;
	}

	public void play() {
		paused = false;
	}
	
	public void pause() {
		paused = true;
	}
	
	public boolean getPaused() {
		return paused;
	}
	
	// Go to a specific time in the animation
	public void goToTime(int ms) {
		animationTime = ms;
	}
	
	public int getAnimationTime() {
		return animationTime;
	}
	
	// Go to a specific frame in the animation
	public void goToFrame(int frameNo) {
		animationTime = frameNo * frameTime;
	}
	
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	
	public boolean isLooping() {
		return loop;
	}
	
	public int getFrameTime() {
		return frameTime;
	}
	
	// Get width of current frame
	public int getWidth() {
		return frames[frameIndex].getWidth();
	}
	
	// Get height of current frame
	public int getHeight() {
		return frames[frameIndex].getHeight();
	}
	
	// Get current frame index
	public int getFrameIndex() {
		return frameIndex;
	}
	
	public void updateFrameIndex(long dt) {
		// If the animation has stopped, don't do anything
		if (paused) return;
		
		animationTime += dt;
		
		// If the animation is beyond the end, then either loop or stop
		if (animationTime >= frameTime * frames.length) {
			if (loop) {
				animationTime %= frameTime * frames.length;
			} else {
				animationTime = frameTime * frames.length - 1;
				paused = true;
			}
		}
		
		frameIndex = Math.min(frames.length - 1, animationTime / frameTime);
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[frameIndex];
	}
}