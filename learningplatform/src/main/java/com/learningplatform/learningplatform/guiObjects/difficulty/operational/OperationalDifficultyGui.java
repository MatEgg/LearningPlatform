package com.learningplatform.learningplatform.guiObjects.difficulty.operational;

/**
 * Holds the information regarding the difficulty settings of all operational skills.
 *
 */
public class OperationalDifficultyGui {

	private int uid;
	private AdditionDifficultyGui additionDifficultyGui;
	private SubtractionDifficultyGui subtractionDifficultyGui;
	private MultiplicationDifficultyGui multiplicationDifficultyGui;
	private DivisionDifficultyGui divisionDifficultyGui;

	public OperationalDifficultyGui(int uid, AdditionDifficultyGui additionDifficultyGui,
			SubtractionDifficultyGui subtractionDifficultyGui, MultiplicationDifficultyGui multiplicationDifficultyGui,
			DivisionDifficultyGui divisionDifficultyGui) {
		this.uid = uid;
		this.additionDifficultyGui = additionDifficultyGui;
		this.subtractionDifficultyGui = subtractionDifficultyGui;
		this.multiplicationDifficultyGui = multiplicationDifficultyGui;
		this.divisionDifficultyGui = divisionDifficultyGui;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public AdditionDifficultyGui getAdditionDifficultyGui() {
		return additionDifficultyGui;
	}

	public void setAdditionDifficultyGui(AdditionDifficultyGui additionDifficultyGui) {
		this.additionDifficultyGui = additionDifficultyGui;
	}

	public SubtractionDifficultyGui getSubtractionDifficultyGui() {
		return subtractionDifficultyGui;
	}

	public void setSubtractionDifficultyGui(SubtractionDifficultyGui subtractionDifficultyGui) {
		this.subtractionDifficultyGui = subtractionDifficultyGui;
	}

	public MultiplicationDifficultyGui getMultiplicationDifficultyGui() {
		return multiplicationDifficultyGui;
	}

	public void setMultiplicationDifficultyGui(MultiplicationDifficultyGui multiplicationDifficultyGui) {
		this.multiplicationDifficultyGui = multiplicationDifficultyGui;
	}

	public DivisionDifficultyGui getDivisionDifficultyGui() {
		return divisionDifficultyGui;
	}

	public void setDivisionDifficultyGui(DivisionDifficultyGui divisionDifficultyGui) {
		this.divisionDifficultyGui = divisionDifficultyGui;
	}

}
