package studentlog;

public enum ImageKeys {
	LOGO("resources/icons/logo.png"),
	CLOSED_FOLDER("resources/icons/tree/folder.png"),
	OPENED_FOLDER(""),
	STUDENT_ICON("resources/icons/tree/head.png"),
	DEFAULT_STUDENT_IMAGE(""),
	PLUS("resources/icons/toolbar/plus.png"),
	FLLOPPY("resources/icons/menubar/floppy.png"),
	BASKET("resources/icons/menubar/basket16x16.png");
	
	private String filePath;
	
	private ImageKeys(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}
}
