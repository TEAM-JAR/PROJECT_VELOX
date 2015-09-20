package com.example.microwave.project_velox;

public class Character {
	private String name;
	
	protected int maxHp;
	protected int maxRcv;
	protected int maxAtk;
	protected int maxMag;
	
	protected int currHp;
	protected int currRcv;
	protected int currAtk;
	protected int currMag;
	
	private boolean enchantments[]= new boolean[3];
	
	public void displayStats(){
		
	}
	
	public int getMaxHp(){
		return this.maxHp;
	}
	public int getMaxRcv(){
		return this.maxRcv;
	}
	public int getMaxAtk(){
		return this.maxAtk;
	}
	public int getMaxMag(){
		return this.maxMag;
	}
	public int getCurrHp(){
		return this.currHp;
	}
	public int getCurrRcv(){
		return this.currRcv;
	}
	public int getCurrAtk(){
		return this.currAtk;
	}
	public int getCurrMag(){
		return this.currMag;
	}
	
	public String getMaxName(){
		return this.name;
	}
	
	public void attack(Character c){
		c.currHp=c.currHp-this.currAtk;
	}
	
	public void magic(Character c){
		
	}
	
	public void defend(){
		this.enchantments[0]=true;
		currHp=currHp*2;
	}
	
	public void dropEnchantments(){
		if(this.enchantments[0]=true){
			this.enchantments[0]=false;
			currHp=currHp/2+1;
		}
	}
	
	public void heal(int Rcv){
		if(currHp+Rcv>maxHp){
			currHp=maxHp;
		}
		else{
			currHp=currHp+Rcv;
		}
	}
}
