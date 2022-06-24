package com.philips.healthSystems.surem.http;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class SuremRestService {
    private static final Logger log = LoggerFactory.getLogger(SuremRestService.class);

    /**
     * 슈어엠 API 호출
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Object post(String req_url, Object req_date, Type classOfT) {
        Gson gson = new Gson();
        String inputLine = "";
        String jsonValue = "";
        StringBuffer outResult = new StringBuffer();


        try {
            log.info("=====[슈어엠 API 요청 : " + req_url + "]=====");

            jsonValue = gson.toJson(req_date);
            URL url = new URL(req_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);

            OutputStream os = conn.getOutputStream();
            os.write(jsonValue.getBytes("UTF-8"));
            os.flush();

            //리턴된 결과 읽기
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((inputLine = in.readLine()) != null) {
                outResult.append(inputLine);
            }

            conn.disconnect();

            log.info("=====[슈어엠 API 결과: " + outResult.toString() + " ]=====");

            log.info("=====[슈어엠 API 요청 종료: " + req_url + " ]=====");
        } catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();

            HashMap<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("retcode", "REQ_ERR");
            errorMap.put("retmsg", "요청 중 에러가 발생했습니다.");

            outResult = new StringBuffer();
            outResult.append(gson.toJson(errorMap));
        }

        return gson.fromJson(outResult.toString(), classOfT);
    }
}
