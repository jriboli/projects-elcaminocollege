package com.promineotech.person.service;

import com.promineotech.person.dao.PersonDao;

public class PersonService {
	private PersonDao personDao = new PersonDao(); 
	
	public void createPersonTable() {
		personDao.createPersonTable();
		
	}

	public void dropPersonTable() {
		personDao.dropPersonTable();
		
	}

	public void addPerson(String name) {
		personDao.addPerson(name);
	}

	public Object findName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
