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
		
		if(text.contains("维护需求：")) {
			return Type.Require;
		}
		
		if(text.contains("文档地址：")) {
			return Type.Document;
		}
		
		if(text.contains("策划负责人：")) {
			return Type.Designer;
		}
		
		if(text.contains("服务端负责人：")) {
			return Type.Server;
		}
		
		if(text.contains("客户端负责人：")) {
			return Type.Client;
		}
		
		if(text.contains("美术负责人：")) {
			return Type.Artist;
		}
		
		if(text.contains("测试负责人：")) {
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
