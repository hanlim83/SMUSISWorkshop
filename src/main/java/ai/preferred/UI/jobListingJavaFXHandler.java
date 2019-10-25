package ai.preferred.UI;

import ai.preferred.crawler.EntityCSVStorage;
import ai.preferred.crawler.entity.jobListing;
import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class jobListingJavaFXHandler implements Handler {
    private static final Logger LOGGER = LoggerFactory.getLogger(webCrawlerController.class);

    @Override
    public void handle(Request request, VResponse vResponse, Scheduler scheduler, Session session, Worker worker) {
        Element jobListingNameElement = vResponse.getJsoup().selectFirst("div.detail-header > div.image > h5");
        Element companyNameElement = vResponse.getJsoup().selectFirst("div.detail-header > div.company-profile.row > div > h6:nth-child(1) > a");
        Element companyLocationElement = vResponse.getJsoup().selectFirst("div.detail-header > div.company-profile.row > div > h6:nth-child(2) > a");
        Element salaryElement = vResponse.getJsoup().selectFirst("div.row.job-categories:nth-child(1) > div:nth-child(1) > p");
        Element industryElement = vResponse.getJsoup().selectFirst("div.row.job-categories:nth-child(1) > div:nth-child(2) > p");
        Element jobTypeElement = vResponse.getJsoup().selectFirst("div.row.job-categories:nth-child(1) > div:nth-child(3) > p");
        Element skillsElement = vResponse.getJsoup().selectFirst("div.row.job-categories:nth-child(1) > div:nth-child(4) > p");
        final jobListing listing = new jobListing(jobListingNameElement.text(), companyNameElement.text(), companyLocationElement.text(), salaryElement.text(), industryElement.text(), jobTypeElement.text(), skillsElement.text());
        final EntityCSVStorage<jobListing> storage = session.get(webCrawlerController.STORAGE_KEY);
        worker.executeBlockingIO(() -> {
            LOGGER.info("storing property: {} ", listing.getName());
            try {
                storage.append(listing);
            } catch (IOException e) {
                LOGGER.error("Unable to store listing.", e);
            }
        });
    }
}
