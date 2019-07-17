package com.learningplatform.learningplatform.guiObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Overview of the statistics of the student.
 *
 */
public class StatsGui {

	private List<Stat> numericalStats;
	private List<Stat> conceptualStats;

	public StatsGui() {
		this.conceptualStats = new ArrayList<>();
		this.numericalStats = new ArrayList<>();
	}

	/**
	 * adds the numerical statistics to the gui.
	 * @param additionGui the information for the addition overview
	 * @param subtractionGui the information for the subtraction overview
	 * @param multiplicationGui the information for the multiplication overview
	 * @param divisionGui the information for the division overview
	 */
	public void addNumericalStats(AdditionGui additionGui, SubtractionGui subtractionGui,
			MultiplicationGui multiplicationGui, DivisionGui divisionGui) {
		numericalStats.add(additionGui.getDecimalHandling());
		numericalStats.add(additionGui.getBigNumberHandling());
		numericalStats.add(additionGui.getNumericalType());

		numericalStats.add(subtractionGui.getDecimalHandling());
		numericalStats.add(subtractionGui.getBigNumberHandling());
		numericalStats.add(subtractionGui.getNumericalType());

		numericalStats.add(multiplicationGui.getDecimalHandling());
		numericalStats.add(multiplicationGui.getBigNumberHandling());
		numericalStats.add(multiplicationGui.getNumericalType());

		numericalStats.add(divisionGui.getDecimalHandling());
		numericalStats.add(divisionGui.getBigNumberHandling());
		numericalStats.add(divisionGui.getNumericalType());
	}

	/**
	 * adds solving task related statistics to the Gui.
	 * @param chooseCorrectFormulars boolean if the correct formula was used
	 * @param chooseCorrectInformation boolean if the correct information was used
	 * @param chooseCorrectValues boolean if the correct values were used
	 */
	public void addSolvingTaskStats(int chooseCorrectFormulars, int chooseCorrectInformation, int chooseCorrectValues) {
		conceptualStats.add(new Stat("Korrekte Verwendung von Formeln", chooseCorrectFormulars));
		conceptualStats.add(new Stat("Korrekte Informationswahl", chooseCorrectInformation));
		conceptualStats.add(new Stat("Verwendung korrekter Werte", chooseCorrectValues));
	}

	/**
	 * adds concept related statistics to the Gui.
	 * @param computationalRules information about computational rules
	 * @param areaCalculation information about area calculation
	 * @param calculateProbabilities information about calculate probabilities
	 */
	public void addConceptStats(int computationalRules, int areaCalculation, int calculateProbabilities) {
		conceptualStats.add(new Stat("Rechenregeln/Arithmetik", computationalRules));
		conceptualStats.add(new Stat("Flächenberechnung", areaCalculation));
		conceptualStats.add(new Stat("Zufall und Wahrscheinlichkeiten", calculateProbabilities));
	}

	public void addGeneralPerformanceStats(int stepsNeeded, int solvingCarryTasks) {
		conceptualStats.add(new Stat("Benötigte Schritte für Aufgabenlösung", stepsNeeded));
		conceptualStats.add(new Stat("Lösen mehrstufiger Aufgaben", solvingCarryTasks));
	}

	public List<Stat> getConceptualStats() {
		return conceptualStats;
	}

	public void setConceptualStats(List<Stat> conceptualStats) {
		this.conceptualStats = conceptualStats;
	}

	public List<Stat> getNumericalStats() {
		return numericalStats;
	}

	public void setNumericalStats(List<Stat> numericalStats) {
		this.numericalStats = numericalStats;
	}
}
