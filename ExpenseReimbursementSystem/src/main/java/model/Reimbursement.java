package model;

import java.sql.Date;

public class Reimbursement {
	int reiId;
	Double reiAmount;
	Date reiSubmitted;
	Date reiResolved;
	String reiDescription;
	int reiAuthor;
	int reiResolver;
	int reiStatusId;
	int reiTypeId;
	String reiStatus;
	String reiType;
	String resolverFName;
	String resolverLName;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(Double reiAmount, String reiDescription, int reiTypeId) {
		super();
		this.reiAmount = reiAmount;
		this.reiDescription = reiDescription;
		this.reiTypeId = reiTypeId;
	}

	public Reimbursement(int reiId, Double reiAmount, Date reiSubmitted, Date reiResolved, String reiDescription,
			int reiAuthor, int reiResolver, int reiStatusId, int reiTypeId, String reiStatus, String reiType,
			String resolverFName, String resolverLName) {
		super();
		this.reiId = reiId;
		this.reiAmount = reiAmount;
		this.reiSubmitted = reiSubmitted;
		this.reiResolved = reiResolved;
		this.reiDescription = reiDescription;
		this.reiAuthor = reiAuthor;
		this.reiResolver = reiResolver;
		this.reiStatusId = reiStatusId;
		this.reiTypeId = reiTypeId;
		this.reiStatus = reiStatus;
		this.reiType = reiType;
		this.resolverFName = resolverFName;
		this.resolverLName = resolverLName;
	}

	public int getReiId() {
		return reiId;
	}

	public void setReiId(int reiId) {
		this.reiId = reiId;
	}

	public Double getReiAmount() {
		return reiAmount;
	}

	public void setReiAmount(Double reiAmount) {
		this.reiAmount = reiAmount;
	}

	public Date getReiSubmitted() {
		return reiSubmitted;
	}

	public void setReiSubmitted(Date reiSubmitted) {
		this.reiSubmitted = reiSubmitted;
	}

	public Date getReiResolved() {
		return reiResolved;
	}

	public void setReiResolved(Date reiResolved) {
		this.reiResolved = reiResolved;
	}

	public String getReiDescription() {
		return reiDescription;
	}

	public void setReiDescription(String reiDescription) {
		this.reiDescription = reiDescription;
	}

	public int getReiAuthor() {
		return reiAuthor;
	}

	public void setReiAuthor(int reiAuthor) {
		this.reiAuthor = reiAuthor;
	}

	public int getReiResolver() {
		return reiResolver;
	}

	public void setReiResolver(int reiResolver) {
		this.reiResolver = reiResolver;
	}

	public int getReiStatusId() {
		return reiStatusId;
	}

	public void setReiStatusId(int reiStatusId) {
		this.reiStatusId = reiStatusId;
	}

	public int getReiTypeId() {
		return reiTypeId;
	}

	public void setReiTypeId(int reiTypeId) {
		this.reiTypeId = reiTypeId;
	}

	public String getReiStatus() {
		return reiStatus;
	}

	public void setReiStatus(String reiStatus) {
		this.reiStatus = reiStatus;
	}

	public String getReiType() {
		return reiType;
	}

	public void setReiType(String reiType) {
		this.reiType = reiType;
	}

	public String getResolverFName() {
		return resolverFName;
	}

	public void setResolverFName(String resolverFName) {
		this.resolverFName = resolverFName;
	}

	public String getResolverLName() {
		return resolverLName;
	}

	public void setResolverLName(String resolverLName) {
		this.resolverLName = resolverLName;
	}

	@Override
	public String toString() {
		return "[reiId=" + reiId + ", reiAmount=" + reiAmount + ", reiSubmitted=" + reiSubmitted
				+ ", reiResolved=" + reiResolved + ", reiDescription=" + reiDescription + ", reiAuthor=" + reiAuthor
				+ ", reiResolver=" + reiResolver + ", reiStatusId=" + reiStatusId + ", reiTypeId=" + reiTypeId
				+ ", reiStatus=" + reiStatus + ", reiType=" + reiType + ", resolverFName=" + resolverFName
				+ ", resolverLName=" + resolverLName + "]\n";
	}
	
	
	
	
	
	
}
