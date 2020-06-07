import java.util.ArrayList;

public class Hero {
	private float hp;
	private float atk;
	private float def;
	private float ah;
	private float sh;
	
	private ArrayList<Skill> skills;
	
	public Hero() {
		hp = 16;
		atk = 1;
		def = 1;
		ah = 0;
		sh = 0;
		skills = new ArrayList<>();
	}
	
	public float getHp() {
		return hp;
	}
	
	public float getAtk() {
		return atk;
	}
	
	public float getDef() {
		return def;
	}
	
	public float getAh() {
		return ah;
	}
	
	public float getSh() {
		return sh;
	}
	
	public ArrayList<Skill> getSkills() {
		return skills;
	}
}
