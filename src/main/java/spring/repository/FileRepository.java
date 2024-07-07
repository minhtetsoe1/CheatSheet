package spring.repository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class FileRepository {

	public String fileUpload1(MultipartFile image) {
		String UPLOAD_DIRECTORY = "./images/blog";
		String filename = image.getOriginalFilename();
		System.out.println(UPLOAD_DIRECTORY + " " + filename);
		String extractedPath = null;
		if (!image.isEmpty()) {
			try {
				byte[] photoBytes = image.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(UPLOAD_DIRECTORY + File.separator + filename)));
				stream.write(photoBytes);
				stream.flush();
				stream.close();
				extractedPath = UPLOAD_DIRECTORY + "/" + filename;
			} catch (IOException e) {
				System.out.println("save courseimage: " + e.getMessage());
			}
		}
		return extractedPath;
	}

}
