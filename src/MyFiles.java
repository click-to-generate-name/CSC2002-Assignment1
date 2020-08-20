import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * File handling functions for Assignment1
 * 
 * @author hrrhan002
 *
 */
class MyFiles {
	/**
	 * <p>Reads terrain data from a file in format shown below. Data is written into
	 * a {@link PointElevation} array.</p>
	 * <p> Required file format:<br> <terrain num rows – INT> <terrain num cols – INT> <br>
	 * <height at grid pos (0,0) - FLOAT> <height at grid pos (0,1) - FLOAT> ... etc.</p>
	 * @param filename
	 * @return PointElevation grid with data from file
	 */
	public static PointElevation[][] extractTerrainData(String filename) {
		try {
			// IO objects
			File inFile = new File(filename);
			Scanner inScanner = new Scanner(inFile);
			
			/* XXX:
			 * Assumption is that files are well formed. No hasNext*() checks.
			 */
			
			// get dimensions
			int rows = inScanner.nextInt();
			int cols = inScanner.nextInt();
			
			// create empty grid with dims
			PointElevation[][] grid = new PointElevation[rows][cols];
			
			// populate grid
			for (int i=0; i<rows; i++) {
				for (int j=0; j<cols; j++) {
					grid[i][j] = new PointElevation(inScanner.nextFloat());
				}
			}
			
			inScanner.close();
			return grid;
		}
		catch(FileNotFoundException e) { // very general exception handling
			System.out.println("Error opening or reading file "+filename);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <p>Writes basin data to output file with the given name.</p>
	 * <p>Note that if the file already exists, it must be empty</p>
	 * 
	 * @param total Total number of basins listed
	 * @param coords List of coordinates for each basin
	 * @param filename Name of output file
	 */
	public static void compileTerrainData(int total, int[][] coords, String filename) {
		try {
			File outFile = new File(filename);
			
			// create file if it doesn't exist
			if (outFile.createNewFile()) {
				System.out.println("File "+filename+" created.");
			}
			else {
				System.out.println("File "+filename+" found.");
				if (outFile.length() != 0L) { // check if file is empty
					System.out.println("File "+filename+" not empty. "
							+ "Contents will be overwritten.");
				}
			}
			
			FileWriter outWriter = new FileWriter(filename);
			
			// write total and coords to file
			outWriter.write(total + "\n");
			for (int i=0; i<coords.length; i++) {
				outWriter.write(coords[i][0] + " " + coords[i][1] + "\n");
			}
			
			outWriter.close();
		}
		catch (IOException e) { // very general exception handling
			e.printStackTrace();
		}
	}
	
	public static void compileTestData(double[][] data, int[] seqCutoffs, String dataSize, String seqPar) {
//		String path = "";
		String filename = seqPar + "_" + dataSize + "-data" + ".txt";
		try {
			File f = new File(filename);
			
			if (f.createNewFile()) {} // create file
			else { // file already exists
				FileWriter wTemp = new FileWriter(f);
				wTemp.write(""); // clear file
				wTemp.close();
			}
			
			FileWriter w = new FileWriter(f, true);
			w.write("# Speed Test Data\n# Units: ns\n# Process: "+seqPar+"\n# Data size: "+dataSize+"\n");
			
			for (int c=0; c<data.length; c++) {
				w.write("\n# Sequential cutoff: "+seqCutoffs[c]+"\n");
				for (int i=0; i<data[0].length; i++) {
					w.write(data[c][i] + "\n");
				}
			}
			
			w.close();
		}
		catch(IOException e) { // very general exception handling
			
		}
	}
}










