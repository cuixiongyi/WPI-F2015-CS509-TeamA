package com.wpi.cs509.teamA.dao;

import java.util.Set;

import com.wpi.cs509.teamA.bean.Edge;

public interface EdgeDao {

	public boolean insertoneEdge(int x1, int y1, int x2, int y2);
	
	public Set<Edge> initAllEdges();
	
}
