package ai.preferred.UI;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class findJobListingsJavaFXHandler implements Handler {
    @Override
    public void handle(Request request, VResponse vResponse, Scheduler scheduler, Session session, Worker worker) {
        Elements elements = vResponse.getJsoup().select("#w1 > ul > li");
        for (Element jobListing : elements) {
            scheduler.add(new VRequest("https://startupjobs.asia" + jobListing.selectFirst("div > div > div > h5 > a").attr("href")), new jobListingJavaFXHandler());
        }
        Element element = vResponse.getJsoup().selectFirst("#suj-single-job-detail-container > div.suj-single-joblist.col.s12.l5 > div > div.row.bottom-pagination > div > div > div > div > div > div > ul > li:last-child");
        if (!element.attr("class").equalsIgnoreCase("next disabled"))
            scheduler.add(new VRequest("https://startupjobs.asia" + element.select("a").attr("href")), this);
    }
}
