import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FilenameUtils.getBaseName;
import static org.apache.commons.io.FilenameUtils.getExtension;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

public class ClassTransformer {

	private static final String POSTFIX = "ModelBase";

	public static void main(String[] args) throws Exception {
		for (String arg : args) {
			File inputFile = new File(arg);
			if (inputFile.exists())
				renameClass(arg, POSTFIX);
		}
	}

	private static void renameClass(String inputFileName, String classExtension)
			throws IOException {
		String extension = getExtension(inputFileName);
		String baseName = getBaseName(inputFileName);
		String path = FilenameUtils.getFullPath(inputFileName);
		String outputFileName = path + baseName+ classExtension + "." + extension;
		String inputFileText = readFileToString(new File(inputFileName));
		String outputFileText = StringUtils.replace(inputFileText, baseName,
				baseName + classExtension);
		System.out.println(inputFileText + ":" + outputFileText);
		FileUtils.writeStringToFile(new File(outputFileName), outputFileText);
	}
}
