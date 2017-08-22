import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.util.ImportUtil;

public class Main {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("CSV to MDB");
			System.out.println("Converts all CSV files in a folder in a single mdb (Access v2000) database.");
			System.out.println("How to use:");
			System.out.println("java -jar CSVToMDB.jar <input folder> <output .mdb file>");
			System.out.println("Lots of fun by not using Access on Mac or Linux!!!!!!!!!!");
		} else {
			File folder = new File(args[0]);
			File output = new File(args[1]);
			ArrayList<File> importFiles = new ArrayList<>();
			
			System.out.println("=== Reading files in " + folder);
			for (final File fileEntry : folder.listFiles()) {
				importFiles.add(fileEntry);
				System.out.println(fileEntry.getName());
		    }
			
			System.out.println("=== Build new mdb file");
			try {
				Database db = DatabaseBuilder.create(Database.FileFormat.V2000, output);	
				
				System.out.println("=== Import Files in mdb");
				for (Iterator<File> iterator = importFiles.iterator(); iterator.hasNext();) {
					final File fileEntry = iterator.next();
					System.out.println("Importiere: " + fileEntry.getAbsolutePath());
					int pos = fileEntry.getName().lastIndexOf(".");
					new ImportUtil.Builder(db, fileEntry.getName().substring(0, pos)).setDelimiter(",").importFile(fileEntry);
				}
				
				db.close();
				System.out.println("Fertig!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
