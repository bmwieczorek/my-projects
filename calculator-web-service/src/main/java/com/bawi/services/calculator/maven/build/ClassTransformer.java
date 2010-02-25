package com.bawi.services.calculator.maven.build;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.apache.commons.io.FilenameUtils.getBaseName;
import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.io.FilenameUtils.getFullPath;
import static org.apache.commons.lang.StringUtils.replace;

import java.io.File;
import java.io.IOException;

public class ClassTransformer {

	private static final String POSTFIX = "ModelBase";

	public static void main(String[] args) throws Exception {
		for (String arg : args) {
			File inputFile = new File(arg);
			if (inputFile.exists())
				renameClass(arg, POSTFIX);
		}
	}

	private static void renameClass(String inputFileName, String postfix)
			throws IOException {
		String fileBaseName = getBaseName(inputFileName);
		String outputFileName = getFullPath(inputFileName) + fileBaseName
				+ postfix + "." + getExtension(inputFileName);
		File inputFile = new File(inputFileName);
		String inputFileText = readFileToString(inputFile);
		String outputFileText = replace(inputFileText,
				"\"" + fileBaseName +"\"", "\"" + fileBaseName + postfix + "\"");
		outputFileText = replace(outputFileText,
				"public class " + fileBaseName, "public abstract class " + fileBaseName + postfix);
		outputFileText = replace(outputFileText, "return this", "return ((" + fileBaseName + ")this)");
		writeStringToFile(new File(outputFileName), outputFileText);
		inputFile.delete();
		System.out.println("Renamed class " + inputFileName + " to "
				+ outputFileName);

	}
}
