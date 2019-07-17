package com.learningplatform.learningplatform.bkt;

public class BktUpdateConceptual {

	private BktTypeConceptual bktTypeConceptual;
	private int updateCount = 0;
	private boolean result;

	public BktUpdateConceptual(BktTypeConceptual bktTypeConceptual, boolean result) {
		this.bktTypeConceptual = bktTypeConceptual;
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public BktTypeConceptual getBktTypeConceptual() {
		return bktTypeConceptual;
	}

	public void setBktTypeConceptual(BktTypeConceptual bktTypeConceptual) {
		this.bktTypeConceptual = bktTypeConceptual;
	}
	
	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}

}
