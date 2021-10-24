package entity;

public enum Role {

	ADMIN(0), ATHLETE(1), COACH(2);
	
	private int id;
	
	private Role(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}