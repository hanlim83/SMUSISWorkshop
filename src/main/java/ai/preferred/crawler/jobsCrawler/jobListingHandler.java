package ai.preferred.crawler.jobsCrawler;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Element;

public class jobListingHandler implements Handler {
    @Override
    public void handle(Request request, VResponse vResponse, Scheduler scheduler, Session session, Worker worker) {
        Element salaryElement = vResponse.getJsoup().selectFirst("div.row.job-categories:nth-child(1) > div:nth-child(1) > p");
        Element industryElement = vResponse.getJsoup().selectFirst("div.row.job-categories:nth-child(1) > div:nth-child(2) > p");
        Element jobTypeElement = vResponse.getJsoup().selectFirst("div.row.job-categories:nth-child(1) > div:nth-child(3) > p");
        Element skillsElement = vResponse.getJsoup().selectFirst("div.row.job-categories:nth-child(1) > div:nth-child(4) > p");
        System.out.println(salaryElement.text());
        System.out.println(industryElement.text());
        System.out.println(jobTypeElement.text());
        System.out.println(skillsElement.text());
    }
}
