package spring.study.gcp.application;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;


@Service
@RequiredArgsConstructor
public class GcpService {
    
    /**
     * Upload new file.
     *
     * @return Inserted file metadata if successful, {@code null} otherwise.
     * @throws IOException if service account credentials file not found.
     */
    public String upload() throws IOException {
        
        // Load pre-authorized user credentials from the environment.
        // TODO(developer) - See https://developers.google.com/identity for
        // guides on implementing OAuth2 for your application.
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
            .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
            credentials);
        
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(
            new NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            requestInitializer
        )
            .setApplicationName("Drive samples")
            .build();

//		FileInfoDTO fileInfo = documentFileJpaRepository.findById(47).get().getFileInfo();
//
//		String dirName = fileInfo.getPath().split("/")[2];
//
//		S3Object s3Object = storageUploader.getS3File(dirName, fileInfo.getName(), false);
//
//		InputStream in = s3Object.getObjectContent();
//		java.io.File tmp = java.io.File.createTempFile("s3test", "");
//		Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		in.close();
        
        // Upload file on drive.
        File fileMetadata = new File();
        fileMetadata.setName("sample.pdf");
        // File's content.
//		java.io.File filePath = new java.io.File("test.pdf");
        java.io.File filePath = new ClassPathResource("sample.pdf").getFile();
        // Specify media type and file-path for file.
        FileContent mediaContent = new FileContent("application/pdf", filePath);
        try {
            File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
            System.out.println("File ID: " + file.getId());
            return file.getId();
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
    }
}
