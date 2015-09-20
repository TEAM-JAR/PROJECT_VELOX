package com.example.microwave.project_velox;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

public class BattleScript {
	
	boolean result;
	BattleAction battleAction;
	ProgressBar pb;
	
	public BattleScript(int difficulty, int numOfEnemies, Hero hero,Pedometer p,VelocityUpdater v, ProgressBar pb) {
		Battle battle=new Battle(numOfEnemies,hero);
		battleAction = BattleAction.STANDBY;

		for(int i=0;i<numOfEnemies;i++) {
			battle.spawnEnemy(difficulty);
		}
		
		while(battle.isOccuring()==0){

			/**
			 * Fight
			 */
			if(battleAction.equals(BattleAction.ATTACK)) {
				double velocity=0;
				
				long start = System.currentTimeMillis();
				long end = start + 3*1000; // 3 seconds * 1000 ms/sec
				while (System.currentTimeMillis() < end)
				{
					int diff = (int) (end - start);
					updateActionBar((diff / 3000) * 100);

					if(v.getVelocity()>velocity){
						velocity=v.getVelocity();
					}
				}

				if (velocity>7){
					//Later integrate user selection of enemy
					battle.getHero().attack(battle.getEnemy((int)( Math.random()*3)+ 1));
				}

				resetBattleAction();
			}

			/**
			 * Magic
			 */
			else if(battleAction.equals(BattleAction.MAGIC)) {

				double velocity=0;
				int stepAt=p.getSensorCount();
				
				long start = System.currentTimeMillis();
				long end = start + 3*1000; // 3 seconds * 1000 ms/sec
				while (System.currentTimeMillis() < end)
				{
					int diff = (int) (end - start);
					updateActionBar((diff / 3000) * 100);

					if(v.getVelocity()>velocity){
						velocity=v.getVelocity();
					}
				}
				
				int stepsTaken=p.getSensorCount()-stepAt;

				if(velocity>4 && stepsTaken==1){
					//Later integrate user selection of enemy
					battle.getHero().attack(battle.getEnemy((int) (Math.random() * 3 )+ 1));
				}

				resetBattleAction();
			}

			/**
			 * Defend
			 */
			else if(battleAction.equals(BattleAction.DEFEND)) {
				double velocity=0;
				int stepAt=p.getSensorCount();
		
				long start = System.currentTimeMillis();
				long end = start + 3*1000; // 3 seconds * 1000 ms/sec
				while (System.currentTimeMillis() < end)
				{
					int diff = (int) (end - start);
					updateActionBar((diff / 3000) * 100);

					if(v.getVelocity()>velocity){
						velocity=v.getVelocity();
					}
				}
				
				int stepsTaken=p.getSensorCount()-stepAt;
				
				if(v.getVelocity()<3 && stepsTaken==0){
					battle.getHero().defend();
				}

				resetBattleAction();
			}

			/**
			 * Heal
			 */
			else if (battleAction.equals(BattleAction.HEAL)) {
				double velocity=0;
				int stepAt=p.getSensorCount();
				
				long start = System.currentTimeMillis();
				long end = start + 3*1000; // 3 seconds * 1000 ms/sec
				while (System.currentTimeMillis() < end)
				{
					int diff = (int) (end - start);
					updateActionBar((diff / 3000) * 100);

					if(v.getVelocity()>velocity){
						velocity=v.getVelocity();
					}
				}
				
				int stepsTaken=p.getSensorCount()-stepAt;
				
				if(velocity<.1 && stepsTaken>0){
					battle.getHero().heal(battle.getHero().getCurrRcv());
				}

				resetBattleAction();
			} else if (battleAction.equals(BattleAction.ESCAPE)) {
				result = false;
			}  else  {
				//Do nothing
			}

			if (!battleAction.equals(BattleAction.STANDBY)) {

				for (int i = 1; i < battle.getEnemies().length; i++) {
					if (battle.getEnemy(i).dies()) {

					}
				}

				for (int i = 1; i < battle.getEnemies().length; i++) {
					battle.getEnemies()[i].performAction(battle.getEnemies()[i].getDifficulty(), battle.getHero());
				}

				battle.getHero().dropEnchantments();
				resetBattleAction();
			} else {
				//Do nothing
			}
		}
		if(battle.isOccuring()==2){
			result=false;
		}
		else if( battle.isOccuring()==1){
			result=true;
		}
	}

	public void resetBattleAction() {
		pb.setProgress(0);
		battleAction = BattleAction.STANDBY;
	}

	public void setBattleAction(BattleAction ba) {
		battleAction = ba;
	}

	public void updateActionBar(int fill) {
		pb.setProgress(fill);
	}

	public boolean getResult(){
		return this.result;
	}
}
