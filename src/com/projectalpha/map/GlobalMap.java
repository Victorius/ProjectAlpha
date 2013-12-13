package com.projectalpha.map;

import java.awt.Color;
import java.util.Random;

public class Map {
	private int width, height;
	private int[] map;
	private final int countOfPoints;
	public void randomGenerator(){
		Random random = new Random();
		for(int i=0;i<countOfPoints;i++){			
			map[random.nextInt(map.length)] = i;
		}
	}
	
	public Map(int countOfPoints, int width, int height){
		this.countOfPoints=countOfPoints;
		this.height=height;
		this.width=width;
		this.map= new int[width*height];
		this.randomGenerator();
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
		Map m = new Map(4, 10,10);
		m.showClassic();
		
	}
	
}
