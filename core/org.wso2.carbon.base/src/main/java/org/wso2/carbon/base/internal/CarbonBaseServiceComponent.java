//package org.wso2.carbon.base.internal;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceRegistration;
//import org.osgi.service.component.ComponentContext;
//import org.osgi.service.component.annotations.Activate;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Deactivate;
//import org.wso2.carbon.base.CarbonBaseUtils;
//import org.wso2.carbon.base.ServerConfiguration;
//import org.wso2.carbon.base.ServerConfigurationException;
//import org.wso2.carbon.base.api.ServerConfigurationService;
//
//import javax.servlet.ServletException;
//import java.io.*;
//
//@Component(
//        name = "org.wso2.carbon.base.component",
//        immediate = true
//)
//public class CarbonBaseServiceComponent {
//
//    private static final Log log = LogFactory.getLog(CarbonBaseServiceComponent.class);
//
//    private ServiceRegistration registration;
//
//    @Activate
//    protected void activate(ComponentContext componentContext) {
//        try {
//            BundleContext bundleContext = componentContext.getBundleContext();
//            ServerConfigurationService carbonServerConfiguration = ServerConfiguration.getInstance();
//            initServerConfiguration((ServerConfiguration) carbonServerConfiguration);
//            String portOffset = System.getProperty("portOffset",
//                    carbonServerConfiguration.getFirstProperty("Ports.Offset"));
//            //setting the retrieved ports.offset value as a system property, in case it was not defined.
//            //NIO transport make use of this system property
//            System.setProperty("portOffset", portOffset);
//
//            registration = bundleContext.registerService(ServerConfigurationService.class.getName(), carbonServerConfiguration, null);
//            if (log.isDebugEnabled()) {
//                log.debug("Carbon Base Service bundle is activated.");
//            }
//        } catch (Exception e) {
//            log.error("Error while activating CarbonBaseServiceComponent.", e);
//        }
//    }
//
//    @Deactivate
//    protected void deactivate(ComponentContext componentContext) {
//
//        BundleContext bundleContext = componentContext.getBundleContext();
//        bundleContext.ungetService(registration.getReference());
//        if (log.isDebugEnabled()) {
//            log.debug("CarbonBaseServiceComponent is deactivated.");
//        }
//    }
//
//    private void initServerConfiguration(ServerConfiguration carbonServerConfiguration) throws ServletException {
//        File carbonXML = new File(CarbonBaseUtils.getServerXml());
//        InputStream in = null;
//        try {
//            in = new FileInputStream(carbonXML);
//            carbonServerConfiguration.forceInit(in);
//        } catch (ServerConfigurationException e) {
//            String msg = "Could not initialize server configuration";
//            log.fatal(msg);
//            throw new ServletException(msg);
//        } catch (FileNotFoundException e) {
//            throw new ServletException(e);
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    log.warn("Cannot close FileInputStream of file " + carbonXML.getAbsolutePath());
//                }
//            }
//        }
//    }
//}
