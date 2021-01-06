package com.mwb.web.mock;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
public class MockMethodUtils {
    private static final Set<Class<?>> COMMON_CLASS_CACHE = new HashSet(64);

    static {
        COMMON_CLASS_CACHE.add(Boolean.TYPE);
        COMMON_CLASS_CACHE.add(Boolean.class);
        COMMON_CLASS_CACHE.add(Byte.TYPE);
        COMMON_CLASS_CACHE.add(Byte.class);
        COMMON_CLASS_CACHE.add(Character.TYPE);
        COMMON_CLASS_CACHE.add(Character.class);
        COMMON_CLASS_CACHE.add(Double.TYPE);
        COMMON_CLASS_CACHE.add(Double.class);
        COMMON_CLASS_CACHE.add(Float.TYPE);
        COMMON_CLASS_CACHE.add(Float.class);
        COMMON_CLASS_CACHE.add(Integer.TYPE);
        COMMON_CLASS_CACHE.add(Integer.class);
        COMMON_CLASS_CACHE.add(Long.TYPE);
        COMMON_CLASS_CACHE.add(Long.class);
        COMMON_CLASS_CACHE.add(Short.TYPE);
        COMMON_CLASS_CACHE.add(Short.class);
        COMMON_CLASS_CACHE.add(Void.TYPE);
        COMMON_CLASS_CACHE.add(Void.class);
        COMMON_CLASS_CACHE.add(String.class);
        COMMON_CLASS_CACHE.add(Date.class);
        COMMON_CLASS_CACHE.add(Map.class);
        COMMON_CLASS_CACHE.add(List.class);
        COMMON_CLASS_CACHE.add(Collection.class);
        COMMON_CLASS_CACHE.add(Set.class);
    }

    public static boolean containClass(Class<?> className) {
        return COMMON_CLASS_CACHE.contains(className);
    }


    public static Object getObject(Class<?> className, String val) {
        if (StringUtils.isBlank(val)) {
            return null;
        }
        if (Boolean.class.equals(className)) {
            return Boolean.valueOf(val);
        } else if (Byte.class.equals(className)) {
            return Byte.valueOf(val);
        } else if (Character.class.equals(className)) {
            return val.charAt(0);
        } else if (Double.class.equals(className)) {
            return Double.valueOf(val);
        } else if (Float.class.equals(className)) {
            return Float.valueOf(val);
        } else if (Integer.class.equals(className)) {
            return Integer.valueOf(val);
        } else if (Long.class.equals(className)) {
            return Long.valueOf(val);
        } else if (Short.class.equals(className)) {
            return Short.valueOf(val);
        }
        return null;
    }

    /**
     * 查询指定包下所有类及接口
     *
     * @param packageNames
     * @return
     */
    public static Set<String> getClassMethodNames(String[] packageNames) {
        if (packageNames != null) {
            Set<String> classMethodNames = new HashSet<>();
            for (String packageName : packageNames) {
                Set<String> classSet = getClassName(packageName, true);
                if (classSet != null) {
                    for (String className : classSet) {
                        Set<String> methodNames = getMethodNames(className);
                        for (String methodName : methodNames) {
                            classMethodNames.add(className + "." + methodName);
                        }
                    }
                }
            }
            return classMethodNames;
        }
        return Collections.emptySet();
    }

    public static Set<String> getMethodNames(String aClass) {
        Set<String> methodNames = new HashSet<>();
        //获取对象类型 包名.类名
        Class<?> classType = null;
        try {
            classType = Class.forName(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (classType != null) {
            //获取对象中的所有方法
            Method[] methods = classType.getDeclaredMethods();
            for (Method method : methods) {
                StringBuilder name = new StringBuilder(method.getName());
                if (!name.toString().contains("$")) {
                    name.append("(");
                    Parameter[] parameters = method.getParameters();
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        name.append(parameter.getType().getSimpleName()).append(" ").append(parameter.getName());
                        if (i < parameters.length - 1) {
                            name.append(", ");
                        }
                    }
                    name.append(")");
                    methodNames.add(name.toString());
                }
            }
        }
        return methodNames;
    }

    /**
     * 获取某包下所有类
     *
     * @param packageName 包名
     * @param isRecursion 是否遍历子包
     * @return 类的完整名称
     */
    public static Set<String> getClassName(String packageName, boolean isRecursion) {
        Set<String> classNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replaceAll("\\.", "\\/");
        URL url = loader.getResource(packagePath);

        if (url != null) {
            String protocol = url.getProtocol();

            if ("file".equals(protocol)) {
                classNames = getClassNameFromDir(url.getPath(), packageName, isRecursion);
            } else if ("jar".equals(protocol)) {
                JarFile jarFile = null;
                try {
                    jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                if (jarFile != null) {
                    classNames = getClassNameFromJar(jarFile.entries(), packageName, isRecursion);
                }
            }
        } else {
            /*从所有的jar包中查找包名*/
            classNames = getClassNameFromJars(((URLClassLoader) loader).getURLs(), packageName, isRecursion);
        }

        return classNames;
    }

    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath    文件路径
     * @param packageName 类名集合
     * @param isRecursion 是否遍历子包
     * @return 类的完整名称
     */
    private static Set<String> getClassNameFromDir(String filePath, String packageName, boolean isRecursion) {
        Set<String> className = new HashSet<String>();
        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File childFile : files) {
            if (childFile.isDirectory()) {
                if (isRecursion) {
                    className.addAll(getClassNameFromDir(childFile.getPath(), packageName + "." + childFile.getName(), isRecursion));
                }
            } else {
                String fileName = childFile.getName();
                if (fileName.endsWith(".class") && !fileName.contains("$")) {
                    className.add(packageName + "." + fileName.replace(".class", ""));
                }
            }
        }
        return className;
    }

    /**
     * 从所有jar中搜索该包，并获取该包下所有类
     *
     * @param urls        URL集合
     * @param packageName 包路径
     * @param isRecursion 是否遍历子包
     * @return 类的完整名称
     */
    private static Set<String> getClassNameFromJars(URL[] urls, String packageName, boolean isRecursion) {
        Set<String> classNames = new HashSet<String>();

        for (int i = 0; i < urls.length; i++) {
            String classPath = urls[i].getPath();

            //不必搜索classes文件夹
            if (classPath.endsWith("classes/")) {
                continue;
            }

            JarFile jarFile = null;
            try {
                jarFile = new JarFile(classPath.substring(classPath.indexOf("/")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (jarFile != null) {
                classNames.addAll(getClassNameFromJar(jarFile.entries(), packageName, isRecursion));
            }
        }

        return classNames;
    }

    private static Set<String> getClassNameFromJar(Enumeration<JarEntry> jarEntries, String packageName, boolean isRecursion) {
        Set<String> classNames = new HashSet<String>();

        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();

            if (!jarEntry.isDirectory()) {
                /*
                 * 这里是为了方便，先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug
                 * (FIXME: 先把"/" 转成 "." 再判断 ".class" 的做法可能会有bug)
                 */
                String entryName = jarEntry.getName().replace("/", ".");

                if (entryName.endsWith(".class") && !entryName.contains("$") && entryName.startsWith(packageName)) {
                    entryName = entryName.replace(".class", "");

                    if (isRecursion) {
                        classNames.add(entryName);
                    } else if (!entryName.replace(packageName + ".", "").contains(".")) {
                        classNames.add(entryName);
                    }
                }
            }
        }

        return classNames;
    }

}
