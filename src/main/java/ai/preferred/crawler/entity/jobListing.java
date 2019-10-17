package ai.preferred.crawler.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class jobListing {
    private String name;
    private String company;
    private String location;
    private String salary;
    private String industry;
    private String type;
    private String skills;

    public jobListing(String name, String company, String location, String salary, String industry, String type, String skills) {
        this.name = name;
        this.company = company;
        this.location = location;
        this.salary = salary;
        this.industry = industry;
        this.type = type;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
