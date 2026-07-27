package com.ecw.deidtool.deid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class PatternScrubber {

    private static final Pattern SSN_PATTERN = Pattern.compile("\\b\\d{3}-\\d{2}-\\d{4}\\b");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\b(?:\\+?1[-.\\s]?)?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}\\b");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");
    private static final Pattern URL_PATTERN = Pattern.compile("\\b(?:(?:https?|ftp)://|www\\.)\\S+\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern IP_ADDRESS_PATTERN = Pattern.compile("\\b(?:(?:25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\b");
    private static final Pattern ZIP_PATTERN = Pattern.compile("\\b\\d{5}(?:-\\d{4})?\\b");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\b(?:\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4}|\\d{4}[/-]\\d{1,2}[/-]\\d{1,2})\\b");
    private static final Pattern RELATIVE_NAME_PATTERN = Pattern.compile(
            "\\b(wife|husband|daughter|son|mother|father|spouse|partner)\\s+([A-Z][a-z]+(?:\\s+[A-Z][a-z]+)+)\\b",
            Pattern.CASE_INSENSITIVE
    );
    private static final Pattern OCCUPATION_PATTERN = Pattern.compile(
            "\\b(works as|employed as|occupation is|job title is)\\s+([^.;\\n]+)",
            Pattern.CASE_INSENSITIVE
    );
    private static final Pattern STANDALONE_ALPHANUMERIC_ID_PATTERN = Pattern.compile(
            "\\b(?=[A-Z0-9-]{6,20}\\b)(?=.*[A-Z])(?=.*\\d)[A-Z0-9]+(?:-[A-Z0-9]+)*\\b", Pattern.CASE_INSENSITIVE
    );

    public Document scrubPatternsEverywhere(Document document, List<String> categories) {
        if (document == null) {
            return document;
        }

        int replacementCount = scrubNode(document.getDocumentElement(), categories);
        log.debug("PatternScrubber replacementCount: " + replacementCount);
        return document;
    }

    public String scrubText(String input, List<String> categories) {
        if (input == null || input.isBlank()) {
            return input;
        }

        String scrubbed = input;

        if (shouldScrubCategory(categories, "ID")) {
            scrubbed = SSN_PATTERN.matcher(scrubbed).replaceAll("923-45-6789");
            scrubbed = URL_PATTERN.matcher(scrubbed).replaceAll("http://redacted.example.com");
//            scrubbed = IP_ADDRESS_PATTERN.matcher(scrubbed).replaceAll("[IP_ADDRESS]");
            scrubbed = STANDALONE_ALPHANUMERIC_ID_PATTERN
                    .matcher(scrubbed)
                    .replaceAll("Redacted-ID");
        }

        if (shouldScrubCategory(categories, "Addr")) {
            scrubbed = PHONE_PATTERN.matcher(scrubbed).replaceAll("999-999-9999");
            scrubbed = EMAIL_PATTERN.matcher(scrubbed).replaceAll("redacted@example.com");
//            scrubbed = ZIP_PATTERN.matcher(scrubbed).replaceAll("[ZIP]");
        }

        if (shouldScrubCategory(categories, "DOB")) {
            scrubbed = DATE_PATTERN.matcher(scrubbed).replaceAll("1900-01-01");
        }

        if (shouldScrubCategory(categories, "Name")) {
            scrubbed = replaceRelativeNames(scrubbed);
            scrubbed = replaceOccupationDetails(scrubbed);
        }

        return scrubbed;
    }

    private int scrubNode(Node node, List<String> categories) {
        if (node == null) {
            return 0;
        }

        int replacementCount = 0;

        replacementCount += scrubAttributes(node, categories);

        if (node.getNodeType() == Node.TEXT_NODE) {
            String originalText = node.getTextContent();
            String scrubbedText = scrubText(originalText, categories);

            if (!Objects.equals(originalText, scrubbedText)) {
                node.setTextContent(scrubbedText);
                replacementCount++;
            }
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            replacementCount += scrubNode(children.item(i), categories);
        }

        return replacementCount;
    }

    private int scrubAttributes(Node node, List<String> categories) {
        NamedNodeMap attributes = node.getAttributes();
        if (attributes == null) {
            return 0;
        }

        int replacementCount = 0;

        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);

            if (shouldSkipAttribute(attribute)) {
                continue;
            }

            String originalValue = attribute.getNodeValue();
            String scrubbedValue = scrubText(originalValue, categories);

            if (!Objects.equals(originalValue, scrubbedValue)) {
                attribute.setNodeValue(scrubbedValue);
                replacementCount++;
            }
        }

        return replacementCount;
    }

    private String replaceRelativeNames(String input) {
        Matcher matcher = RELATIVE_NAME_PATTERN.matcher(input);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result, Matcher.quoteReplacement(matcher.group(1) + " [REDACTED]"));
        }

        matcher.appendTail(result);
        return result.toString();
    }

    private String replaceOccupationDetails(String input) {
        Matcher matcher = OCCUPATION_PATTERN.matcher(input);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result, Matcher.quoteReplacement(matcher.group(1) + " [REDACTED]"));
        }

        matcher.appendTail(result);
        return result.toString();
    }

    private boolean shouldScrubCategory(List<String> categories, String category) {
        if (categories == null || categories.isEmpty()) {
            return true;
        }

        return categories.stream()
                .filter(Objects::nonNull)
                .anyMatch(selectedCategory -> selectedCategory.equalsIgnoreCase(category));
    }

    private boolean shouldSkipAttribute(Node attribute) {
        String attributeName = attribute.getNodeName();

        return "root".equalsIgnoreCase(attributeName)
                || "classCode".equalsIgnoreCase(attributeName)
                || "moodCode".equalsIgnoreCase(attributeName)
                || "codeSystem".equalsIgnoreCase(attributeName)
                || "typeCode".equalsIgnoreCase(attributeName);
    }
}
