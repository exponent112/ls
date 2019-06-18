package ls;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class ls {
	static boolean all;
	static String inP;
	static boolean help;
	static boolean p;
	static boolean re;
	static boolean f;
	static boolean A;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Options options = new Options();
	
		options.addOption(Option.builder("a").longOpt("all")
		        .desc(" lists all files in the given directory, including those whose names start with \".\" ")
		        .build());
		
		options.addOption(Option.builder("help").longOpt("help")
		        .desc("help")
		        .build());
		options.addOption(Option.builder("r").longOpt("reverse")
		        .desc("reverse sorting")
		        .build());
		options.addOption(Option.builder("f")
		        .desc("no sorting")
		        .build());
		options.addOption(Option.builder("A")
		        .desc(" \n" + 
		        		"Prints hidden items except'.' And '..' ")
		        .build());
		
		
		try {
			int a = args.length;
			if(args[a-1].contains("/")) {
				inP = args[a-1];
			}
			else {
				inP = "./";
			}
		}catch (Exception e) {
			inP = "./";
		}
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}//if(help)
		}
		ls ls = new ls();
		ls.Printer();
		
	}

	
	
	
	
	private static void printHelp(Options options) {
		// TODO Auto-generated method stub
		HelpFormatter formatter = new HelpFormatter();
		String header = "ls";
		String footer ="";
		formatter.printHelp("ls", header, options, footer, true);
	}

	private static boolean parseOptions (Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			
		
			all = cmd.hasOption("a");
			help = cmd.hasOption("help");
			re = cmd.hasOption("r");
			f = cmd.hasOption("f");
			A = cmd.hasOption("A");
			

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}//parseOptions
	
	public void Printer () {
			if(f) {
				File dir = new File(inP);
				File [] fileList = dir.listFiles();
				System.out.println(".");
				System.out.println("..");
				for(File file : fileList) {
						String fileName = file.getName();
							System.out.println(fileName);
					}
			}
			else {
				ArrayList<String> f= new ArrayList<String>();
				File dir = new File(inP);
				File [] fileList = dir.listFiles();
				int t =fileList.length;
				if(all) {
					for(File file : fileList) {
						String fileName = file.getName();
							f.add(fileName);
					}
					if(p) {
						f.add("./");
						f.add("../");
					}
					else{
						f.add(".");
						f.add("..");
					}
				}
				else if(A){
					for(File file : fileList) {
						String fileName = file.getName();
							f.add(fileName);
					}
					
				}
				else {
					for(File file : fileList) {
						String fileName = file.getName();
						if(fileName.contains(".")) continue;
							f.add(fileName);
					}
				}
				Collections.sort(f);
				if(re)
					Collections.sort(f, new AscendingString());
				for (int i = 0; i < f.size(); i++) { 
					System.out.println(f.get(i) + " "); 
					}
			}
	}
	

	class AscendingString implements Comparator<String> { 
		@Override 
		public int compare(String a, String b) { 
			return b.compareTo(a); 
			}
		}



	
}
