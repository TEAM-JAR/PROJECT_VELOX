package com.example.microwave.project_velox;
public class Enemy extends Character{

	private int difficulty;
	
	public Enemy(int difficulty){
		this.maxHp=(int) (200*.5*difficulty);
		this.currHp=maxHp;
		this.maxRcv=(int) (50*.5*difficulty);
		this.currRcv=maxRcv;
		this.maxAtk=(int) (100*.5*difficulty);
		this.currAtk=maxAtk;
		this.maxMag=(int) (10*.5*difficulty);
		this.currMag=maxMag;
		
		this.difficulty=difficulty;
		
	}
	
	public void performAction(int difficulty, Hero hero){
		if(difficulty==1){
			this.attack(hero);
		}
	}
	
	public int getDifficulty(){
		return this.difficulty;
	}
}
