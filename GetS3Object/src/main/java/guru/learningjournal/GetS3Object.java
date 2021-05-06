package guru.learningjournal;

//https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/java-dg-roles.html

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetS3Object {

    public static void main(String[] args) throws IOException
    {
        AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

        try {
            System.out.println("Downloading an object");
            String bucketName = "learningjournal.guru";
            String key = "iam-best-practices.txt";

            S3Object s3object = s3Client.getObject(
                    new GetObjectRequest(bucketName, key));

            displayTextInputStream(s3object.getObjectContent());
        }
        catch(AmazonServiceException ase) {
            System.err.println("ASE Exception : " + ase.getErrorMessage());
        }
        catch(AmazonClientException ace) {
            System.err.println("ACE Exception: " + ace.getMessage());
        }
    }

    private static void displayTextInputStream(InputStream input) throws IOException
    {
        // Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while(true)
        {
            String line = reader.readLine();
            if(line == null) break;
            System.out.println( "    " + line );
        }
        System.out.println();
    }
}