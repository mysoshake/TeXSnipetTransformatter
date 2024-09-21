import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TJ
{
	{// public static void main(String[] lines)
//	{
//		for (String line : lines)
//		{
//			for (String sect : line.split("\n"))
//			{
//				System.out.println("\"" + sect + "\",");
//			}
//		}
//	}
	}
	
	public static void main(String[] filenames)
	{
		for (String fname : filenames)
		{
			create(Paths.get(fname));
		}
	}
	
	private static void create(Path fpath)
	{
		Path outdir = Paths.get(".");
		try (BufferedReader reader = Files.newBufferedReader(fpath);)
		{
			Path outfile = outdir.resolve("output_" + fpath.getFileName());
			try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(outfile)))
			{
				List<String> lines = translate(reader.lines());
				for (String line : lines)
				{
					writer.println(line);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static List<String> translate(Stream<String> lines)
	{
		List<String> ret = lines.map((line) ->
		{
			StringBuilder builder = new StringBuilder();
			builder.append("\t\t\t\"");
			String bs_replced = line.replaceAll("\\\\", "\\\\\\\\");
			builder.append(bs_replced.replaceAll("	", "\\\\t"));
			builder.append("\",");
			return new String(builder);
		}).collect(Collectors.toList());
		return ret;
	}
}
