package spring.study.gcp.application;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GcpService {
    
    public void upload() throws IOException {
        
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
        
        // Upload file on drive.
        File fileMetadata = new File();
        fileMetadata.setName("sample.pdf");
        // File's content.
        java.io.File filePath = new ClassPathResource("sample.pdf").getFile();
        // Specify media type and file-path for file.
        FileContent mediaContent = new FileContent("application/pdf", filePath);
        try {
            File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
            System.out.println("File ID: " + file.getId());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
    }
    
    public void searchFile() throws IOException {
        
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
            .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
            credentials);
        
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(new NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            requestInitializer)
            .setApplicationName("Drive samples")
            .build();
        
        String pageToken = null;
        do {
            FileList result = service.files().list()
                .setQ("visibility = 'limited'")
                .setPageToken(pageToken)
                .execute();
            for (File file : result.getFiles()) {
                System.out.printf("Found file: %s (%s)\n",
                    file.getName(), file.getId());
            }
            
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
    }
    
    public List<String> shareFile(String realFileId, String realUser, String realDomain)
        throws IOException {
        
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
            .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
            credentials);
        
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(new NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            requestInitializer)
            .setApplicationName("Drive samples")
            .build();
        
        final List<String> ids = new ArrayList<String>();
        
        
        JsonBatchCallback<Permission> callback = new JsonBatchCallback<Permission>() {
            @Override
            public void onFailure(
                GoogleJsonError e,
                HttpHeaders responseHeaders
            ) throws IOException {
                // Handle error
                System.err.println(e.getMessage());
            }
            
            @Override
            public void onSuccess(
                Permission permission,
                HttpHeaders responseHeaders
            ) throws IOException {
                System.out.println("Permission ID: " + permission.getId());
                
                ids.add(permission.getId());
                
            }
        };
        BatchRequest batch = service.batch();
        Permission userPermission = new Permission()
            .setType("user")
            .setRole("writer");
        
        userPermission.setEmailAddress(realUser);
        try {
            service.permissions().create(realFileId, userPermission)
                .setFields("id")
                .queue(batch, callback);
            
            Permission domainPermission = new Permission()
                .setType("domain")
                .setRole("reader");
            
            domainPermission.setDomain(realDomain);
            
            service.permissions().create(realFileId, domainPermission)
                .setFields("id")
                .queue(batch, callback);
            
            batch.execute();
            
            return ids;
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to modify permission: " + e.getDetails());
            throw e;
        }
    }
}