import java.io.File;;

public class File_System {
	static int dirs = 0;
	static int files = 0;
	static long dirlength = 0;
	static long filelength = 0;
	static String preStr = "";
	File f;
	//this in constructor
	File_System(File f) {
		this.f = f;
	}
	
	public void tree(File f, int level) {
		String preStr = "";
		this.preStr = preStr;
		
		for (int i = 0; i < level; i++) {
			preStr += "\t";
		}
		File[] childs = f.listFiles();
		
		for (int i = 0; i < childs.length; i++) {
			if (childs[i].isDirectory()) {
				dirs++;
			}
			else {
				files++;
				filelength = childs[i].length();//file.length() 返回表示此抽象路径名的文件的长度，以字节为单位
				dirlength += childs[i].length();
			}
			
			if (!childs[i].isDirectory()) {
				System.out.println(preStr + childs[i].getName());
				System.out.println(preStr + "------------------------");
			}
			
			if (childs[i].isDirectory()) {
				tree(childs[i], level + 1);
			}
		}
	}
	
	public static void main(String[] args) {
		File f = new File("/media/yeyuan/Windows/Users/lenovo/source");
		
		File_System t = new File_System(f);
		
		System.out.println("目标目录" + f.getName());
		
		t.tree(f, 1);
		System.out.println("===============================");
		
		System.out.println("这个目录中共有" + dirs + "个目录     共" + dirlength + "字节");
		System.out.println("这个目录中共有" + files + "个文件     共" + filelength + "字节");
		
	}
}

