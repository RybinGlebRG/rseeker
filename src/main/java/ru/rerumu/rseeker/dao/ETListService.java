package ru.rerumu.rseeker.dao;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.rerumu.rseeker.dao.exception.FailedToFetchException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.util.List;

@Service
@Slf4j
public class ETListService {

    private final String etSite;
    private final String etSelector;

    @Autowired
    public ETListService(
            @Value("${lists.et.site}") @NonNull String etSite,
            @Value("${lists.et.selector}") @NonNull String etSelector
    ) {
        this.etSite = etSite;
        this.etSelector = etSelector;
    }

    public List<String> getList(){
        try {
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            } };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            Document doc = Jsoup.connect(etSite)
                    .sslSocketFactory(sc.getSocketFactory())
                    .get();
            List<String> values = doc.select(etSelector).eachText();

//            for(String value: values){
//                log.info("value: {}", value);
//            }

            return values;
        } catch (Exception e){
            throw new FailedToFetchException(e);
        }
    }
}
