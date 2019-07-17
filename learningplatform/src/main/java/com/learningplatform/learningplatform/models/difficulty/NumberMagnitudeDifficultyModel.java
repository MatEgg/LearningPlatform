package com.learningplatform.learningplatform.models.difficulty;


import com.learningplatform.learningplatform.models.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * Model of the number magnitude difficulty.
 *
 */
@Entity
@Table(name = "numbermagnitude_difficulty")
public class NumberMagnitudeDifficultyModel extends Model {

	private int uid;
	private int numbermagnitudeDifficulty;
	private int digitcountDifficulty;
	private int manual;

	@OneToOne(mappedBy = "numbermagnitude_difficulty")
	private Difficulty difficulty;

	public NumberMagnitudeDifficultyModel(int numbermagnitudeDifficulty, int digitcountDifficulty, int manual) {
		this.numbermagnitudeDifficulty = numbermagnitudeDifficulty;
		this.digitcountDifficulty = digitcountDifficulty;
		this.manual = manual;
	}

	public NumberMagnitudeDifficultyModel() {

	}

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_numbermagnitude_difficulty", unique = true)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "numbermagnitude_difficulty")
	public int getNumbermagnitudeDifficulty() {
		return numbermagnitudeDifficulty;
	}

	public void setNumbermagnitudeDifficulty(int numbermagnitudeDifficulty) {
		this.numbermagnitudeDifficulty = numbermagnitudeDifficulty;
	}

	@Column(name = "digitcount_difficulty")
	public int getDigitcountDifficulty() {
		return digitcountDifficulty;
	}

	public void setDigitcountDifficulty(int digitcountDifficulty) {
		this.digitcountDifficulty = digitcountDifficulty;
	}

	@Column(name = "manual")
	public int getManual() {
		return manual;
	}

	public void setManual(int manual) {
		this.manual = manual;
	}

}
