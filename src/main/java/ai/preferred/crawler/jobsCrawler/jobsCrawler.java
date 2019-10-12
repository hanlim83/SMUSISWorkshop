package ai.preferred.crawler.jobsCrawler;

import ai.preferred.venom.Crawler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;

public class jobsCrawler {
    public static void main(String[] args) {
        try(Crawler crawler = Crawler.buildDefault().start()){
            Request request = new VRequest("https://startupjobs.asia/job/all/anywhere");
            crawler.getScheduler().add(request,new findJobListingsHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
