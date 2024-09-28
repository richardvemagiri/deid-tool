package com.ecw.deidtool.helper;

import javax.xml.namespace.NamespaceContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CCDANamespaceContext implements NamespaceContext {

    private final Map<String, String> NS_MAP = new HashMap<String, String>();

    public CCDANamespaceContext(final Map<String, String> nsMap){
        NS_MAP.putAll(nsMap);
    }

    @Override
    public String getNamespaceURI(String prefix) {
        return NS_MAP.get(prefix);
    }

    @Override
    public String getPrefix(String namespaceURI) {
        return null;
    }

    @Override
    public Iterator<String> getPrefixes(String namespaceURI) {
        return null;
    }
}
