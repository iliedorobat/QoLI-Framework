package app.java.relation.dao;

public interface CommonRelDAO {
    double getActivePop(String region, short year);
    double getAvgNetSalary(String region, short year);
    double getResidents(String region, short year);
}
