package com.wpi.cs509.teamA.dao;

import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Major;
import com.wpi.cs509.teamA.bean.Professor;

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
