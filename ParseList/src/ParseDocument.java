import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class ParseDocument {
	public static String srcFilePath;
	
	public static String targetFilePath;
	
	public static String errorTargetFilePath;
	
	public static String Date = "20191009";
	
	
	
	public ParseDocument() {
		
	}
	
	public void run() {
		System.out.println("--------------------------------------Begin-----------------------------------------");
		
		TaskManager taskManager = new TaskManager();
		Task tempTask = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(srcFilePath));
			XWPFDocument xDocument;
			try {
				xDocument = new XWPFDocument(fileInputStream);
				List<XWPFParagraph> paragraphs = xDocument.getParagraphs();
				for(XWPFParagraph paragraph:paragraphs) {
					
					
					List<XWPFRun> runs = paragraph.getRuns();
					Type lastRunType = null;
					for(XWPFRun run:runs) {
						String content = run.getText(0);
						//System.out.println(run.getText(0));
						TaskLogic logic = new TaskLogic(run, lastRunType);
						if(logic.isRequireHead()) {
							if(tempTask != null) {
								taskManager.addTask(tempTask);
							}
							tempTask = new Task();
						}
						Type tempType = logic.calcType();
						if(tempType != null && content != null) {
							switch (tempType) {
							case Require:
								tempTask.requireAddContent(content);
								break;
							case Document:
								tempTask.documentAddContent(content);
								break;
							case Designer:
								tempTask.gameDesignerAddContent(content);
								break;
							case Server:
								tempTask.serverCoderAddContent(content);
								tempTask.setServerSure(logic.checkSure());
								break;
							case Client:
								tempTask.clientCoderAddContent(content);
								tempTask.setClientSure(logic.checkSure());
								break;
							case Artist:
								tempTask.artistControlerAddContent(content);
								tempTask.setArtistSure(logic.checkSure());
								break;
							case Qualitier:
								tempTask.qualityControlerAddContent(content);
								break;

							default:
								break;
							}
						}
						
						lastRunType = logic.getLastRunType();
					}
				}
				taskManager.addTask(tempTask);
				
				
				fileInputStream.close();
				taskManager.writeFile();
				//taskManager.display();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
