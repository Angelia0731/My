package page55;

public interface FlyBehaviour {
	
	public void fly();

}

class FlyWithWings implements FlyBehaviour{

	@Override
	public void fly() {
           System.out.println("I'm flying");		
	}
	
}

class FlyNoWay implements FlyBehaviour{

	@Override
	public void fly() {
        System.out.println("I can't flying");		
		
	}
	
}