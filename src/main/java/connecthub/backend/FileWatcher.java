package connecthub.backend;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;


public class FileWatcher {

        private static final String DIRECTORY_PATH = "resources\\database\\"; // Directory containing the files
        private static final String FILE_1 = Constants.RELATIONS_DATABASE +".json";
        private static final String FILE_2 = Constants.FRIENDS_REQUEST_DATABASE+".json";
      //  private static final String FILE_3 = "data3.json";

        public static void startWatching() {
            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                Path dirPath = Paths.get(DIRECTORY_PATH);
                dirPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.submit(() -> {
                    while (true) {
                        WatchKey key;
                        try {
                            key = watchService.take(); // Wait for a change
                        } catch (InterruptedException e) {
                            return;
                        }

                        for (WatchEvent<?> event : key.pollEvents()) {
                            String fileName = event.context().toString();
                            if (fileName.equals(FILE_1)) {
                                System.out.println("relations changed");
                                reloadFile1();
                            } else if (fileName.equals(FILE_2)) {
                                System.out.println("friend request changed");
                                reloadFile2();
                            }/* else if (fileName.equals(FILE_3)) {
                                System.out.println("File 3 changed! Reloading...");
                                reloadFile3();
                            }*/
                        }
                        key.reset(); // Reset the watch key
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void reloadFile1() {
            Platform.runLater(() -> {
                // Logic to reload file 1 and refresh the UI
                System.out.println("Reloading relations...");
            });
        }

        private static void reloadFile2() {
            Platform.runLater(() -> {
                // Logic to reload file 2 and refresh the UI
                System.out.println("Reloading friend requests...");
            });
        }

  /*      private static void reloadFile3() {
            Platform.runLater(() -> {
                // Logic to reload file 3 and refresh the UI
                System.out.println("Reloading data3.json...");
            });
        }*/
    }


