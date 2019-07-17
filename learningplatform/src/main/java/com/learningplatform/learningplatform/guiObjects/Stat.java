package com.learningplatform.learningplatform.guiObjects;

import com.learningplatform.learningplatform.bkt.BktHandler;
import com.learningplatform.learningplatform.bkt.BktHandlerUtils;

public class Stat {

	private String name;
	private int level;
	private int progress;

	public Stat(String name, int value) {
		this.name = name;
		this.level = BktHandlerUtils.extractLevel(value);
		this.progress = value % 100;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}


}
