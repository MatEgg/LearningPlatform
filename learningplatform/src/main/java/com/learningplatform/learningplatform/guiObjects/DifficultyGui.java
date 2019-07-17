package com.learningplatform.learningplatform.guiObjects;

import com.learningplatform.learningplatform.guiObjects.difficulty.concept.ConceptDifficultyModelGui;
import com.learningplatform.learningplatform.guiObjects.difficulty.conceptual.ConceptualDifficultyModelGui;
import com.learningplatform.learningplatform.guiObjects.difficulty.operational.OperationalDifficultyGui;

/**
 * Overview of the difficulty settings for the student.
 *
 */
public class DifficultyGui {

	private int uid;
	private ConceptDifficultyModelGui conceptDifficultyModelGui;
	private ConceptualDifficultyModelGui conceptualDifficultyModelGui;
	private OperationalDifficultyGui operationalDifficultyModelGui;

	public DifficultyGui(int uid, ConceptDifficultyModelGui conceptDifficultyModelGui,
			ConceptualDifficultyModelGui conceptualDifficultyModelGui,
			OperationalDifficultyGui operationalDifficultyModelGui) {
		this.uid = uid;
		this.conceptDifficultyModelGui = conceptDifficultyModelGui;
		this.conceptualDifficultyModelGui = conceptualDifficultyModelGui;
		this.operationalDifficultyModelGui = operationalDifficultyModelGui;
	}

	public ConceptDifficultyModelGui getConceptDifficultyModelGui() {
		return conceptDifficultyModelGui;
	}

	public void setConceptDifficultyModelGui(ConceptDifficultyModelGui conceptDifficultyModelGui) {
		this.conceptDifficultyModelGui = conceptDifficultyModelGui;
	}

	public ConceptualDifficultyModelGui getConceptualDifficultyModelGui() {
		return conceptualDifficultyModelGui;
	}

	public void setConceptualDifficultyModelGui(ConceptualDifficultyModelGui conceptualDifficultyModelGui) {
		this.conceptualDifficultyModelGui = conceptualDifficultyModelGui;
	}

	public OperationalDifficultyGui getOperationalDifficultyModelGui() {
		return operationalDifficultyModelGui;
	}

	public void setOperationalDifficultyModelGui(OperationalDifficultyGui operationalDifficultyModelGui) {
		this.operationalDifficultyModelGui = operationalDifficultyModelGui;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
