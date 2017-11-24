package hu.bme.szoftarch.graphdb.parser;

import com.opencsv.CSVReader;
import hu.bme.szoftarch.graphdb.model.Graph;
import org.springframework.util.StringUtils;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * CSV fájlhoz parser, ami a sorokat WordDto-kba rakja
 * @author redey
 */
public final class CsvParser {

    private CsvParser() { }

    public static Set<Graph> parse(Reader reader) throws IOException{
        CSVReader csvReader = null;
        final Set<Graph> graphs = new TreeSet<>(Comparator.comparing(Graph::getName));
        try {
            csvReader = new CSVReader(reader);
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                if(line.length == 2) {
                    final Graph graph = new Graph();
                    graph.setName(line[0]);
                    graph.setDescriptor(line[1]);
                    if(isValid(graph)) {
                        graphs.add(graph);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graphs;
    }

    private static boolean isValid(Graph graph) {
        return StringUtils.hasLength(graph.getName().trim()) && StringUtils.hasLength(graph.getDescriptor().trim());
    }
}
