package com.mon.demo.upload.client;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Response;

import java.io.File;

/**
 * @author liu fucheng
 */
@Component
public class FileUploadClient {
    private static final String PATH = "/upload/file";
    private static final String UPLOAD_SERVER = "https://your-server.com";

    public String uploadFile(File amrFile, String uploadToken) {
        FileDataBodyPart filePart = new FileDataBodyPart("content", amrFile);
        FormDataMultiPart multipart = new FormDataMultiPart();
        multipart.bodyPart(filePart);
        Configuration clientConfig = new ClientConfig(MultiPartFeature.class);
        Client client = ClientBuilder.newClient(clientConfig);
        try (Response response = client.target(UPLOAD_SERVER)
                .path(PATH)
                .queryParam("param1", 1)
                .request()
                .header("Authorization", uploadToken)
                .post(Entity.entity(multipart, multipart.getMediaType()))) {
            return response.readEntity(String.class);
        }
    }
}
