package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RoutesList {
	
	private RouteNode firstRouteNode;
	
	public RoutesList() {
		firstRouteNode=null;
	}

	public void add(String route, boolean type, String text){
		LocalDateTime time= LocalDateTime.now();
		RouteNode node= new RouteNode(route,time, type, text);
		if(firstRouteNode!=null) {
			add(firstRouteNode, node);
		}else {
			firstRouteNode=node;
		}
	}
	
	private void add(RouteNode current, RouteNode node) {
		if(current.getNext()!=null) {
			add(current.getNext(),node);
		}else {
			current.setNext(node);
			current.getNext().setPrev(current);
		}
	}
	
	public String search(String route) {
	if(firstRouteNode!=null) {
		return search(firstRouteNode, route);
	}
	return null;
	}
	
	private String search(RouteNode current, String route) {
		if(current.getRoute().equals(route)) {
			return current.getText();
		}else{
			if(current.getNext()!=null)
			search(current.getNext(), route);
		}
		return null;
	}
	
	public RouteNode delete(String route) {
		if(firstRouteNode!=null) {
			return delete(firstRouteNode, route);
		}else {
			return null;
		}
	}
	
	private RouteNode delete(RouteNode current, String route) {
		if(current.getRoute().equals(route) ) {

			//////When current is the only node 
			if(current.getNext()==null && current.getPrev()==null) {
				firstRouteNode=null;
				
			/////When current is the first node	
			}else if(current.getPrev()==null) {
				current.getNext().setPrev(null);
				
			/////When current is the last one 
			}else if(current.getNext()==null){
				current.getPrev().setNext(null);
				
			/////When current is in the middle
			}else {
				current.getPrev().setNext(current.getNext());
				current.getNext().setPrev(current.getPrev());
			}
			
			return current;
		}else {
			if(current.getNext()!=null)
			return delete(current.getNext(), route);
		}
		return null;
	}
	
	
	public ArrayList <RouteNode> getRoutes(boolean type) {
		if(firstRouteNode!=null) {
			ArrayList <RouteNode> list= new ArrayList <RouteNode>();
			return getRoutes(firstRouteNode, type, list);
		}
		return null;
	}
	
	private ArrayList <RouteNode> getRoutes(RouteNode current, boolean type, ArrayList <RouteNode> list){
		if(current.getType()==type) {
			list.add(current);
		}
		
		if(current.getNext()!=null) 
			return getRoutes(current.getNext(), type,list);
			else
				return list;
	}
	
}

