/*******************************************************************************
 * Copyright 2015, Medidata Solutions, Inc., All Rights Reserved. The program
 * and the accompanying materials are made under the terms of MIT license.
 * Author: Nan Li, nli@mdsol.com
 ******************************************************************************/
package com.mdsol.skyfire.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mdsol.skyfire.IdentifiableElementType;
import com.mdsol.skyfire.Mapping;

/**
 * A JUnit test case for class {@link Mapping}
 *
 * @author Nan Li
 * @version 1.0 Nov 12, 2012
 *
 */
public class MappingTest {

    @Test
    public void testConstructorAndGettersAndSetters() {
        String mappingName = "vMachineInit";
        String identifiedElementName = "vm";
        IdentifiableElementType type = IdentifiableElementType.CLASS;
        String testCode = "vendingMachine vm = new vendingMachine();";
        List<String> mappings = new ArrayList<>();
        List<String> parameters = new ArrayList<>();

        Mapping mapping = new Mapping(mappingName, type, identifiedElementName, testCode, mappings,
                parameters, null, null);

        assertEquals(mapping.getName(), "vMachineInit");
        assertEquals(mapping.getIdentifiableElementName(), "vm");
        assertEquals(mapping.getType(), IdentifiableElementType.CLASS);
        assertEquals(mapping.getTestCode(), "vendingMachine vm = new vendingMachine();");
        assertEquals(mapping.getRequiredMappings().size(), 0);
        assertEquals(mapping.getParameters().size(), 0);
    }

}
