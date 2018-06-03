package studentlog.model;

import java.util.List;

public class StudentsEntry implements ITreeItem {

	private String name;
	private String groupNumber;
	private String address;
	private String city;
	private String result;
	private String imagePath;

	transient private StudentsGroup parent;

	public StudentsEntry() {

	}

	public StudentsEntry(String name, String groupNumber, String address, String city, String result,
			StudentsGroup group) {
		super();
		this.name = name;
		this.groupNumber = groupNumber;
		this.address = address;
		this.city = city;
		this.result = result;
		this.parent = group;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getResult() {
		return result;
	}

	public StudentsGroup getGroup() {
		return parent;
	}

	
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public StudentsGroup getParent() {
		return parent;
	}

	@Override
	public List<?> getChildren() {
		return null;
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setParent(StudentsGroup parent) {
		this.parent = parent;
	}
}
