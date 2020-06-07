import java.util.ArrayList;
import java.util.Iterator;

public class BasicModel {
	ArrayList<Hero> red;
	ArrayList<Hero> blue;
	Hero redSupport;
	Hero blueSupport;
	
	int round;
	
	public BasicModel() {
		
		
	}
	
	public float getDamage(float src_atk, float src_ah, float dst_hp, float dst_def, float dst_sh, float skill_value) {
		float result = skill_value * (src_atk * src_atk / (src_atk + dst_def)) * (1 + src_ah * 1000/20000) * (1 - dst_sh * 1000/20000);
		return result;
	}
	
	public boolean PVP() {
		while(!isCombatFinish()) {
			dealRound();
			round ++;
		}
		return true;
	}
	
	private boolean isCombatFinish() {
		if(round >= 30) {
			return true;
		}
		return false;
	}
	
	private void dealRound() {
		Iterator<Hero> redIterator = red.iterator();
		Iterator<Hero> blueIterator = blue.iterator();
		//common attack
		while(redIterator.next() != null) {
			
		}
		
		while(blueIterator.next() != null) {
			
		}
		
		//skill attack
		redIterator = red.iterator();
		blueIterator = blue.iterator();
		while(redIterator.next() != null) {
			
		}
		
		while(blueIterator.next() != null) {
			
		}
		
	}
	
	public static void main(String[] args) {
		
	}
	
}
