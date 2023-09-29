//package sanity.nil.order.onlineshop;
//
//import org.junit.platform.engine.discovery.DiscoverySelectors;
//import org.junit.platform.launcher.Launcher;
//import org.junit.platform.launcher.LauncherDiscoveryRequest;
//import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
//import org.junit.platform.launcher.core.LauncherFactory;
//import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
//
//import java.io.PrintWriter;
//
//public class TestLauncher {
//    public static void main(String[] args){
//
//            Launcher launcher = LauncherFactory.create();
//            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
//                    .request()
//                    .selectors(DiscoverySelectors.selectPackage("sanity.nil.springproject.service"))
//                    .selectors(DiscoverySelectors.selectPackage("sanity.nil.springproject.facade"))
//                    .build();
//            SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();
//            launcher.registerTestExecutionListeners(summaryGeneratingListener);
//            launcher.execute(request);
//
//            try (var writer = new PrintWriter(System.out)) {
//                summaryGeneratingListener.getSummary().printTo(writer);
//            }
//    }
//}
