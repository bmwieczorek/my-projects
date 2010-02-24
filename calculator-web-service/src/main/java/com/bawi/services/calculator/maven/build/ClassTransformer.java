package com.bawi.services.calculator.maven.build;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.apache.commons.io.FilenameUtils.getBaseName;
import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.io.FilenameUtils.getFullPath;
import static org.apache.commons.lang.StringUtils.replace;
import static org.apache.commons.lang.StringUtils.substring;

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
		String fileBaseNameWithoutFirstChar = substring(fileBaseName, 1);
		String outputFileText = replace(inputFileText,
				fileBaseNameWithoutFirstChar, fileBaseNameWithoutFirstChar
						+ postfix);
		writeStringToFile(new File(outputFileName), outputFileText);
		inputFile.delete();
		System.out.println("Renamed class " + inputFileName + " to "
				+ outputFileName);

	}
}
