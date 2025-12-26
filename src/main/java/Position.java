public enum Position {
    CORNER_LEFT, LEFT, SLIGHTLY_LEFT, CENTER, SLIGHTLY_RIGHT, RIGHT, CORNER_RIGHT;
	
    public static Position fromString(String s) {
        if (s == null) return CENTER;

        return switch(s.toLowerCase()) {
            case "corner_left" -> CORNER_LEFT;
            case "left" -> LEFT;
            case "slightly_left" -> SLIGHTLY_LEFT;
            case "center" -> CENTER;
            case "slightly_right" -> SLIGHTLY_RIGHT;
            case "right" -> RIGHT;
            case "corner_right" -> CORNER_RIGHT;
            default -> CENTER; 
        };
    }
}

