package week2;
class UnitTest
{
	public static void main(String[] args) 
	{
		Zerg z1 = new Zerg("Hydralisk",false);
		z1.encEnergy();
		System.out.println("z1의 Energy : "+z1.getEnergy());
		Protoss p1 = new Protoss("Corsair",true);
		p1.encEnergy();
		System.out.println("p1의 Energy : "+p1.getEnergy());
		Terran t1 = new Terran("Marine",false);
		t1.encEnergy();
		System.out.println("t1의 Energy : "+t1.getEnergy());
	}
}