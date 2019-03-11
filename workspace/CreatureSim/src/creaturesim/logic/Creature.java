package creaturesim.logic;

import java.awt.Color;
import java.util.ArrayList;

import creaturesim.neural.HardlimNode;
import creaturesim.neural.SigmoidInputNode;
import creaturesim.neural.SigmoidNode;
import creaturesim.neural.TanhNode;

public class Creature{
	
	double x = Math.random()*CompetitionManager.spawn_area.width + CompetitionManager.spawn_area.x;
	double y = Math.random()*CompetitionManager.spawn_area.height + CompetitionManager.spawn_area.y;
	double bearing = Math.random()*2*Math.PI - Math.PI;
	double radius = 1;
	double movement_speed = 0.01/radius;
	
	Color color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
	
	double rotation_next_tick;
	boolean movement_next_tick;
	
	boolean alive = true;
	
	SigmoidInputNode i0 = new SigmoidInputNode(); // Bearing
	SigmoidInputNode i1 = new SigmoidInputNode(); // Distance to object
	SigmoidInputNode i2 = new SigmoidInputNode(); // Size of object
	SigmoidInputNode i3 = new SigmoidInputNode(); // Radius
	SigmoidInputNode i4 = new SigmoidInputNode(); // Movement Speed
	SigmoidInputNode[] inputs = new SigmoidInputNode[]{i0, i1, i2, i3, i4};
	SigmoidNode h0 = new SigmoidNode(inputs);
	SigmoidNode h1 = new SigmoidNode(inputs);
	SigmoidNode h2 = new SigmoidNode(inputs);
	SigmoidNode h3 = new SigmoidNode(inputs);
	SigmoidNode[] hiddens = new SigmoidNode[]{h0, h1, h2, h3};
	TanhNode turning = new TanhNode(hiddens);
	HardlimNode movement = new HardlimNode(hiddens);
	
	public void tick(){
		move();
		loseEnergy();
		runThroughNetwork();
	}
	
	public void loseEnergy(){
		radius -= 0.0001;
		movement_speed = 0.01/radius;
		if(radius < 0.5){
			die();
		}
	}
	
	public void move(){
		bearing += rotation_next_tick;
		if(movement_next_tick){
			x += movement_speed*Math.cos(bearing);
			y += movement_speed*Math.sin(bearing);
		}
		else{
			x -= movement_speed*Math.cos(bearing);
			y -= movement_speed*Math.sin(bearing);
		}
		ArrayList<FoodPellet> food = CompetitionManager.food;
		for(int i = 0; i < food.size(); i++){
			if(distanceTo(food.get(i)) < radius){
				eat(food.get(i));
			}
		}
	}
	
	public void runThroughNetwork(){
		i0.giveValue(bearing);
		i1.giveValue(100);
		i2.giveValue(100);
		i3.giveValue(radius);
		i4.giveValue(movement_speed);
		movement_next_tick = movement.getOutput() == 1;
		rotation_next_tick = turning.getOutput()/100;
	}
	
	void eat(FoodPellet food){
		radius += food.getRadius();
		CompetitionManager.removeFood(food);
	}
	
	public void die(){
		alive = false;
		CompetitionManager.kill(this);
	}
	
	double distanceTo(FoodPellet target){
		return(Math.hypot(target.getX() - x, target.getY() - y));
	}
	
	public boolean isAlive(){
		return(alive);
	}
	public double getX(){
		return(x);
	}
	public double getY(){
		return(y);
	}
	public double getRadius(){
		return(radius);
	}
	public double getBearing(){
		return(bearing);
	}
	public Color getColor(){
		return(color);
	}
}
