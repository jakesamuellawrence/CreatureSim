package creaturesim.logic;

public class Generation{
	
	public Creature[] creatures = new Creature[CompetitionManager.generation_size];
	
	public Generation(){
		for(int i = 0; i < creatures.length; i++){
			creatures[i] = new Creature();
		}
	}
	public Generation(Generation previous){
		
	}
	
	void tick(){
		for(int i = 0; i < creatures.length; i++){
			if(creatures[i].isAlive()){
				creatures[i].tick();
			}
		}
	}
	
	public Creature[] getCreatures(){
		return(creatures);
	}
}
