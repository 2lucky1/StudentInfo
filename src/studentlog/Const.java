package studentlog;

public enum Const {
	PLUGIN_ID("studentlog.views.students"),
	BUNDLE_ID("studentlog");
	
	private String value;

	private Const(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
