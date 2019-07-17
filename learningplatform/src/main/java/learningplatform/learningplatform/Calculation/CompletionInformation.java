package learningplatform.learningplatform.calculation;


public class CompletionInformation {

	private int calculationStepCount;
	private int usedHelpCount;
	private int usedQuestionHelpCount;
	private int autoSolveCount;
	private int autoSolveQuestionCount;
	private int questionCount;

	public int getCalculationStepCount() {
		return calculationStepCount;
	}

	public void setCalculationStepCount(int calculationStepCount) {
		this.calculationStepCount = calculationStepCount;
	}

	public int getUsedHelpCount() {
		return usedHelpCount;
	}

	public void setUsedHelpCount(int usedHelpCount) {
		this.usedHelpCount = usedHelpCount;
	}

	public int getAutoSolveCount() {
		return autoSolveCount;
	}

	public void setAutoSolveCount(int autoSolveCount) {
		this.autoSolveCount = autoSolveCount;
	}

	public int getUsedQuestionHelpCount() {
		return usedQuestionHelpCount;
	}

	public void setUsedQuestionHelpCount(int usedQuestionHelpCount) {
		this.usedQuestionHelpCount = usedQuestionHelpCount;
	}

	public int getAutoSolveQuestionCount() {
		return autoSolveQuestionCount;
	}

	public void setAutoSolveQuestionCount(int autoSolveQuestionCount) {
		this.autoSolveQuestionCount = autoSolveQuestionCount;
	}
	
	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

}
