package app.java.data.fetch.dao;

public interface GeneralDAO {
    /**
     * Population on 1 January<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: count (number)<br/>
     * Dataset: demo_pjan<br/>
     * Years: 1960 - 2018
     *
     * @return
     */
    StringBuilder getPopulation();
}
