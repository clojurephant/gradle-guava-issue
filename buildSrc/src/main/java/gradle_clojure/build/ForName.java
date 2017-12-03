package gradle_clojure.build;

import javax.inject.Inject;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.workers.WorkerExecutor;

public class ForName extends DefaultTask {
  private final WorkerExecutor workerExecutor;

  @Inject
  public ForName(WorkerExecutor workerExecutor) {
    this.workerExecutor = workerExecutor;
  }

  @TaskAction
  public void run() {
    workerExecutor.submit(ForNameWorker.class, config -> {});
  }

  public static class ForNameWorker implements Runnable {
    @Override
    public void run() {
      try {
        Class.forName("com.google.common.base.Functions");
        System.err.println("ERROR: Guava found on classpath");
      } catch (ClassNotFoundException e) {
        System.out.println("SUCCESS: Guava not on classpath");
      }
    }
  }
}
