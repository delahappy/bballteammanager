package com.beardy.bballteammanager.beans;

public class Player {
	private String id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String position;
	private String parentName1;
	private String parentPhone1;
	private String parentEmail1;
	private String parentName2;
	private String parentPhone2;
	private String parentEmail2;
	private String jersey;
	private String teamId;
	
	@Override
	public String toString() {
		return firstName + " " + lastName + ": " + position;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getParentName1() {
		return parentName1;
	}
	public void setParentName1(String parentName1) {
		this.parentName1 = parentName1;
	}
	public String getParentPhone1() {
		return parentPhone1;
	}
	public void setParentPhone1(String parentPhone1) {
		this.parentPhone1 = parentPhone1;
	}
	public String getParentEmail1() {
		return parentEmail1;
	}
	public void setParentEmail1(String parentEmail1) {
		this.parentEmail1 = parentEmail1;
	}
	public String getParentName2() {
		return parentName2;
	}
	public void setParentName2(String parentName2) {
		this.parentName2 = parentName2;
	}
	public String getParentPhone2() {
		return parentPhone2;
	}
	public void setParentPhone2(String parentPhone2) {
		this.parentPhone2 = parentPhone2;
	}
	public String getParentEmail2() {
		return parentEmail2;
	}
	public void setParentEmail2(String parentEmail2) {
		this.parentEmail2 = parentEmail2;
	}
	public String getJersey() {
		return jersey;
	}
	public void setJersey(String jersey) {
		this.jersey = jersey;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	

}
