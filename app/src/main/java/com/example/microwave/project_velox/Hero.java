package com.example.microwave.project_velox;
public class Hero extends Character{

	private int level;
	private int rank;
	private int exp;
	private int rankExp;

	private int paceCapacity;
	private int reqExp;
	private int reqRankExp;

	private Item helmet;
	private Item armor;
	private Item weapon;
	private Item sheild;
	private double topSpeed;

	public Hero(int clas) {
		topSpeed=5;

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

//	public void getTopSpeed(){
//		//run activity called hero initializer 
//		// ask to pick class Warrior(1), Mage(2), Thief(3)
//		// press button to run getTopSpeed then call display stats
//		topSpeed=phone.getVelocity();
//	}

	public void spawn(){

	}

	public void gainRankExp(int i){
		rankExp=rankExp+1;
	}

	public void rankUp(){
		if(rankExp>=Math.pow(rank, 1.1)*10){
			rank++;
			rankExp=(int) (rankExp-Math.pow(rank, 1.1)*10);
		}
	}
	public void gainExp(int i){
		exp=exp+i;
	}

	public void levelUp(){
		if(exp>=Math.pow(level, 1.1)*10){
			level++;
			maxHp=(int) (maxHp*.99)+200;
			maxRcv=(int) (maxRcv*.99)+100;
			maxAtk=(int) (maxAtk*.99)+200;
			maxMag=(int) (maxMag*.99)+200;

			exp=(int) (exp-Math.pow(level, 1.1)*10);
		}
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
		return helmet.getMag()+ armor.getMag()+weapon.getMag()+sheild.getMag();
	}

	public int getPaceCapacity(){
		return (int) Math.pow(rank, 1.3)*rank+10;
	}
	public int getReqExp(){
		return (int) Math.pow(level, 1.1)*10;
	}
	public int getReqRankExp(){
		return (int) Math.pow(rank, 1.1)*10;
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
