package page55;

public interface QuackBehaviour {
	public void quack();

}

class Quack implements QuackBehaviour{

	@Override
	public void quack() {
        System.out.println("Quack");		
		
	}
	
}

class MuteQuack implements QuackBehaviour{

	@Override
	public void quack() {
        System.out.println("Silence");		
		
	}
	
}

class Squeak implements QuackBehaviour{

	@Override
	public void quack() {
        System.out.println("Squeak");		
		
	}
	
}
