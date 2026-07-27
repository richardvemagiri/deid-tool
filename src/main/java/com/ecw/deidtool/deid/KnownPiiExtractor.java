package com.ecw.deidtool.deid;

import com.ecw.deidtool.repository.DeIDDBConfig;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.*;
import java.util.*;

@Component
public class KnownPiiExtractor {

    public Map<String, Set<String>> extractKnownValues(Document document, List<DeIDDBConfig> deIDDBConfigList, NamespaceContext namespaceContext){

        final Set<String> SUPPORTED_KNOWN_VALUE_CATEGORIES =
                Set.of("Name", "DOB", "Addr");
        Map<String, Set<String>> extractKnownValues = new HashMap<>();



        for(DeIDDBConfig config: deIDDBConfigList){
            String category = config.getCategory();
            String expression = config.getXPath();

            if (category == null ||
                    SUPPORTED_KNOWN_VALUE_CATEGORIES.stream()
                            .noneMatch(allowed -> allowed.equalsIgnoreCase(category.trim()))) {
                continue;
            }

            NodeList nodeList = evaluateXPath(document, expression, namespaceContext);

            for (int j = 0; j < nodeList.getLength(); j++) {
                String originalValue = nodeList.item(j).getTextContent();

                if (originalValue != null && !originalValue.isBlank()) {
                    extractKnownValues
                            .computeIfAbsent(category, key -> new HashSet<>())
                            .add(originalValue.trim());
                }
            }
        }
        
        return extractKnownValues;
    }

    private NodeList evaluateXPath(Document document, String expression, NamespaceContext namespaceContext) {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            xpath.setNamespaceContext(namespaceContext);

            XPathExpression xPathExpr = xpath.compile(expression);
            return (NodeList) xPathExpr.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new RuntimeException("Error parsing XPath '" + expression + "'", e);
        }
    }
    
}
