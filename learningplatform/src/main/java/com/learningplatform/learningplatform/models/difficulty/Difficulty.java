package com.learningplatform.learningplatform.models.difficulty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.conceptual.ConceptualDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.OperationalDifficultyModel;

/**
 * Model of difficulty settings. Contains all sub difficulties.
 *
 */
@Entity
@Table(name = "difficulty")
public class Difficulty extends Model {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_difficulty", unique = true)
	private int uid;

	@OneToOne
	@JoinColumn(name = "id_conceptual_difficulty")
	private ConceptualDifficultyModel conceptualDifficultyModel;

	@OneToOne
	@JoinColumn(name = "id_operational_difficulty")
	private OperationalDifficultyModel operationalDifficultyModel;

	@OneToOne
	@JoinColumn(name = "id_concept_difficulty")
	private ConceptDifficultyModel conceptDifficultyModel;

	@OneToOne(mappedBy = "difficulty")
	private Settings settings;

	public Difficulty(ConceptualDifficultyModel conceptualDifficultyModel,
			OperationalDifficultyModel operationalDifficultyModel, ConceptDifficultyModel conceptDifficultyModel) {
		this.conceptualDifficultyModel = conceptualDifficultyModel;
		this.operationalDifficultyModel = operationalDifficultyModel;
		this.conceptDifficultyModel = conceptDifficultyModel;
	}

	public Difficulty() {
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	
	public ConceptualDifficultyModel getConceptualDifficultyModel() {
		return conceptualDifficultyModel;
	}

	public void setConceptualDifficultyModel(ConceptualDifficultyModel conceptualDifficultyModel) {
		this.conceptualDifficultyModel = conceptualDifficultyModel;
	}

	public OperationalDifficultyModel getOperationalDifficultyModel() {
		return operationalDifficultyModel;
	}

	public void setOperationalDifficultyModel(OperationalDifficultyModel operationalDifficultyModel) {
		this.operationalDifficultyModel = operationalDifficultyModel;
	}

	public ConceptDifficultyModel getConceptDifficultyModel() {
		return conceptDifficultyModel;
	}

	public void setConceptDifficultyModel(ConceptDifficultyModel conceptDifficultyModel) {
		this.conceptDifficultyModel = conceptDifficultyModel;
	}

}
