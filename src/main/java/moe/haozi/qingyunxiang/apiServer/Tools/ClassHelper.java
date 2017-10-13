package moe.haozi.qingyunxiang.apiServer.Tools;

import sun.plugin.util.PluginSysUtil;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassHelper {
    public Set<Class<?>> getClasses() {
        return classes;
    }

    private Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

    public ClassHelper scanner(Enumeration<URL> packageUrl, String packagePath) {
        try {
            while (packageUrl.hasMoreElements()) {
                URL url = packageUrl.nextElement();//得到的结果大概是：jar:file:/C:/Users/ibm/.m2/repository/junit/junit/4.12/junit-4.12.jar!/org/junit
                String protocol = url.getProtocol();//大概是jar
                if ("jar".equalsIgnoreCase(protocol)) {
                    //转换为JarURLConnection
                    JarURLConnection connection = (JarURLConnection) url.openConnection();
                    if (connection != null) {
                        JarFile jarFile = connection.getJarFile();
                        if (jarFile != null) {
                            //得到该jar文件下面的类实体
                            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                            while (jarEntryEnumeration.hasMoreElements()) {
                                JarEntry entry = jarEntryEnumeration.nextElement();
                                String jarEntryName = entry.getName();
                                //这里我们需要过滤不是class文件和不在basePack包名下的类
                                if (jarEntryName.contains(".class") && jarEntryName.replaceAll("/",".").startsWith(packagePath)) {
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                                    Class cls = Class.forName(className);
                                    getClasses().add(cls);
                                    System.out.println(cls);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
