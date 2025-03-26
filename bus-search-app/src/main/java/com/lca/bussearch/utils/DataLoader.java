package com.lca.bussearch.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.lca.bussearch.repository.RouteRepository;
import com.lca.bussearch.repository.LocationRepository;
import com.lca.bussearch.repository.RouteStopRepository;
import com.lca.bussearch.repository.BusRepository;
import com.lca.bussearch.repository.ScheduleRepository;
import com.lca.bussearch.entity.Location;
import com.lca.bussearch.entity.Route;
import com.lca.bussearch.entity.Bus;
import com.lca.bussearch.entity.Schedule;
import com.lca.bussearch.entity.RouteStop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final LocationRepository locationRepository;
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final ScheduleRepository scheduleRepository;
    private final RouteStopRepository routeStopRepository;

    public DataLoader(LocationRepository locationRepository, RouteRepository routeRepository,
                      BusRepository busRepository, ScheduleRepository scheduleRepository,
                      RouteStopRepository routeStopRepository) {
        this.locationRepository = locationRepository;
        this.routeRepository = routeRepository;
        this.busRepository = busRepository;
        this.scheduleRepository = scheduleRepository;
        this.routeStopRepository = routeStopRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Long locationCount = locationRepository.count();
        System.out.println("locationCount: "+locationCount);
        if (locationCount > 100) {
            System.out.println("Enough data is already loaded. Skipping location insertion.");
        } else {
            System.out.println("Loading data from CSV files...");
            loadLocationsFromCsv();
        }

        loadSampleBusData(locationCount);
    }

    private void loadLocationsFromCsv() {
        List<String> csvFiles = List.of(
            "Karnataka_cleaned.csv",
            "Andhra_Pradesh_cleaned.csv",
            "Maharashtra_cleaned.csv",
            "Telangana_cleaned.csv"
        );

        for (String fileName : csvFiles) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new ClassPathResource(fileName).getInputStream(), StandardCharsets.UTF_8))) {
                
                String line;
                boolean isHeader = true;

                while ((line = reader.readLine()) != null) {
                    if (isHeader) { 
                        isHeader = false;
                        continue;
                    }

                    String[] data = line.split(",");

                    if (data.length < 11) continue;

                    Location location = new Location();
                    location.setPostalCode(data[0].trim());
                    location.setPlaceName(data[1].trim());
                    location.setTaluka(data[2].trim());
                    location.setDistrict(data[3].trim());
                    location.setState(data[4].trim());
                    location.setTalukaCode(parseInteger(data[5]));
                    location.setDistrictCode(parseInteger(data[6]));
                    location.setStateCode(data[7].trim());
                    location.setCountryCode(data[8].trim());
                    location.setLatitude(parseDouble(data[9]));
                    location.setLongitude(parseDouble(data[10]));
                    location.setAccuracy(parseInteger(data.length > 11 ? data[11] : "0"));

                    locationRepository.save(location);
                }
                System.out.println("✅ Data loaded successfully from: " + fileName);
            } catch (Exception e) {
                System.err.println("❌ Error loading file " + fileName + ": " + e.getMessage());
            }
        }
    }

    private void loadSampleBusData(Long locationCount) {
        System.out.println("routeRepository.count(): "+routeRepository.count());
        if (routeRepository.count() > 0) {
            System.out.println("✅ Sample bus data already exists. Skipping insertion.");
            return;
        }

        // Fetch some locations to create sample routes
        if (locationCount < 5) {
            System.err.println("❌ Not enough locations in the database to create routes.");
            return;
        }

        List<Location> locations = locationRepository.findAll();
        Location cityCenter = locations.get(0);
        Location mainStreet = locations.get(1);
        Location airport = locations.get(2);
        Location downtown = locations.get(3);
        Location railwayStation = locations.get(4);

        // Add sample routes
        Route route1 = new Route(cityCenter, airport);
        Route route2 = new Route(mainStreet, railwayStation);
        routeRepository.saveAll(List.of(route1, route2));
        System.out.println("Route table is loaded...");

        // Add route stops
        routeStopRepository.saveAll(List.of(
            new RouteStop(route1, mainStreet, 1, "08:15"),
            new RouteStop(route1, downtown, 2, "08:30"),
            new RouteStop(route2, downtown, 1, "09:00")
        ));
        System.out.println("RouteStop table is loaded...");

        // Add sample buses
        Bus bus1 = new Bus("105A");
        Bus bus2 = new Bus("220B");
        busRepository.saveAll(List.of(bus1, bus2));
        System.out.println("Bus table is loaded...");

        // Add schedules
        scheduleRepository.saveAll(List.of(
            new Schedule(bus1, route1, "08:00", "08:45", "Mon-Fri"),
            new Schedule(bus2, route2, "09:00", "09:45", "Sat-Sun")
        ));
        System.out.println("Schedule table is loaded...");

        System.out.println("✅ Sample bus data loaded successfully.");
    }

    private Integer parseInteger(String value) {
        try {
            return value.isEmpty() ? null : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double parseDouble(String value) {
        try {
            return value.isEmpty() ? null : Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
