package com.wpi.cs509.teamA.persistence.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.persistence.bean.OtherFeature;

public interface OtherFeatureDao {
	public List<OtherFeature> getAllOtherFeatures();	
	public List<String> getAllFeatureLabels();
	public List<Node> getListofNodesWithLabel(String newLabel);
	public void saveAllOtherFeatures(OtherFeature otherFeature);
}
