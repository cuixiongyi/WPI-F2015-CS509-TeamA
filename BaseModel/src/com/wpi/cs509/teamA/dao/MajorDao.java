package com.wpi.cs509.teamA.dao;

import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.Major;

public interface MajorDao {
	public List<Major> getAllMajors();	
	public void saveMajors(Major major); 
}
