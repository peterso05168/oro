/**
 * 
 */
package hk.oro.iefas.ws.core.watcher;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2762 $ $Date: 2018-05-31 14:40:28 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@ConditionalOnProperty(name = "iefas.ftp.watchFtpFile", havingValue = "true")
@Component
public class FtpFileWatcher implements ApplicationRunner {

    @Value(value = "${iefas.ftp.output}")
    private String outputPath;

    @Value(value = "${iefas.ftp.input}")
    private String inputPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run start");
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(inputPath);
        path.toFile().mkdirs();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
            StandardWatchEventKinds.ENTRY_MODIFY);

        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    WatchKey watchKey;
                    watchKey = watchService.take();
                    List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                    watchEvents.stream().forEach(event -> {
                        log.info("[" + path + "/" + event.context() + "] - [" + event.kind() + "]");
                    });
                    watchKey.reset();
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });
        thread.setDaemon(false);
        thread.start();

        log.info("run end");
    }

}
