package com.napier.sem.service;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.reports.CapitalCityReport;
import com.napier.sem.reports.CityReport;
import com.napier.sem.reports.CountryReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the default implementation of the AppService interface.
 * The implemented methods contain the actual business logic.
 */
public class DefaultAppServices implements AppServices {

    private final DataLayer dataLayer;

    public DefaultAppServices(DataLayer dataLayer) {
        this.dataLayer = dataLayer;
    }

    @Override
    public List<CountryReport> getAllCountriesOrderedByLargestPopulationToSmallest() throws SQLException {
        // get data from database
        ResultSet resultSet = dataLayer.executeNativeQuery(Reports.ALL_COUNTRIES_ORDERED_BY_LARGEST_POPULATION_TO_SMALLEST);
        List<CountryReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            // extract values from result set
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            String continent = resultSet.getString("Continent");
            int population = resultSet.getInt("population");
            String region = resultSet.getString("Region");
            String capital = resultSet.getString("Capital");

            // create country report
            CountryReport report = new CountryReport(code, name, continent, region, population, capital);
            resultList.add(report);
        }
        return resultList;
    }

    @Override
    public List<CityReport> getAllCitiesInTheWorldOrderedByLargestPopulationToSmallest() throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select name, population, district, countryCode FROM city\n" +
                "ORDER BY population DESC");
        List<CityReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            int population = resultSet.getInt("population");
            String district = resultSet.getString("District");
            String countryCode = resultSet.getString("Country Code ");
            CityReport report = new CityReport(name, countryCode, district, population);
            resultList.add(report);
        }
        return resultList;

    }

    @Override
    public List<CityReport> getAllCitiesFromContinentOrderedByLargestPopulationToSmallest(String continentsql) throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select c.name, c.population, district, c.CountryCode \n" +
                "FROM city c\n" +
                "inner join country co on c.CountryCode = co.code\n" +
                "where continent = " + continentsql + " " +
                "ORDER BY population DESC");
        List<CityReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String countryCode = resultSet.getString("Country Code ");
            String name = resultSet.getString("Name");
            String district = resultSet.getString("District");
            int population = resultSet.getInt("population");
            CityReport report = new CityReport(name, countryCode, district, population);
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CityReport> getAllCitiesFromRegionOrderedByLargestPopulationToSmallest(String regionsql) throws SQLException {
        ResultSet resultSet =dataLayer.executeNativeQuery("select c.name, c.population, district, c.CountryCode \n" +
                "FROM city c\n" +
                "inner join country co on c.CountryCode = co.code\n" +
                "where Region = " + regionsql + " "+
                "ORDER BY population DESC");
        List<CityReport> resultList =new ArrayList<>();
        while (resultSet.next()){

            String countryCode = resultSet.getString("Country Code ");
            int population = resultSet.getInt("population");
            String district = resultSet.getString("District");
            String name = resultSet.getString("Name");
            CityReport report = new CityReport(name,countryCode,district,population);
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CityReport> getAllCitiesFromCountryOrderedByLargestPopulationToSmallest(String countrysql) throws SQLException {
        ResultSet resultSet =dataLayer.executeNativeQuery("select c.name, c.population, district, c.CountryCode \n" +
                "FROM city c\n" +
                "inner join country co on c.CountryCode = co.code\n" +
                "where  co.Name = " + countrysql + " " +
                "ORDER BY population DESC");
        List<CityReport> resultList =new ArrayList<>();
        while (resultSet.next()){
            int population = resultSet.getInt("population");
            String countryCode = resultSet.getString("Country Code ");
            String name = resultSet.getString("Name");
            String district = resultSet.getString("District");
            CityReport report = new CityReport(name,countryCode,district,population);
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CityReport> getAllCitiesFromDistrictOrderedByLargestPopulationToSmallest(String districtsql) throws SQLException {
        ResultSet resultSet =dataLayer.executeNativeQuery("select name, district, population, CountryCode from city\n" +
                "where district = " + districtsql + " " +
                "ORDER BY population DESC");
        List<CityReport> resultList =new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString("Name");
            String district = resultSet.getString("District");
            String countryCode = resultSet.getString("Country Code ");
            int population = resultSet.getInt("population");
            CityReport report = new CityReport(name,countryCode,district,population);
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CountryReport> getNCountriesFromWorldOrderedByLargestPopulationToSmallestNisSelectedByUser(int limitsql) throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select code, name, Continent, population, Region from country\n" +
                "order by population DESC\n" +
                "limit " + limitsql);
        List<CountryReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            String continent = resultSet.getString("Continent");
            int population = resultSet.getInt("population");
            String region = resultSet.getString("Region");
            CountryReport report = new CountryReport(code, name, continent, region, population,"TODO:capital");
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CountryReport> getNCountriesFromContinentOrderedByLargestPopulationToSmallestNisSelectedByUser(String continentsql, int limitsql) throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select code, name, Continent, population, Region from country\n" +
                "where continent = " + continentsql + " " +
                "order by population DESC\n" +
                "limit " + limitsql);
        List<CountryReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            String continent = resultSet.getString("Continent");
            int population = resultSet.getInt("population");
            String region = resultSet.getString("Region");
            CountryReport report = new CountryReport(code, name, continent, region, population, "TODO:capital");
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CountryReport> getNCountriesFromRegionOrderedByLargestPopulationToSmallestNisSelectedByUser(String regionsql, int limitsql) throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select code, name, Continent, population, Region from country\n" +
                "where region = " + regionsql + " " +
                "order by population DESC\n" +
                "limit " + limitsql);
        List<CountryReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            String continent = resultSet.getString("Continent");
            int population = resultSet.getInt("population");
            String region = resultSet.getString("Region");
            CountryReport report = new CountryReport(code, name, continent, region, population, "TODO:capital");
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CapitalCityReport> getNCapitalCitiesByLargestPopulationToSmallestNisSelectedByUser(int limitsql) throws SQLException {
        ResultSet resultSet =dataLayer.executeNativeQuery("select c.name, c.population, co.name FROM city c \n" +
                "inner join country co on c.CountryCode = co.code  \n" +
                "where  co.Capital= c.id\n" +
                "ORDER BY population DESC\n" +
                "limit "+ limitsql);
        List<CapitalCityReport> resultList = new ArrayList<>();
        while(resultSet.next()){
            String name =resultSet.getString("Name");
            String country = resultSet.getString("Country");
            int population = resultSet.getInt("Population");
            CapitalCityReport report = new CapitalCityReport(name,country,population);
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CityReport> getNCitiesByLargestPopulationToSmallestWhereNisSelectedByUser(int limitsql) throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select name, district, population, CountryCode from city\n" +
                "ORDER BY population DESC \n" +
                "limit " + limitsql);
        List<CityReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            String district = resultSet.getString("District");
            String countryCode = resultSet.getString("Country Code ");
            int population = resultSet.getInt("population");
            CityReport report = new CityReport(name,countryCode,district,population);
            resultList.add(report);
        }
        return resultList;
    }

    @Override
    public List<CapitalCityReport> getAllCapitalCitiesfromRegionOrderedByLargestPopulationToSmallest(String regionsql) throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select c.Name,cnt.Region,c.Population,c.CountryCode from city c\n" +
                "join country cnt on c.ID = cnt.Capital\n" +
                "where cnt.Region = " + regionsql + " " +
                "ORDER BY c.Population DESC\n");
        List<CapitalCityReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            String region = resultSet.getString("Region");
            String countryCode = resultSet.getString("Country Code ");
            int population = resultSet.getInt("population");
            CapitalCityReport report = new CapitalCityReport(name, region, population);
            resultList.add(report);
        }
        return resultList;
    }

    @Override
    public List<CapitalCityReport> getAllCapitalCitiesInTheWorldOrderedByLargestPopulationToSmallest() throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select c.Name ,cnt.Name, c.Population, c.CountryCode\n"+
                "from city c\n"+
                "join country cnt on c.ID = cnt.Capital\n"+
                "ORDER BY c.Population DESC\n");
        List<CapitalCityReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            int population = resultSet.getInt("population");
            String Country = resultSet.getString("Country");
            String countryCode = resultSet.getString("Country Code ");
            CapitalCityReport report = new CapitalCityReport(name, Country, population);
            resultList.add(report);
        }
        return resultList;

    }
}
