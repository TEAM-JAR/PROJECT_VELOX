package com.example.microwave.project_velox;
public class Dungeon {
	private int numStages;
	private int currStage;
	private int difficulty;
	private Hero hero;
	private Pedometer p;
	private VelocityUpdater vu;
	
	private BattleScript newBattle;

	public Dungeon(int difficulty, int numStages, Hero hero, Pedometer p, VelocityUpdater vu){
		this.difficulty=difficulty;
		this.numStages=numStages;
		this.hero=hero;
		this.p=p;
		this.vu=vu;
	}

	public boolean canAdvance(){
		if(currStage<=numStages){
			return true;
		}
		else{
			return false;
		}
	}

	public void start(){
		currStage=1;
		newBattle = new BattleScript(difficulty,(int)(Math.random()*3)+1,hero,p, vu);
	}

	public void next(){
		if (newBattle.getResult()==true){
			this.advance();
		}
		else{
			this.gameover();
		}
	}
	
	public void advance(){
		if(canAdvance()){
			currStage=currStage+1;
			newBattle = new BattleScript(difficulty,(int)(Math.random()*3)+1,hero,p, vu);
		}
		else{
			
			//display win screen aka cut to new activity where rank exp and exp are earned are displayed
			//and there is an okay button takes you to menu
		}
	}

	public void gameover(){
		//display lose screen aka cut to new activity where an okay button takes you to menu
	}
}
