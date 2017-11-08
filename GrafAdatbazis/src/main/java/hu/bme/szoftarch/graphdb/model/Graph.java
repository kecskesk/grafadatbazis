package hu.bme.szoftarch.graphdb.model;

import org.springframework.stereotype.Component;

/**
 * Graph entity: contains a name and a descriptor
 *
 * @author kkrisz
 */
@Component
public class Graph {

    private int id;
    private String name;
    private String descriptor;

    public Graph() {
    }

    public Graph(int id, String name, String descriptor) {
        this.id = id;
        this.name = name;
        this.descriptor = descriptor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graph Graph = (Graph) o;

        if (id != Graph.id) return false;
        if (name != null ? !name.equals(Graph.name) : Graph.name != null) return false;
        return descriptor != null ? descriptor.equals(Graph.descriptor) : Graph.descriptor == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (descriptor != null ? descriptor.hashCode() : 0);
        return result;
    }
}
