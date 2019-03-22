package week_2;

class Protoss extends Unit {
	boolean fly;
	public Protoss(String n, boolean b){
		name = n;
		fly = b;
	}

	@Override
	public void encEnergy() {
		energy += 10;
	}

	public void decEnergy(){
		energy--;
	}
}
