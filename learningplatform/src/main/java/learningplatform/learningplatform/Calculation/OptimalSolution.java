package learningplatform.learningplatform.Calculation;

public class OptimalSolution {

	private String optimalSolutionText;
	private String optimalSolutionDetailed;
	private CalculationStats calculationStats;

	public OptimalSolution(AttributeCalculation attributeCalculation) {
		this.optimalSolutionText = attributeCalculation.getCalculationTextSimpleWrapper();
		this.calculationStats = attributeCalculation.getCalculationStats();
	}

	public OptimalSolution() {

	}

	public String getOptimalSolutionText() {
		return optimalSolutionText;
	}

	public void setOptimalSolutionText(String optimalSolutionText) {
		this.optimalSolutionText = optimalSolutionText;
	}

	public String getOptimalSolutionDetailed() {
		return optimalSolutionDetailed;
	}

	public void setOptimalSolutionDetailed(String optimalSolutionDetailed) {
		this.optimalSolutionDetailed = optimalSolutionDetailed;
	}

	public CalculationStats getCalculationStats() {
		return calculationStats;
	}

	public void setCalculationStats(CalculationStats calculationStats) {
		this.calculationStats = calculationStats;
	}
}
