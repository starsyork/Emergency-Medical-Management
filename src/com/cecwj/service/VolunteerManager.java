package com.cecwj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cecwj.dao.VolunteerDao;
import com.cecwj.model.serialize.JsonBase;
import com.cecwj.model.serialize.VolunteerInfo;
import com.cecwj.model.User;
import com.cecwj.model.Volunteer;

import javax.annotation.Resource;

@Component("volunteerManager")
public class VolunteerManager {
	private VolunteerDao volunteerDao;

	public VolunteerDao getVolunteerDao() {
		return this.volunteerDao;
	}

	@Resource
	public void setVolunteerDao(VolunteerDao volunteerDao) {
		this.volunteerDao = volunteerDao;
	}

	public Volunteer getvolunteerById(int id) {
		return this.volunteerDao.getvolunteerById(id);
	}

	public Volunteer getvolunteerByIdA(int id) {
		return this.volunteerDao.getvolunteerByIdA(id);
	}

	public Volunteer getvolunteerByName(int vid) {
		return this.volunteerDao.getvolunteerByVid(vid);
	}

	public List<Volunteer> getAllVolunteer() {
		List<Volunteer> volunteer = this.volunteerDao.getAllVolunteer();
		return volunteer;
	}
	public List<Volunteer> getundeVolunteer() {
		List<Volunteer> volunteer = this.volunteerDao.getunderVolunteer();
		return volunteer;
	}
	public List<Volunteer>  getAllVolunteerA(JsonBase result) {
		List<Volunteer> volunteers = this.volunteerDao.getAllVolunteer();
		return volunteers;
	}

	public void add(Volunteer volunteer) {

		this.volunteerDao.add(volunteer);
	}

	public void delete(Volunteer volunteer) {

		this.volunteerDao.delete(volunteer);

	}

	public void update(Volunteer volunteer) {
		// TODO Auto-generated method stub
		this.volunteerDao.update(volunteer);

	}

}
