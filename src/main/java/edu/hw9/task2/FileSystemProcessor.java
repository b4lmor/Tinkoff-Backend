package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FileSystemProcessor {

    public List<String> findDirectoriesWithMoreThanXFiles(String path, int x) {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FindDirectoriesTask task = new FindDirectoriesTask(new File(path), x);
            return forkJoinPool.invoke(task);
        }
    }

    public List<String> findFilesByPredicate(String path, FilePredicate predicate) {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FindFilesTask task = new FindFilesTask(new File(path), predicate);
            return forkJoinPool.invoke(task);
        }
    }

    private static class FindDirectoriesTask extends RecursiveTask<List<String>> {
        private final File directory;
        private final int x;

        FindDirectoriesTask(File directory, int x) {
            this.directory = directory;
            this.x = x;
        }

        @Override
        protected List<String> compute() {
            List<String> result = new ArrayList<>();

            File[] files = directory.listFiles();
            if (files != null) {
                List<FindDirectoriesTask> subTasks = new ArrayList<>();

                for (File file : files) {
                    if (file.isDirectory()) {
                        FindDirectoriesTask subTask = new FindDirectoriesTask(file, x);
                        subTasks.add(subTask);
                        subTask.fork();
                    }
                }

                for (FindDirectoriesTask subTask : subTasks) {
                    result.addAll(subTask.join());
                }

                if (files.length > x) {
                    result.add(directory.getAbsolutePath());
                }
            }

            return result;
        }
    }

    private static class FindFilesTask extends RecursiveTask<List<String>> {
        private final File directory;
        private final FilePredicate predicate;

        FindFilesTask(File directory, FilePredicate predicate) {
            this.directory = directory;
            this.predicate = predicate;
        }

        @Override
        protected List<String> compute() {
            List<String> result = new ArrayList<>();

            File[] files = directory.listFiles();
            if (files != null) {
                List<FindFilesTask> subTasks = new ArrayList<>();

                for (File file : files) {
                    if (file.isDirectory()) {
                        FindFilesTask subTask = new FindFilesTask(file, predicate);
                        subTasks.add(subTask);
                        subTask.fork();
                    } else if (predicate.test(file)) {
                        result.add(file.getAbsolutePath());
                    }
                }

                for (FindFilesTask subTask : subTasks) {
                    result.addAll(subTask.join());
                }
            }

            return result;
        }
    }

    public interface FilePredicate {
        boolean test(File file);
    }
}
