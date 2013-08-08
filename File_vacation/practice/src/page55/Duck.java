package page55;

public abstract class Duck {
	FlyBehaviour flyBehaviour;
	QuackBehaviour quackBehaviour;
	
	public Duck(){
		
	}
	
	public abstract void diaply();
	
	public void performFly(){
		flyBehaviour.fly();
	}
	
	public void performQuack(){
		quackBehaviour.quack();
		
	}
	
	public void swim(){
        System.out.println("All ducks swim!");		

	}
	
	public void setFlyBehaviour(FlyBehaviour fb){
		flyBehaviour=fb;
	}
	public void setQuackBehaviour(QuackBehaviour qb){
		quackBehaviour=qb;
	}

}
