package com.wpi.cs509.teamA.persistence.dao;

import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.persistence.bean.GeneralMap;
import com.wpi.cs509.teamA.persistence.bean.Major;
import com.wpi.cs509.teamA.persistence.bean.Professor;

public interface ProfessorDao {

	/**
	 * get all professors in the database
	 * 
	 * @return a set of maps
	 * 
	 */
	public List<Professor> getAllProfessors();
	public void saveProfessor(Professor professor); 
}
