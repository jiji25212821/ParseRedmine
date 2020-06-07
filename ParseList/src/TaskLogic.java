import org.apache.poi.xwpf.usermodel.XWPFRun;



enum Type {
	Require,
	Document,
	Designer,
	Server,
	Client,
	Artist,
	Qualitier,
}

public class TaskLogic {
	private XWPFRun run;
	private Type lastRunType;
	
	public TaskLogic(XWPFRun run, Type lastRunType) {
		this.run = run;
		this.lastRunType = lastRunType;
	}
	
	public String getText() {
		return run.getText(0);
	}
	
	public Type checkType() {
		String text = getText();
		if(text == null || text.isEmpty()) {
			return null;
		}
		
		if(text.contains("ά������")) {
			return Type.Require;
		}
		
		if(text.contains("�ĵ���ַ��")) {
			return Type.Document;
		}
		
		if(text.contains("�߻������ˣ�")) {
			return Type.Designer;
		}
		
		if(text.contains("����˸����ˣ�")) {
			return Type.Server;
		}
		
		if(text.contains("�ͻ��˸����ˣ�")) {
			return Type.Client;
		}
		
		if(text.contains("���������ˣ�")) {
			return Type.Artist;
		}
		
		if(text.contains("���Ը����ˣ�")) {
			return Type.Qualitier;
		}
		return null;
	}
	
	public Type calcType() {
		Type type = checkType();
		if(type == null && lastRunType == null) {
			return null;
		}
		if(type == null && lastRunType != null) {
			return lastRunType;
		}
		
		lastRunType = type;
		return type;
	}
	
	public boolean isRequireHead() {
		Type type = checkType();
		if(type == Type.Require && (lastRunType == null || lastRunType == Type.Qualitier)) {
			return true;
		}
		return false;
	}
	
	public boolean checkSure() {
		if(run.getColor() == null) {
			return false;
		}
		
		if(run.getColor().equals("000000") || run.getColor().equals("auto")) {
			return false;
		}
		return true;
	}
	
	public Type getLastRunType() {
		return lastRunType;
	}
}
