package creaturesim.logic;

public class FoodPellet{
	
	double x = Math.random()*CompetitionManager.spawn_area.width + CompetitionManager.spawn_area.x;
	double y = Math.random()*CompetitionManager.spawn_area.height + CompetitionManager.spawn_area.y;
	double radius = 0.2;
	
	public double getX(){
		return(x);
	}
	public double getY(){
		return(y);
	}
	public double getRadius(){
		return(radius);
	}
}
