package week_2;

abstract class Unit{
	protected String name;
	protected int energy=100;
	abstract public void decEnergy();
	abstract public void encEnergy();
	public int getEnergy(){
		return energy;
	}
}