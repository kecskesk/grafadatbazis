package hu.bme.szoftarch.graphdb.parser;

import com.opencsv.CSVReader;
import hu.bme.szoftarch.graphdb.model.Graph;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

/**
 * CSV fájlhoz parser, ami a sorokat WordDto-kba rakja
 * @author redey
 */
public final class CsvParser {

    private CsvParser() { }

    public static Set<Graph> parse(Reader reader) throws IOException{
        CSVReader csvReader = null;
        final Set<Graph> grahs = new HashSet<>();
        try {
            csvReader = new CSVReader(reader);
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                final Graph graph = new Graph();
                graph.setName(line[0]);
                graph.setDescriptor(line[1]);
                grahs.add(graph);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grahs;
    }
}
