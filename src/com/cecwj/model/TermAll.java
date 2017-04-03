package com.cecwj.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.cecwj.common.TimeFormat;

@javax.persistence.Entity
@javax.persistence.Table(name = "drug")
public class TermAll {
	private int id;
	private String drugcode;
	private String name;
	private String spec;
	private String units;
	private String doseType;
	private String property;
	private float mixUnit;
	private String doseUnit;
	private String code;
	private float indicator;
	private int amount;
	private int need;
	private Timestamp entrytime;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "DRUG_NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DRUG_SPEC")
	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Column(name = "UNITS")
	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	@Column(name = "DRUG_FORM")
	public String getDoseType() {
		return this.doseType;
	}

	public void setDoseType(String doseType) {
		this.doseType = doseType;
	}

	@Column(name = "TOXI_PROPERTY")
	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Column(name = "DOSE_PER_UNIT")
	public float getMixUnit() {
		return this.mixUnit;
	}

	public void setMixUnit(float mixUnit) {
		this.mixUnit = mixUnit;
	}

	@Column(name = "DOSE_UNITS")
	public String getDoseUnit() {
		return this.doseUnit;
	}

	public void setDoseUnit(String doseUnit) {
		this.doseUnit = doseUnit;
	}

	@Column(name = "INPUT_CODE")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "DRUG_CODE")
	public String getDrugcode() {
		return drugcode;
	}

	public void setDrugcode(String drugcode) {
		this.drugcode = drugcode;
	}

	@Column(name = "DRUG_INDICATOR")
	public float getIndicator() {
		return this.indicator;
	}

	public void setIndicator(float indicator) {
		this.indicator = indicator;
	}

	@Column(name = "AMOUNT")
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(name = "NEED")
	public int getNeed() {
		return this.need;
	}

	public void setNeed(int need) {
		this.need = need;
	}

	@Column(name = "entrytime")
	public Timestamp getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(Timestamp entrytime) {
		this.entrytime = entrytime;
	}

}
