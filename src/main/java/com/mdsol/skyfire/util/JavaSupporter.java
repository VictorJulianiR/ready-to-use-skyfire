/*******************************************************************************
 * Copyright 2015, Medidata Solutions, Inc., All Rights Reserved. The program
 * and the accompanying materials are made under the terms of MIT license.
 * Author: Nan Li, nli@mdsol.com
 ******************************************************************************/
package com.mdsol.skyfire.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.NamedElement;

import com.mdsol.skyfire.Mapping;

/**
 * A utility class used to support compilation of Java classes
 *
 * @author Nan Li
 * @version 1.0 Feb 8, 2013
 */

public class JavaSupporter {

    /**
     * This default constructor should prevent instantiating utility class
     */
    protected JavaSupporter() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns all Java files in a directory specified by path
     *
     * @param path
     *            the current path of a directory
     * @return a {@link java.util.List} of {@link java.io.File}s that are Java files
     */
    public static final List<File> returnAllJavaFiles(final String path) {

        final File folder = new File(path);
        final File[] files = folder.listFiles();
        final List<File> results = new ArrayList<>();

        for (final File file : files) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                results.add(file);
            }
        }

        return results;
    }

    /**
     * Returns all Jar files in a directory specified by path
     *
     * @param path
     *            the current path of a directory
     * @return a {@link java.util.List} of {@link java.io.File}s that are Jar files
     */
    public static final List<File> returnAllJarFiles(final String path) {

        final File folder = new File(path);
        final File[] files = folder.listFiles();
        final List<File> results = new ArrayList<>();

        for (final File file : files) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                results.add(file);
            }
        }

        return results;
    }

    /**
     * Removes the semicolon if there is one at the end of a String object.
     *
     * @param string
     *            a statement having semicolons at the end in a String format
     * @return a String object without having a semicolon at the end
     */
    public static final String removeSemiColon(final String string) {
        String result = string;
        if (string.endsWith(";")) {
            result = string.substring(0, string.length() - 1);
            if (result.endsWith(";")) {
                result = removeSemiColon(result);
            }
        } else {
            return result;
        }
        return result;
    }

    /**
     * Copies a File object from the source to the destination.
     *
     * @param source
     *            the source file
     * @param destination
     *            the destination file
     * @throws IOException
     *             when either the source or destination file is not found
     */
    public static final void copyFile(final File source, final File destination)
            throws IOException {
        final InputStream oInStream = new FileInputStream(source);
        final OutputStream oOutStream = new FileOutputStream(destination);

        // Transfer bytes from in to out
        final byte[] oBytes = new byte[1024];
        int nLength;
        final BufferedInputStream oBuffInputStream = new BufferedInputStream(oInStream);

        while ((nLength = oBuffInputStream.read(oBytes)) > 0) {
            oOutStream.write(oBytes, 0, nLength);
        }

        oInStream.close();
        oOutStream.close();
    }

    /**
     * Returns all UML files in a directory specified by path.
     *
     * @param path
     *            the current path of a directory
     * @return a {@link java.util.List} of {@link java.io.File}s that are Java files
     */
    public static final List<File> returnAllUmlFiles(final String path) {

        final File folder = new File(path);
        final File[] files = folder.listFiles();
        final List<File> results = new ArrayList<>();

        for (final File file : files) {
            if (file.isFile() && file.getName().endsWith(".uml")) {
                results.add(file);
            }
        }

        return results;
    }

    /**
     * Returns all sub-directories in a directory specified by path.
     *
     * @param path
     *            the current path of a directory
     * @return a {@link java.util.List} of {@link java.io.File}s that are Java files
     */
    public static final List<File> returnAllDirectories(final String path) {

        final File folder = new File(path);
        final File[] files = folder.listFiles();
        final List<File> results = new ArrayList<>();

        for (final File file : files) {
            if (file.isDirectory()) {
                results.add(file);
            }
        }
        return results;
    }

    /**
     * Get the names of a list of File objects.
     *
     * @param files
     *            a list of File objects
     * @return an array of names of File objects
     */
    public static final Object[] getFileNames(final List<File> files) {
        final List<String> fileNames = new ArrayList<>();
        for (final File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames.toArray();
    }

    /**
     * Get the names of a list of {@link org.eclipse.uml2.uml.NamedElement} objects.
     *
     * @param elements
     *            a list of {@link org.eclipse.uml2.uml.NamedElement} objects
     * @return an array of names of {@link org.eclipse.uml2.uml.NamedElement} objects
     */
    public static final Object[] getElementNames(final List<NamedElement> elements) {
        final List<String> elementNames = new ArrayList<>();
        for (final NamedElement element : elements) {
            elementNames.add(element.getName());
        }
        return elementNames.toArray();
    }

    /**
     * Get the names of a list of {@link edu.gmu.swe.taf.Mapping} objects.
     *
     * @param mappings
     *            a list of {@link edu.gmu.swe.taf.Mapping} objects
     * @return an array of names of {@link edu.gmu.swe.taf.Mapping} objects
     */
    public static final Object[] getMappingNames(final List<? extends Mapping> mappings) {
        final List<String> mappingNames = new ArrayList<>();
        for (final Mapping mapping : mappings) {
            mappingNames.add(mapping.getName());
        }
        return mappingNames.toArray();
    }

    /**
     * Convert a packageName separated by dot to a file path having the package names separated by
     * slash.
     *
     * @param packageName
     *            a package name that could have dot or have no dot
     * @return a path in a String format
     */
    public static final String returnPackages(final String packageName) {
        if (packageName != null && packageName.trim().length() > 0) {
            String name = packageName;
            if (packageName.startsWith("package")) {
                name = name.substring(7, name.length()).trim();
            }
            if (name.endsWith(";")) {
                name = removeSemiColon(name);
            }
            final String[] levels = name.split("\\.");
            String directories = "";
            for (final String level : levels) {
                directories += level + "/";
            }
            return directories;
        }
        return "";
    }

    /**
     * Removes the header "package" and ";" at the end if any.
     *
     * @param packageName
     *            a package name in a String format
     * @return the package name without "package" keyword and ";"
     */
    public static final String cleanUpPackageName(final String packageName) {
        if (packageName != null && packageName.trim().length() > 0) {
            String name = packageName;
            if (packageName.startsWith("package")) {
                name = name.substring(7, name.length()).trim();
            }
            if (name.endsWith(";")) {
                name = removeSemiColon(name);
            }
            return name.trim();
        }
        return "";
    }

    /**
     * Removes the brackets in the toString() result used by List
     *
     * @param listString
     *            toString() result by java.util.List
     * @return the same String representation without brackets
     */
    public static final String removeBrackets(final String listString) {
        if (listString.startsWith("[") && listString.endsWith("]")) {
            // remove brackets
            return listString.substring(1, listString.length() - 1);
        } else {
            return listString;
        }
    }

}
