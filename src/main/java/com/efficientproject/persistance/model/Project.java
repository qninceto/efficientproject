package com.efficientproject.persistance.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Project {
	private int id;
	private String name;
	private LocalDate deadline;
	private Organization organization;
	private LocalDate startDate;
	private int daysLeft;
	private int duration;
//	private  Map<TaskState,Integer> tasksNumberPerState=new ConcurrentHashMap<>();
	

	public Project(String name, LocalDate deadline, Organization organization) {
		this.name = name;
		this.deadline = deadline;
		this.organization = organization;
		this.setStartDate(LocalDate.now());

	}

	public Project(int id, String name, LocalDate deadline, Organization organization,LocalDate startDate) {
		this(name, deadline, organization);
		this.id = id;
		this.setStartDate(startDate);
		this.setDaysLeft(deadline);
		this.setDuration(startDate,deadline);
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	private void setDaysLeft(LocalDate deadline) {
		this.daysLeft = (int) ChronoUnit.DAYS.between(LocalDate.now(), deadline);;
	}

	public int getDuration() {
		return duration;
	}

	private void setDuration(LocalDate startDate,LocalDate deadline) {
		this.duration = (int) ChronoUnit.DAYS.between(startDate, deadline);
	}
}
