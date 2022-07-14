package com.vttp2022.ssfworkshop3.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil {
    private static Logger logger = LoggerFactory.getLogger(IOUtil.class);

    public static void createDir(String path) {
        File dir = new File(path);
        boolean isDirCreated = dir.mkdir();
        logger.info("dir created > " + isDirCreated);
        if(isDirCreated) {
            String osName = System.getProperty("os.name");
            if(osName.contains("Windows")) {
                try {
                    String perm = "rwxrwx---";
                    Set<PosixFilePermission> permission = PosixFilePermissions.fromString(perm);
                    Files.setPosixFilePermissions(dir.toPath(), permission);
                } catch (IOException e) {
                    logger.error("Error", e);
                }
            }
        }
        
    }
}
