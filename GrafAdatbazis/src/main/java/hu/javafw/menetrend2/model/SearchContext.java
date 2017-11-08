package hu.javafw.menetrend2.model;

/**
 * SearchContext class: wrapper for a paged schedule object 
 *
 * @author kkrisz
 */
public class SearchContext {
    public Schedule schedule;
    public Paging paging;

    public SearchContext(Schedule schedule, Paging paging) {
        this.schedule = schedule;
        this.paging = paging;
    }

    public SearchContext() {
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
