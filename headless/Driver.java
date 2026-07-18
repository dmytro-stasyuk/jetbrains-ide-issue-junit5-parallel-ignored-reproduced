import com.intellij.junit5.JUnit5TestExecutionListener;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import java.nio.file.Paths;
import java.util.Set;

public class Driver {
    public static void main(String[] args) throws Exception {
        Launcher launcher = LauncherFactory.create();
        JUnit5TestExecutionListener listener = new JUnit5TestExecutionListener(System.out);
        try { listener.initializeIdSuffix(true); } catch (Throwable t) {}
        listener.setSendTree();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(DiscoverySelectors.selectClasspathRoots(Set.of(Paths.get(args[0]))))
            .build();
        launcher.execute(request, listener);
    }
}
