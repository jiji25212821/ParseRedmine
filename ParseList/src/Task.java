import java.io.OutputStreamWriter;

import org.apache.poi.util.TempFile;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Task {
	private String require;
	private String document;
	private String gameDesigner;
	private String serverCoder;
	private String clientCoder;
	private String qualityController;
	private String artist;
	
	private boolean isServerSure;
	private boolean isClientSure;
	private boolean isArtistSure;
	
	private boolean isWeekTask;
	
	private static String qualityControllerDefault = "韦泽敏";
	public Task() {
	}
	
	public void requireAddContent(String content) {
		if(require == null) {
			require = content;
		} else {
			require = require + content;
		}
	}
	
	public void documentAddContent(String content) {
		if(document == null) {
			document = content;
		} else {
			document = document + content;
		}
	}
	
	public void gameDesignerAddContent(String content) {
		if(gameDesigner == null) {
			gameDesigner = content;
		} else {
			gameDesigner = gameDesigner + content;
		}
	}
	
	public void serverCoderAddContent(String content) {
		if(serverCoder == null) {
			serverCoder = content;
		} else {
			serverCoder = serverCoder + content;
		}
	}
	
	public void clientCoderAddContent(String content) {
		if(clientCoder == null) {
			clientCoder = content;
		} else {
			clientCoder = clientCoder + content;
		}
	}
	
	public void qualityControlerAddContent(String content) {
		if(qualityController == null) {
			qualityController = content;
		} else {
			qualityController = qualityController + content;
		}
	}
	
	public void artistControlerAddContent(String content) {
		if(artist == null) {
			artist = content;
		} else {
			artist = artist + content;
		}
	}
	
	public void setServerSure(boolean isSure) {
		isServerSure = isSure;
	}
	
	public void setClientSure(boolean isSure) {
		isClientSure = isSure;
	}
	
	public void setArtistSure(boolean isSure) {
		isArtistSure = isSure;
	}
	
	
	
	public void display() {
		if(require != null) {
			System.out.println(require);
		}
		
		if(document != null) {
			System.out.println(document);
		}
		
		if(gameDesigner != null) {
			System.out.println(gameDesigner);
		}
		
		if(serverCoder != null) {
			System.out.println(serverCoder);
		}
		
		if(clientCoder != null) {
			System.out.println(clientCoder);
		}
		
		if(qualityController != null) {
			System.out.println(qualityController);
		}
		
		if(artist != null) {
			System.out.println(artist);
		}
		
		System.out.println(isServerSure);
		System.out.println(isClientSure);
		System.out.println(isArtistSure);
	}
	
	public void writeFile(OutputStreamWriter writer) throws Exception {
		if(require != null) {
			writer.write(require + "\n");
		}
		
		if(document != null) {
			writer.write(document + "\n");
		}
		
		if(gameDesigner != null) {
			writer.write(gameDesigner + "\n");
		}
		
		if(serverCoder != null) {
			writer.write(serverCoder + "\n");
		}
		
		if(clientCoder != null) {
			writer.write(clientCoder + "\n");
		}
		
		if(qualityController != null) {
			writer.write(qualityController + "\n");
		}
		
		if(artist != null) {
			writer.write(artist + "\n");
		}
	}
	
	public void writeFile(XWPFDocument document) throws Exception {
		if(require != null) {
			XWPFParagraph paragraph = document.createParagraph();
			String[] tStrings = require.split("【");
			String[] tStrings2 = tStrings[1].split("】");
			
			XWPFRun run = paragraph.createRun();
			run.setText(tStrings[0] + "【");
			
			XWPFRun run2 = paragraph.createRun();
			run2.setText(tStrings2[0]);
			if(isWeekTask) {
				run2.setColor("00FF00");
			} else {
				run2.setColor("FF0000");
			}
			
			XWPFRun run3 = paragraph.createRun();
			String tmp = require.replaceFirst("维护需求：【", "");
			tmp = tmp.replaceFirst(tStrings2[0], "");
			run3.setText(tmp);
			
		}
		
		if(this.document != null) {
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText((this.document));
		}
		
		if(gameDesigner != null) {
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText((gameDesigner));
		}
		
		if(serverCoder != null) {
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText((serverCoder));
			if(isServerSure) {
				run.setColor("00FF00");
			} else {
				run.setColor("FF0000");
			}
		}
		
		if(clientCoder != null) {
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText((clientCoder));
			if(isClientSure) {
				run.setColor("00FF00");
			} else {
				run.setColor("FF0000");
			}
		}
		
		if(qualityController != null) {
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText((qualityController));
		}
		
		if(artist != null) {
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText((artist));
			if(isArtistSure) {
				run.setColor("00FF00");
			} else {
				run.setColor("FF0000");
			}
		}
		
		XWPFParagraph paragraph2 = document.createParagraph();
		XWPFRun run2 = paragraph2.createRun();
		run2.setText((""));
		
		
	}
	
	public boolean isProgramTaskSure() {
		if(isServerSure && isClientSure) {
			if(isServerSure && serverCoder != null && serverCoder.equals("服务端负责人：")) {
				serverCoder = null;
			}
			if(isClientSure && clientCoder != null && clientCoder.equals("客户端负责人：")) {
				clientCoder = null;
			}
			return true;
		} else if(isServerSure && clientCoder == null) {
			return true;
		} else if(isServerSure && clientCoder != null && clientCoder.equals("客户端负责人：")) {
			clientCoder = null;
			return true;
		} else if(isClientSure && serverCoder == null) {
			return true;
		} else if(isClientSure && serverCoder != null && serverCoder.equals("服务端负责人：")) {
			serverCoder = null;
			return true;
		}
		return false;
	}
	
	public boolean isArtTaskSure() {
		return isArtistSure;
	}
	
	public void qualityControlerAdd() {
		if(qualityController != null && qualityController.equals("测试负责人：")) {
			qualityController = qualityController + qualityControllerDefault;
		}
	}
	
	public String getRequire() {
		return require;
	}
	
	public void documentAdd() {
		if(document == null) {
			document = "文档地址：无需文档";
		} else if(document.equals("文档地址：")) {
			document += "无需文档";
		}
	}
	
	public boolean isWeekTask() {
		String[] tStrings = require.split("【");
		String[] tStrings2 = tStrings[1].split("】");

		if(tStrings2[0].contains(ParseDocument.Date)) {
			isWeekTask = true;
			return true;
		}
		
		isWeekTask = false;
		return false;
	}
	
	public boolean getIsServer() {
		return isServerSure;
	}
	
	public boolean getIsClient() {
		return isClientSure;
	}
	
	public boolean isDesigner() {
		if(serverCoder == null && clientCoder == null && artist == null) {
			return true;
		}
		return false;
	}
}
