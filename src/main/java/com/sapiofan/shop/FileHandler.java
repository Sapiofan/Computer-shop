package com.sapiofan.shop;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.sapiofan.shop.entities.Computer;
import com.sapiofan.shop.entities.Laptop;
import com.sapiofan.shop.entities.Tablet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileHandler {

    private final String RESOURCES_FOLDER = "src/main/resources/";

    public void showCategory(String category) {
        switch (category) {
            case "Laptops": {
                boolean header = true;
                try (CSVReader csvReader = new CSVReader(new FileReader(RESOURCES_FOLDER + "laptops.csv"))) {
                    String[] values;
                    while ((values = csvReader.readNext()) != null) {
                        if (header) {
                            header = false;
                            continue;
                        }
                        Computer computer = getComputerById(Long.parseLong(values[5]));
                        if (computer != null) {
                            System.out.println(new Laptop(computer.getId(), computer.getName(), computer.getBrand(),
                                    computer.getPrice(), values[2], values[1], Integer.parseInt(values[4]),
                                    Integer.parseInt(values[0]), values[3]));
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Something happened while reading of laptops file: " + e);
                }
            }
            break;
            case "Tablets": {
                boolean header = true;
                try (CSVReader csvReader = new CSVReader(new FileReader(RESOURCES_FOLDER + "tablets.csv"))) {
                    String[] values;
                    while ((values = csvReader.readNext()) != null) {
                        if (header) {
                            header = false;
                            continue;
                        }
                        Computer computer = getComputerById(Long.parseLong(values[4]));
                        if (computer != null) {
                            System.out.println(new Tablet(computer.getId(), computer.getName(), computer.getBrand(),
                                    computer.getPrice(), values[2], values[1], Boolean.parseBoolean(values[0]), values[3]));
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Something happened while reading of tablets file: " + e);
                }
            }
        }
    }

    public List<Computer> getAllComputers() {
        List<Computer> computers = new ArrayList<>();

        boolean header = true;
        try (CSVReader csvReader = new CSVReader(new FileReader(RESOURCES_FOLDER + "computers.csv"))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                computers.add(new Computer(Long.valueOf(values[0]), values[1], values[2], Integer.valueOf(values[3])));
            }
        } catch (IOException e) {
            System.err.println("Something happened while reading of computers file: " + e);
        }

        return computers;
    }

    public Computer getDetailsOfComputer(long id) {
        Computer computer = getComputerById(id);
        if (computer == null) {
            return null;
        }

        boolean header = true;
        try (CSVReader csvReader = new CSVReader(new FileReader(RESOURCES_FOLDER + "laptops.csv"))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                if (Long.parseLong(values[5]) == id) {
                    return new Laptop(computer.getId(), computer.getName(), computer.getBrand(), computer.getPrice(),
                            values[2], values[1], Integer.parseInt(values[4]), Integer.parseInt(values[0]), values[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Something happened while reading of laptops file: " + e);
        }

        header = true;
        try (CSVReader csvReader = new CSVReader(new FileReader(RESOURCES_FOLDER + "tablets.csv"))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                if (Long.parseLong(values[4]) == id) {
                    return new Tablet(computer.getId(), computer.getName(), computer.getBrand(), computer.getPrice(),
                            values[2], values[1], Boolean.parseBoolean(values[0]), values[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Something happened while reading of tablets file: " + e);
        }

        return null;
    }

    private Computer getComputerById(long id) {
        boolean header = true;
        try (CSVReader csvReader = new CSVReader(new FileReader(RESOURCES_FOLDER + "computers.csv"))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                if (Long.parseLong(values[0]) == id) {
                    return new Computer(Long.valueOf(values[0]), values[1], values[2], Integer.valueOf(values[3]));
                }
            }
        } catch (IOException e) {
            System.err.println("Something happened while reading of computers file: " + e);
        }

        return null;
    }

    public void addNote(String phone, String address, long device_id) {
        int newId = getNumberOfRows() + 1;
        try (CSVWriter writer = new CSVWriter(new FileWriter(RESOURCES_FOLDER + "notes.csv", true))) {
            writer.writeNext(new String[]{String.valueOf(newId), address,
                    new Date().toString(), phone, String.valueOf(device_id)}, true);
        } catch (IOException e) {
            System.err.println("Something happened while writing to notes file: " + e);
        }
    }

    private int getNumberOfRows() {
        try (CSVReader csvReader = new CSVReader(new FileReader(RESOURCES_FOLDER + "notes.csv"))) {
            List<String[]> all = csvReader.readAll();
            return Integer.parseInt(all.get(all.size() - 1)[0]);
        } catch (IOException e) {
            System.err.println("Something happened while reading of notes file: " + e);
        }
        return 0;
    }
}
