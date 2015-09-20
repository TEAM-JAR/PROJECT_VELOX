
public class Battle {
	private Enemy[] enemies;
	private Hero hero;
	
	Battle (int n, Hero hero){
		Character[] enemies=new Enemy[n];
		this.hero=hero;
	}
	
	public void spawnEnemy(int difficulty){
		Enemy enemy=new Enemy(difficulty);
		int i=0;
		while(enemies[i]!=null){
			if(enemies[i]==null);
		}
		enemies[i]=enemy;
	}
	
	public int isOccuring(){
		int happening=0;
		
		for(int i=0;i<enemies.length;i++){
			if(enemies[i].getCurrHp()<=0){
				happening=1; //you win the stage
			}
		}
		if(hero.getCurrHp()==0){
			happening=2; //you lost the stage 
		}
		return happening;
	}
	
	public Hero getHero(){
		return hero;
	}
	
	public Enemy getEnemy(int i){
		return enemies[i];
	}
	
	public Enemy[] getEnemies(){
		return enemies;
	}
}
