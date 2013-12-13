package com.projectalpha.map;

import java.util.ArrayList;
import java.util.Stack;

public class Map  extends GlobalMap{
	private int countOfFillingPoints;
	public Stack<Integer> counterPoint = new Stack<Integer>();
	public Map(int countOfPoints) {
		super(countOfPoints);
	}
	

}
