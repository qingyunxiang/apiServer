package moe.haozi.qingyunxiang.apiServer.Tools;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ClassHelper {
    public Set<Class<?>> getClasses() {
        return classes;
    }

    private Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

    public ClassHelper scanner(String packagePath) {
//        packagePath = packagePath.replace(".", "/");
        URL url = Thread.currentThread().getContextClassLoader().getResource(packagePath);
        try {
            if (url != null && url.toString().startsWith("file")) {
                String filePath = URLDecoder.decode(url.getFile(),"utf-8");
                File dir = new File(filePath);
                List<File> fileList = new ArrayList<File>();
                fetchFileList(dir,fileList);
                for (File f: fileList) {
                    String fileName = f.getAbsolutePath();
                    if (fileName.endsWith(".class")) {
                        String nosuffixFileName = fileName.substring(8+fileName.lastIndexOf("classes"),fileName.indexOf(".class"));
                        String filePackage = nosuffixFileName.replaceAll("\\\\", ".");
                        Class<?> clazz = Class.forName(filePackage);
                        classes.add(clazz);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    private static void  fetchFileList(File dir,List<File> fileList){
        if(dir.isDirectory()){
            for(File f:dir.listFiles()){
                fetchFileList(f,fileList);
            }
        }else{
            fileList.add(dir);
        }
    }
}
