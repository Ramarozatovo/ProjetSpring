/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ETU1850.framework;

import ETU1850.framework.Mapping;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tovo
 */
public class Utilities {
    // Récupère toutes les classes dans un package donné et ses sous-packages
    public static List<Class<?>> getClasses2(String scannedPackage) throws Exception {
        
        try {
            // Obtient le class loader actuel
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // Convertit le nom du package en un chemin de fichier
            String path = scannedPackage.replace('.', '/');
            // Récupère toutes les URL de ressources avec ce chemin de fichier
            Enumeration<URL> resources = classLoader.getResources(path);
            // Stocke tous les répertoires trouvés
            List<File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            // Stocke toutes les classes trouvées
            List<Class<?>> classes = new ArrayList<>();
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, scannedPackage));
            }
            return classes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting classes in package");
        }
    }
    
    // Récupère toutes les classes dans un répertoire donné
    private static List<Class<?>> findClasses(File directory, String packageName) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        try {
            if (!directory.exists()) {
                return classes;
            }
            // Récupère tous les fichiers dans ce répertoire
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    // Si le fichier est un répertoire, appelle récursivement findClasses avec le nouveau nom de package
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    // Si le fichier est une classe, charge la classe avec son nom complet
                    String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                    classes.add(Class.forName(className));
                }
            }
            return classes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting classes in a specific directory");
        }
    }
    
    // Récupère toutes les méthodes annotées avec une annotation donnée dans un package donné
    public static HashMap<String,Mapping> getAnnotatedMethods(String packageName, Class<? extends Annotation> annotationClass) throws Exception {
        try {
            // Récupère toutes les classes dans le package donné
            List<Class<?>> classes = getClasses2(packageName);
            // Stocke toutes les méthodes annotées trouvées
            HashMap<String,Mapping> annotatedMethods = new HashMap<String,Mapping>();
            for (Class<?> cls : classes) {
                // Récupère toutes les méthodes déclarées dans la classe
                Method[] methods = cls.getDeclaredMethods();
                for (Method method : methods) {
                    // Vérifie si la méthode est annotée avec l'annotation donnée
                    Annotation annotation = method.getAnnotation(annotationClass);
                    if (annotation != null) {
                        // Stocke la méthode annotée dans un objet Mapping
                        annotatedMethods.put(((AnnotedClass) annotation).methodName(), new Mapping( cls.getName(), method.getName()));
                    }
                }
            }
            return annotatedMethods;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting all methods annoted");
        }
       
    }
}
