package com.zhoukb.homework.designmodel.proxy.dynamicproxy.kbproxy.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来生成源代码的工具类
 */
public class KBProxy {

    public static final String ln = "\r\n";

    public static Object newProxyInstance(KBClassLoader classLoader, Class<?>[] interfaces, KBInvocationHandler h) {
        try {
            //1、动态生成源代码.java文件
            String src = generateSrc(interfaces);

//           System.out.println(src);
            //2、Java文件输出磁盘
            String filePath = KBProxy.class.getResource("").getPath();
//           System.out.println(filePath);
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            //3、把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manage.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null, manage, null, null, null, iterable);
            task.call();
            manage.close();

            //4、编译生成的.class文件加载到JVM中来
            Class proxyClass = classLoader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(KBInvocationHandler.class);
            f.delete();

            //5、返回字节码重组以后的新的代理对象
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append(KBProxy.class.getPackage() + ";" + ln);
        for (int i = 0; i < interfaces.length; i++) {
            sb.append("import " + interfaces[i].getName() + ";" + ln);
        }
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
        sb.append("KBInvocationHandler h;" + ln);
        sb.append("public $Proxy0(KBInvocationHandler h) { " + ln);
        sb.append("this.h = h;");
        sb.append("}" + ln);
        for (int i = 0; i < interfaces.length; i++) {
            for (Method m : interfaces[i].getMethods()) {
                Class<?>[] parameterTypes = m.getParameterTypes();

                StringBuffer parameterNames = new StringBuffer();
                StringBuffer parameterValues = new StringBuffer();
                StringBuffer parameterClasses = new StringBuffer();

                for (int j = 0; j < parameterTypes.length; j++) {
                    Class clazz = parameterTypes[j];
                    String type = clazz.getName();
                    String paramName = toLowerFirstCase(clazz.getSimpleName());
                    parameterNames.append(type).append(" ").append(paramName);
                    parameterValues.append(paramName);
                    parameterClasses.append(clazz.getName()).append(".class");
                    if (j < parameterTypes.length - 1) {
                        parameterNames.append(",");
                        parameterValues.append(",");
                        parameterClasses.append(",");
                    }
                }

                sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + parameterNames + ") {" + ln);
                sb.append("try{" + ln);
                sb.append("Method m = " + interfaces[i].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + parameterClasses + "});" + ln);
                sb.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + parameterValues + "})", m.getReturnType()) + ";" + ln);
                sb.append("}catch(Error _ex) { }");
                sb.append("catch(Throwable e){" + ln);
                sb.append("throw new UndeclaredThrowableException(e);" + ln);
                sb.append("}");
                sb.append(getReturnEmptyCode(m.getReturnType()));
                sb.append("}");
            }
        }
        sb.append("}" + ln);
        return sb.toString();
    }

    private static Map<Class, Class> mappings = new HashMap<>();

    static {
        mappings.put(int.class, Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "return 0;";
        } else if (returnClass == void.class) {
            return "";
        } else {
            return "return null;";
        }
    }

    private static String getCaseCode(String code, Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "((" + mappings.get(returnClass).getName() + ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz) {
        return clazz != void.class;
    }

    private static String toLowerFirstCase(String src) {
        char[] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
