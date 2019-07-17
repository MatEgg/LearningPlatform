package com.learningplatform.learningplatform.models.difficulty.conceptual;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;

/**
 * Model of the conceptual difficulty.
 *
 */
@Entity
@Table(name = "conceptual_difficulty")
public class ConceptualDifficultyModel extends Model {

	@Id
	@NotNull
	@GeneratedValue
	@Column(name = "id_conceptual_difficulty", unique = true)
	private int uid;

	@Column(name = "replace_difficulty")
	private int replaceDifficulty;

	@Column(name = "conceptual_difficulty")
	private int conceptualDifficulty;

	@Column(name = "different_concepts_difficulty")
	private int differentConceptsDifficulty;

	@Column(name = "question_difficulty")
	private int questionDifficulty;

	@Column(name = "distractors_difficulty")
	private int distractorsDifficulty;

	@Column(name = "manual")
	private int manual;

	@OneToOne(mappedBy = "conceptualDifficultyModel")
	private Difficulty difficulty;

	public ConceptualDifficultyModel(int replaceDifficulty, int conceptualDifficulty,
			int differentConceptsDifficulty, int questionDifficulty, int distractorsDifficulty, int manual) {
		this.replaceDifficulty = replaceDifficulty;
		this.conceptualDifficulty = conceptualDifficulty;
		this.differentConceptsDifficulty = differentConceptsDifficulty;
		this.questionDifficulty = questionDifficulty;
		this.distractorsDifficulty = distractorsDifficulty;
		this.manual = manual;
	}

	public ConceptualDifficultyModel() {

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getReplaceDifficulty() {
		return replaceDifficulty;
	}

	public void setReplaceDifficulty(int replaceDifficulty) {
		this.replaceDifficulty = replaceDifficulty;
	}

	public int getConceptualDifficulty() {
		return conceptualDifficulty;
	}

	public void setConceptualDifficulty(int conceptualDifficulty) {
		this.conceptualDifficulty = conceptualDifficulty;
	}

	public int getDifferentConceptsDifficulty() {
		return differentConceptsDifficulty;
	}

	public void setDifferentConceptsDifficulty(int differentConceptsDifficulty) {
		this.differentConceptsDifficulty = differentConceptsDifficulty;
	}

	public int getQuestionDifficulty() {
		return questionDifficulty;
	}

	public void setQuestionDifficulty(int questionDifficulty) {
		this.questionDifficulty = questionDifficulty;
	}

	public int getDistractorsDifficulty() {
		return distractorsDifficulty;
	}

	public void setDistractorsDifficulty(int distractorsDifficulty) {
		this.distractorsDifficulty = distractorsDifficulty;
	}

	public int getManual() {
		return manual;
	}

	public void setManual(int manual) {
		this.manual = manual;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

}
