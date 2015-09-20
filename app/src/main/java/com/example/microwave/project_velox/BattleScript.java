
public class BattleScript {
	
	boolean result;
	
	public BattleScript(int difficulty, int numOfEnemies) {
		Battle battle=new Battle(numOfEnemies);
		for(int i=0;i<numOfEnemies;i++){
			battle.spawnEnemy(difficulty);
		}
		
		while(battle.isOccuring()==0){
			Enemy selectedEnemy=new Enemy(0);
			if(buttonFight.isPressed()){
				double velocity=0;
				
				long start = System.currentTimeMillis();
				long end = start + 3*1000; // 3 seconds * 1000 ms/sec
				while (System.currentTimeMillis() < end)
				{
					if(phone.getVelocity()>velocity){
						velocity=phone.getVelocity();
					}
				}

				if (velocity>7){
					//prompt user to select enemy
					selectedEnemy= ;
					battle.getHero().attack(selectedEnemy);
				}
			}
			else if(buttonMagic.isPressed()){

				double velocity=0;
				int stepAt=phone.getSteps(); 
				
				long start = System.currentTimeMillis();
				long end = start + 3*1000; // 3 seconds * 1000 ms/sec
				while (System.currentTimeMillis() < end)
				{
					if(phone.getVelocity()>velocity){
						velocity=phone.getVelocity();
					}
				}
				
				int stepsTaken=phone.getSteps()-stepAt; 

				if(velocity>4 && stepsTaken==1){
					//prompt user to select enemy
					selectedEnemy= ;
					battle.getHero().magic(selectedEnemy);
				}
			}
			else if(buttonDefend.isPressed()){
				double velocity=0;
				int stepAt=phone.getSteps(); 
		
				long start = System.currentTimeMillis();
				long end = start + 3*1000; // 3 seconds * 1000 ms/sec
				while (System.currentTimeMillis() < end)
				{
					if(phone.getVelocity()>velocity){
						velocity=phone.getVelocity();
					}
				}
				
				int stepsTaken=phone.getSteps()-stepAt; 
				
				if(phone.distanceTravled()==0 && stepsTaken==0){
					battle.getHero().defend();
				}
			}
			else if(buttonHeal.isPressed()){
				double velocity=0;
				int stepAt=phone.getSteps(); 
				
				long start = System.currentTimeMillis();
				long end = start + 3*1000; // 3 seconds * 1000 ms/sec
				while (System.currentTimeMillis() < end)
				{
					if(phone.getVelocity()>velocity){
						velocity=phone.getVelocity();
					}
				}
				
				int stepsTaken=phone.getSteps()-stepAt; 
				
				if(velocity<.1 && stepsTaken>0){
					battle.getHero().heal(battle.getHero().getCurrRcv());
				}
			}

			for(int i=1;i<battle.getEnemies().length;i++){
				battle.getEnemies()[i].performAction(battle.getEnemies()[i].getDifficulty(),battle.getHero());
			}

			battle.getHero().dropEnchantments();
		}
		if(battle.isOccuring()==2){
			result=false;
		}
		else if( battle.isOccuring()==1){
			result=true;
		}

	}
	public boolean getResult(){
		return this.result;
	}
}
