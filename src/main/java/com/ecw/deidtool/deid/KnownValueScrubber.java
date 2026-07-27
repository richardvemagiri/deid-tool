package com.ecw.deidtool.deid;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class KnownValueScrubber {

    public Document scrubKnownValuesEverywhere(Document document, Map<String, Set<String>> knownValues) {
        if (document == null || knownValues == null || knownValues.isEmpty()) {
            return document;
        }

        scrubNode(document.getDocumentElement(), knownValues);
        return document;
    }

    private int scrubNode(Node node, Map<String, Set<String>> knownValues) {
        if (node == null) {
            return 0;
        }

        int replacementCount = 0;

        replacementCount += scrubAttributes(node, knownValues);

        if (node.getNodeType() == Node.TEXT_NODE) {
            String originalText = node.getTextContent();
            String scrubbedText = scrubText(originalText, knownValues);

            if (!originalText.equals(scrubbedText)) {
                node.setTextContent(scrubbedText);
                replacementCount++;
            }
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            replacementCount += scrubNode(children.item(i), knownValues);
        }

        return replacementCount;
    }

    private int scrubAttributes(Node node, Map<String, Set<String>> knownValues) {
        NamedNodeMap attributes = node.getAttributes();
        if (attributes == null) {
            return 0;
        }

        int replacementCount = 0;

        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            String originalValue = attribute.getNodeValue();
            String scrubbedValue = scrubText(originalValue, knownValues);

            if (!originalValue.equals(scrubbedValue)) {
                attribute.setNodeValue(scrubbedValue);
                replacementCount++;
            }
        }

        return replacementCount;
    }

    private String scrubText(String input, Map<String, Set<String>> knownValues) {
        if (input == null || input.isBlank()) {
            return input;
        }

        String scrubbed = input;

        for (Map.Entry<String, Set<String>> entry : knownValues.entrySet()) {
            String replacement = replacementForCategory(entry.getKey());

            for (String knownValue : entry.getValue()) {
                if (knownValue == null || knownValue.isBlank()) {
                    continue;
                }

                Pattern pattern = Pattern.compile(Pattern.quote(knownValue.trim()));
                Matcher matcher = pattern.matcher(scrubbed);
                scrubbed = matcher.replaceAll(Matcher.quoteReplacement(replacement));
            }
        }

        return scrubbed;
    }

    private String replacementForCategory(String category) {
        if ("DOB".equalsIgnoreCase(category)) {
            return "1900-01-01";
        }

        if ("ID".equalsIgnoreCase(category)) {
            return "99999999";
        }

        if ("Gender".equalsIgnoreCase(category)) {
            return "Male";
        }

        if ("MaritalStatus".equalsIgnoreCase(category)) {
            return "Single";
        }

        if ("RaceEthnicity".equalsIgnoreCase(category)) {
            return "REDACTED";
        }

        return "REDACTED";
    }
}
