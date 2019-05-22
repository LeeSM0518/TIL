package week2;
class Zerg extends Unit{
	boolean fly;
	public Zerg(String n, boolean b){
		name = n;
		fly = b;
	}

	@Override
	public void encEnergy() {
		energy += 60;
	}

	public void decEnergy(){
		energy -= 6;
	}
}
