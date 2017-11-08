package hu.bme.szoftarch.graphdb.model;

/**
 * SearchContext class: wrapper for a paged graph object
 *
 * @author kkrisz
 */
public class SearchContext {
    public Graph graph;
    public Paging paging;

    public SearchContext(Graph graph, Paging paging) {
        this.graph = graph;
        this.paging = paging;
    }

    public SearchContext() {
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
