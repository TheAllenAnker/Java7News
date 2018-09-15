package com.company.NewIO;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class IODemo {
    public static void main(String[] args) {
        IODemo demo = new IODemo();
        demo.demo1();
        System.out.println("======================");
        try {
            demo.directory_demo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finding files in a directory (not including its sub-directory)
     */
    public void demo1() {
        Path dir = Paths.get("./src/com/company");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.java")) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Find all the matched files from a directory, including its sub-directory.
     */
    public void directory_demo() throws IOException {
        Path startingDir = Paths.get("./src");
        Files.walkFileTree(startingDir, new FindJavaVisitor());
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toString().endsWith(".java")) {
                System.out.println(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
