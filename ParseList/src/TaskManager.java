import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class TaskManager {
	private ArrayList<Task> taskList;
	
	public TaskManager() {
		taskList = new ArrayList<>();
	}
	
	public void addTask(Task task) {
		taskList.add(task);
	}
	
	public void display() {
		if(taskList == null) {
			System.out.println("------No Task-------");
			return;
		}
		
		for(Task task:taskList) {
			try {
				if(task.isWeekTask() && (task.isProgramTaskSure() || task.isArtTaskSure())) {
					task.documentAdd();
					task.qualityControlerAdd();
					task.isWeekTask();
					task.display();
				}
				else {
					//System.out.println("Unsure Require:" + task.getRequire());
					task.display();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void writeFile() {
		try {
			FileOutputStream txtWriter = new FileOutputStream(new File(ParseDocument.targetFilePath));
			FileOutputStream txtWriter2 = new FileOutputStream(new File(ParseDocument.errorTargetFilePath));
			
			XWPFDocument document = new XWPFDocument();
			XWPFDocument document2 = new XWPFDocument();
			if(taskList == null) {
				XWPFParagraph paragraph = document.createParagraph();
				XWPFRun run = paragraph.createRun();
				run.setText("-------------------------No Content------------------------");
			}
			
			for(Task task:taskList) {
				try {
					if(task.isWeekTask() && (task.isProgramTaskSure() || task.isArtTaskSure()) || task.isDesigner()) {
						task.documentAdd();
						task.qualityControlerAdd();
						
						task.writeFile(document);
						
						System.out.println(task.getRequire() + "\n");
					}
					else {						
						task.documentAdd();
						task.qualityControlerAdd();
						task.writeFile(document2);
						
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			document.write(txtWriter);
			document2.write(txtWriter2);
			
			txtWriter.close();
			txtWriter2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
