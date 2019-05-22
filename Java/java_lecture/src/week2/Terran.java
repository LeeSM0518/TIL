package week2;

class Terran extends Unit{
	boolean fly;
	public Terran(String n, boolean b){
		name = n;
		fly = b;
	}

	@Override
	public void encEnergy() {
		energy += 30;
	}

	public void decEnergy(){
		energy -= 3;
	}
}
