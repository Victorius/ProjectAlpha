package com.projectalpha.map;

import java.awt.Color;
import java.util.Random;

public class GlobalMap {
	protected int width, height;
	protected int[] map;
	protected final int countOfPoints;
	private Map[] listOfMaps;
	public void randomGenerator(){
		Random random = new Random();
		for(int i=0;i<countOfPoints;i++){			
			map[random.nextInt(map.length)] = i;
		}
	}
	public GlobalMap(int countOfPoints){
		this.countOfPoints=countOfPoints;
	}
	public GlobalMap(int countOfPoints, int width, int height){
		this.countOfPoints=countOfPoints;
		this.height=height;
		this.width=width;
		this.map= new int[width*height];
		this.randomGenerator();
		this.listOfMaps = new Map[countOfPoints];
	}
	

	public void showClassic(){
		for(int y=0;y<10;y++){
			for(int x=0;x<10;x++){
				System.out.print(map[x+y*10]);
			}
			System.out.println();
		}
	}
	
	public void fillMap(){
		
	}
	
	public static void main(String[] args){
		GlobalMap m = new GlobalMap(4, 10,10);
		m.showClassic();
		
	}
	
}
