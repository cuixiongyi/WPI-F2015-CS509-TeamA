package com.wpi.cs509.teamA.bean;

import java.util.ArrayList;
import java.util.List;

import com.wpi.cs509.teamA.util.Database;

public class NodeInformation {

	private Node node;
	private List<Major> major;
	private List<Professor> professor;
	private List<OtherFeature> labels;
	private List<Activity> activities;
	String breakLine= "<br>";
	String bold="<b>";
	String cbold="</b>";
	
	public NodeInformation(Node node, List<Major> major, List<Professor> professor,
			List<OtherFeature> labels, List<Activity> activities){
		this.node = node;
		this.major = major;
		this.professor = professor;
		this.labels = labels;
		this.activities = activities;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public List<Major> getMajor() {
		return major;
	}

	public void setMajor(List<Major> major) {
		this.major = major;
	}

	public List<Professor> getProfessor() {
		return professor;
	}

	public void setProfessor(List<Professor> professor) {
		this.professor = professor;
	}

	public List<OtherFeature> getLabels() {
		return labels;
	}

	public void setLabels(List<OtherFeature> labels) {
		this.labels = labels;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	public String PrintNodeInfo(){
		String line = "";
		line = line+bold+"Node name: "+cbold+ this.node.getName() +breakLine+bold+
				"Map name: "+cbold+ this.node.getMap().getMapName()+breakLine;
		
		//print majors
		if((major!=null) && (major.size()!=0)){
			for(Major m: major){
				line = line+bold+"Major: "+cbold+ m.getMajorName()+breakLine;
		//		listOfString.add("Major");
		//		listOfString.add(m.getMajorName());
			}
		}
		// print professors
		if((professor!=null)&&(professor.size()!=0)){
			for(Professor p:professor){
				line = line+bold+"Professor: "+cbold+ p.getProfessorName()+breakLine;
		//		listOfString.add("Professor");
		//		listOfString.add(p.getProfessorName());
			}
		}
		//print labels
		if((labels!=null)&&(labels.size()!=0)){
			for(OtherFeature ll:labels){
				line = line+bold+"Labels: "+cbold+ ll.getFeatureLabel()+breakLine;
		//		listOfString.add("Labels");
		//		listOfString.add(ll.getFeatureLabel());
			}
		}
		//print activities
		if((activities!=null)&&(activities.size()!=0)){
			for(Activity aa:activities){
				line = line+bold+"Activity desc: "+cbold+ aa.getActivityName()+breakLine;
		//		listOfString.add("Activity desc");
		//		listOfString.add(aa.getActivityName());
			}
		}
		return line;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database.InitFromDatabase();
		System.out.println(Database.getNodeInformation(53).PrintNodeInfo());
	}

}
