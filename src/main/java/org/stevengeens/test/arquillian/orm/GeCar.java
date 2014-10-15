package org.stevengeens.test.arquillian.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@NamedQueries({
		@NamedQuery(
				name=GeCar.findAllCars,
				query="SELECT car FROM GeCar car"
			)
})

@Entity
@Table(name="GE_CAR")
@SequenceGenerator(name="SEQ_CAR", sequenceName="SEQ_CAR", allocationSize=1, initialValue=1)
public class GeCar {
	
	public final static String findAllCars = "findAllCars";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CAR")
	@Column(name="CAR_ID")
	private Long uid;

	@Column(name="CAR_CODE")
	private String code;

	@Column(name="CAR_DESC")
	private String description;
	
	@Column(name="CAR_VALID_YN")
	private String validYn;

	@Version
	@Column(name="CAR_OLV")
	private Long version;
	
	public GeCar() {
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValidYn() {
		return validYn;
	}

	public void setValidYn(String validYn) {
		this.validYn = validYn;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
