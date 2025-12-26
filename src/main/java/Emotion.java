public enum Emotion {
    HAPPY, SAD, ANGRY, NEUTRAL, FLUSTERED, BLUSHING, SCARED;
	
	public static Emotion fromString(String s) {
		if(s == null) return NEUTRAL;	

		return switch(s.toLowerCase()) {
			case "happy" -> HAPPY;
			case "sad" -> SAD;
			case "angry" -> ANGRY;
			case "neutral" -> NEUTRAL;
			case "flustered" -> FLUSTERED;
			case "blushing" -> BLUSHING;
			case "scared" -> SCARED;
			default -> NEUTRAL;
		};
	}
}

