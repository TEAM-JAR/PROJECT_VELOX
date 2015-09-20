
public class Hero extends Character{
	


	private Item helmet;
	private Item armor;
	private Item weapon;
	private Item sheild;

	public Hero(int clas) {
		double topSpeed = 0;

		// tell user to start running and record the top speed within the interval and save that to topSpeed

		if(clas==1){
			maxHp=(int) (Math.random()*(topSpeed/4)*50+1000);
			maxRcv=(int) (Math.random()*(topSpeed/4)*15+150);
			maxAtk=(int) (Math.random()*(topSpeed/4)*10+100);
			maxMag=(int) (Math.random()*(topSpeed/4)*5+35);
		}
		else if(clas==2){
			maxHp=(int) (Math.random()*(topSpeed/4)*50+725);
			maxRcv=(int) (Math.random()*(topSpeed/4)*15+300);
			maxAtk=(int) (Math.random()*(topSpeed/4)*10+50);
			maxMag=(int) (Math.random()*(topSpeed/4)*5+70);
		}
		else{
			maxHp=(int) (Math.random()*(topSpeed/4)*50+600);
			maxRcv=(int) (Math.random()*(topSpeed/4)*15+150);
			maxAtk=(int) (Math.random()*(topSpeed/4)*10+125);
			maxMag=(int) (Math.random()*(topSpeed/4)*5+50);
		}
	}
	
	public void askName(){

	}
	
	

	public int itemsHp(){
		return helmet.getHp()+armor.getHp()+weapon.getHp()+sheild.getHp();
	}
	public int itemsRcv(){
		return helmet.getRcv()+armor.getRcv()+weapon.getRcv()+sheild.getRcv();
	}
	public int itemsAtk(){
		return helmet.getAtk()+armor.getAtk()+weapon.getAtk()+sheild.getAtk();
	}
	public int itemsMag(){
		return helmet.getMag()+armor.getMag()+weapon.getMag()+sheild.getMag();
	}

	public int totalHp(){
		return itemsHp()+getMaxHp();
	}
	public int totalRcv(){
		return itemsRcv()+getMaxRcv();
	}
	public int totalAtk(){
		return itemsAtk()+getMaxAtk();
	}
	public int totalMag(){
		return itemsMag()+getMaxMag();
	}
	
}
