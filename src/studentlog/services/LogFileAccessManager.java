package studentlog.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;

import studentlog.model.Folder;
import studentlog.model.Root;
import studentlog.model.StudentsGroup;

public class LogFileAccessManager {

	public LogFileAccessManager() {
		super();
	}

	public void writeLogItemsToFile(String logFilePath, Root root) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(root);
		System.out.println("Jason string " + jsonStr);
		try (Writer writer = new FileWriter(logFilePath)) {
			writer.write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Root readLogItemsFromFile(String logFilePath) {
		String jsonStr = null;
		try (Reader reader = new FileReader(logFilePath)) {
			BufferedReader bufferedReader = new BufferedReader(reader);
			jsonStr = bufferedReader.readLine();
			Root root = new Gson().fromJson(jsonStr, Root.class);
			root.initParent();
			return root;
		} catch (FileNotFoundException e) {
			System.out.println("LogFileAccessor: file doesnt exists");
			return getDefaultLogItems();
		} catch (IOException e) {
			return getDefaultLogItems();
		}
	}

	private Root getDefaultLogItems() {
		Root root = new Root();
		
		Folder folder = new Folder(root, "Folder");
		root.addEntry(folder);

		StudentsGroup firstGroup = new StudentsGroup(folder, "Group1");
		StudentsGroup secondGroup = new StudentsGroup(folder, "Group2");

		folder.addEntry(firstGroup);
		folder.addEntry(secondGroup);

		return root;
	}
}
