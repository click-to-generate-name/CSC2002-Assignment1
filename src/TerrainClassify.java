import java.util.concurrent.ForkJoinPool;

/**
 * <p>Contains <code>main</code> method to interface with user and 
 * implement functionality of {@link ElevationAnalysis} class.</p>
 * 
 * @author hrrhan002
 *
 */
public class TerrainClassify {
	private static ElevationAnalysis analyze; // redundant for parallel, needed for sequential
	private static ForkJoinPool fjPool = new ForkJoinPool();
	private static double t_tick;
	
	public static void main(String[] args) {
//		String ipp = "../io-files/"; // input file path prefix
//		String opp = "../io-files/"; // output file path prefix
//		
//		String infile = ipp+args[0];
//		String outfile = opp+args[1];
		String infile = "/home/avk/Documents/2020/CSC2002/CSC2002-Workspace/CSC2002-Assignment1/io-files/small_in.txt";
		String outfile = "/home/avk/Documents/2020/CSC2002/CSC2002-Workspace/CSC2002-Assignment1/io-files/small_out.txt";
		
		analyze = new ElevationAnalysis(MyFiles.extractTerrainData(infile));
		
		int num_basins = fjPool.invoke(new ElevationAnalysis());
		
		MyFiles.compileTerrainData(num_basins, ElevationAnalysis.listBasins(num_basins), outfile);
	}
	
	/**
	 * <p>Records the current time (stored in static field)</p>
	 */
	private static void tick() {
		t_tick = System.currentTimeMillis();
	}
	
	/**
	 * <p>Calculates and returns the time elapsed since 
	 * the last tick() call</p>
	 * @return Elapsed time in ms
	 */
	private static double tock() {
		return System.currentTimeMillis() - t_tick;
	}
}