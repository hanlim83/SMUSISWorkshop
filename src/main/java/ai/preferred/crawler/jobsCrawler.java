package ai.preferred.crawler;

import ai.preferred.crawler.entity.jobListing;
import ai.preferred.crawler.handlers.findJobListingsHandler;
import ai.preferred.venom.Crawler;
import ai.preferred.venom.Session;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;

public class jobsCrawler {
    public static final Session.Key<EntityCSVStorage<jobListing>> STORAGE_KEY = new Session.Key<>();
    public static void main(String[] args) {
        try (EntityCSVStorage<jobListing> storage = new EntityCSVStorage<>("data.csv")) {
            Crawler crawler = Crawler.builder().setSession(Session.builder().put(STORAGE_KEY, storage).build()).build().startAndClose();
            Request request = new VRequest("https://startupjobs.asia/job/all/anywhere");
            crawler.getScheduler().add(request,new findJobListingsHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
