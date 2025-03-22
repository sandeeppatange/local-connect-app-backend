package com.example.bustimingapp.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.bustimingapp.repository.LocationRepository;
import com.example.bustimingapp.repository.RouteRepository;
import com.example.bustimingapp.repository.RouteStopRepository;
import com.example.bustimingapp.repository.BusRepository;
import com.example.bustimingapp.repository.ScheduleRepository;
import com.example.bustimingapp.entity.Location;
import com.example.bustimingapp.entity.Route;
import com.example.bustimingapp.entity.Bus;
import com.example.bustimingapp.entity.Schedule;
import com.example.bustimingapp.entity.RouteStop;

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
    public void run(String... args) {
        // Adding Locations
        Location cityCenter = locationRepository.save(new Location("City Center"));
        Location mainStreet = locationRepository.save(new Location("Main Street"));
        Location airport = locationRepository.save(new Location("Airport"));
        Location downtown = locationRepository.save(new Location("Downtown"));

        // Adding Routes
        Route route1 = routeRepository.save(new Route(cityCenter, airport));
        Route route2 = routeRepository.save(new Route(mainStreet, airport));

        // Adding Route Stops
        routeStopRepository.save(new RouteStop(route1, mainStreet, 1, "08:15"));
        routeStopRepository.save(new RouteStop(route1, downtown, 2, "08:30"));
        routeStopRepository.save(new RouteStop(route2, downtown, 1, "09:00"));

        // Adding Buses
        Bus bus1 = busRepository.save(new Bus("105A"));
        Bus bus2 = busRepository.save(new Bus("220B"));

        // Adding Schedules
        scheduleRepository.save(new Schedule(bus1, route1, "08:00", "08:45", "Mon-Fri"));
        scheduleRepository.save(new Schedule(bus2, route2, "09:00", "09:45", "Sat-Sun"));
    }
}
