import edu.uwb.css143b2020fall.service.Indexer;
import edu.uwb.css143b2020fall.service.IndexerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/*
DO NOT CHANGE
 */

public class IndexerTest {

    private Indexer indexer;

    @Before
    public void setUp() {
        indexer = new IndexerImpl();
    }

    @Test
    public void testIndexer() {
        List<TestCase> cases = getTestCase();
        for (TestCase testCase : cases) {
            Map<String, List<List<Integer>>> actual = indexer.index(testCase.input);
            // uncomment this to see the index
            //System.out.println(actual);
            assertEquals(testCase.expect, actual);
        }
    }
    private List<TestCase> getTestCase() {
        List<TestCase> testCases = new ArrayList<>(Arrays.asList(
                new TestCase(
                        new ArrayList<>(Arrays.asList(
                                "world world hello world",
                                "world world hallo",
                                "world seattle hello abc world day",
                                "abc hello world fun",
                                "sunny    better",
                                "     fun day fun",
                                "     day sunny fun   ")),
                        Util.getIndexForIndexerTest()
                ),
                new TestCase(
                        new ArrayList<>(Arrays.asList(
                                "    "
                        )),
                        new HashMap<>()
                )
        ));
        return testCases;
    }

    private class TestCase {
        private List<String> input;
        private Map<String, List<List<Integer>>> expect;
        public TestCase(List<String> input, Map<String, List<List<Integer>>> output) {
            this.input = input;
            this.expect = output;
        }
    }
}