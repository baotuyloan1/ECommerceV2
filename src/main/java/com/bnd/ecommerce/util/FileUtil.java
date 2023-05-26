package com.bnd.ecommerce.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

  public static long saveFile(String uploadDir, String fileName, MultipartFile multipartFile)
      throws IOException {
    Path uploadPath = Paths.get(uploadDir);
    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }
    try (InputStream inputStream = multipartFile.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      return Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
      throw new IOException("Could not save image file: " + fileName, ioe);
    }
  }

  //  public static long saveFileImage(String uploadDir, String fileName, MultipartFile
  // multipartFile)
  //      throws IOException {
  //    Path uploadPath = Paths.get(uploadDir);
  //    if (!Files.exists(uploadPath)) {
  //      Files.createDirectories(uploadPath);
  //    }
  //    try (InputStream inputStream = multipartFile.getInputStream()) {
  //      Path filePath = uploadPath.resolve(fileName);
  //      return Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
  //    } catch (IOException ioe) {
  //      throw new IOException("Could not save image file: " + fileName, ioe);
  //    }
  //  }

  public static void deleteFile(String uploadDir, String fileName) throws IOException {
    Path uploadPath = Paths.get(uploadDir);
    Path filePath = uploadPath.resolve(fileName);
    if (Files.exists(filePath)) {
      try {
        Files.deleteIfExists(filePath);
      } catch (IOException e) {
        throw new IOException("Could not delete image: " + fileName);
      }
    }
  }

  public static void deleteDir(String dir) throws IOException {
    Path path = Paths.get(dir);
    if (Files.exists(path)) {
      try {
        FileUtils.deleteDirectory(path.toFile());
      } catch (IOException e) {
        throw new IOException("Could not delete dir: " + dir);
      }
    }
  }

  public static void deleteImageDirByProductId(long id) {
    if (id > 0) {
      String uploadDir = "phone-photos/" + id;
      try {
        FileUtil.deleteDir(uploadDir);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
