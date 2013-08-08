package page55;

public class MallardDuck extends Duck{
	public MallardDuck(){
		flyBehaviour=new FlyWithWings();
		quackBehaviour=new Quack();
	}

	@Override
	public void diaply() {
        System.out.println("I'm a real MallardDuck");		
	
	}
	
	
	

}
