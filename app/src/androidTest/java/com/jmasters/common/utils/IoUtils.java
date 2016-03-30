package com.jmasters.common.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.Map.Entry;

import com.jmasters.common.finals.Constants;

public class IoUtils implements Constants {


	public static void copyFile(String sourceFilePath, String destFilePath) throws IOException {
		copyFile(new File(sourceFilePath), new File(destFilePath));
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		copyFile(new FileInputStream(sourceFile), destFile);
	}

	public static void copyFile(FileInputStream sourceStream, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = sourceStream.getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static byte[] readBinaryFile(File filePath) throws IOException {
		FileInputStream fileinputstream = new FileInputStream(filePath);
		int numberBytes = (int) filePath.length();
		byte bytearray[] = new byte[numberBytes];
		fileinputstream.read(bytearray);
		fileinputstream.close();
		return bytearray;
	}

	public static void writeBinaryFile(byte[] fileBytes, File filePath) throws IOException {
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(fileBytes);
		fos.close();
	}



	public static void main(String[] args) {
		// try {
		// FSTreeNode rootNode = new FSTreeNode(new File("c:/"), null);
		// createFSTree(rootNode, 1);
		// System.out.println("DONE");
		// // createFullPathRecurcively("d:/temp/1/2/3/5");
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String testString = "根";
		// byte[] stringByteArray = testString.getBytes();
		// byte[] byteArray = { -127 };
		//
		// System.out.println(toHexString(stringByteArray));
		// System.out.println(toHexString(byteArray));
	}
}
