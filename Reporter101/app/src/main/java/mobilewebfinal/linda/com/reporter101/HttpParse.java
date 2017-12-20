package mobilewebfinal.linda.com.reporter101;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Linda on 07/12/2017.
 */

public class HttpParse {
    String FinalHttpData = "";

    StringBuilder stringBuilder = new StringBuilder();
    String Result;
    String HttpURL = "http://172.20.18.136/mobilewebfinal/userRegistration.php";
    URL url;

    public String postRequest(HashMap<String, String> Data, String HttpUrlHolder) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            url = new URL(HttpURL);
            OutputStream outPutStream;
            BufferedWriter bufferedWriterObject ;
            BufferedReader bufferedReaderObject ;
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(14000);

            httpURLConnection.setConnectTimeout(14000);

            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);

            httpURLConnection.setDoOutput(true);

            outPutStream = httpURLConnection.getOutputStream();

            bufferedWriterObject = new BufferedWriter(
                    new OutputStreamWriter(outPutStream, "UTF-8"));

            bufferedWriterObject.write(FinalDataParse(Data));

            bufferedWriterObject.flush();

            bufferedWriterObject.close();

            outPutStream.close();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReaderObject.readLine()) != null){
                    stringBuilder.append(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String FinalDataParse(HashMap<String, String> hashMap2) throws UnsupportedEncodingException {

        for (Map.Entry<String, String> map_entry : hashMap2.entrySet()) {

            stringBuilder.append("&");

            stringBuilder.append(URLEncoder.encode(map_entry.getKey(), "UTF-8"));

            stringBuilder.append("=");

            stringBuilder.append(URLEncoder.encode(map_entry.getValue(), "UTF-8"));

        }

        Result = stringBuilder.toString();

        return Result;
    }
}
