package com.wpi.cs509.teamA.persistence.dao;

import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.persistence.bean.Major;

public interface MajorDao {
	public List<Major> getAllMajors();	
	public void saveMajors(Major major); 
}
