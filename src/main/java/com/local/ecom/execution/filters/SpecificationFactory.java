package com.local.ecom.execution.filters;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.local.ecom.util.EComLogger.logError;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecificationFactory {
    public static <T> Specification<T> build(String filter) {
        try {
            String preProcessedFilter = filter == null ? "" : preProcessFilter(filter);
            Node parsedFilterRootNode = new RSQLParser().parse(preProcessedFilter);
            return parsedFilterRootNode.accept(new SpecificationVisitor<>());
        } catch (RSQLParserException rsqlParserException) {
            logError(String.format("Exception occurred while building filter specification for: %s",filter),rsqlParserException);
            return null;
        }
    }

    private static String preProcessFilter(String filter) {
        StringBuilder result = new StringBuilder();
        boolean insideQuotes = false;
        StringBuilder currentSegment = new StringBuilder();

        for (int i = 0; i < filter.length(); i++) {
            char c = filter.charAt(i);
            if (c == '"') {
                // process the accumulated unquoted part
                if (!insideQuotes) {
                    result.append(processUnquoted(currentSegment.toString()));
                    currentSegment.setLength(0);
                } else {
                    // if closing quote, append the quoted part as is
                    result.append(currentSegment.toString());
                    currentSegment.setLength(0);
                }

                // toggle quote mode and append the quote itself
                insideQuotes = !insideQuotes;
                //result.append(c);
            } else {
                currentSegment.append(c);
            }
        }

        // process the last part if outside quotes
        if (!insideQuotes) {
            result.append(processUnquoted(currentSegment.toString()));
        } else {
            // if still inside quotes (malformed input), just append as is
            result.append(currentSegment.toString());
        }

        return result.toString();
    }

    // Only apply replacements on unquoted parts
    private static String processUnquoted(String segment) {
        return segment
                .replaceAll("\\s*(?<![<>!])=(?!=)\\s*", " == ")
                .replaceAll("(?i)\\s+AND\\s+", ";")
                .replaceAll("(?i)\\s+OR\\s+", ",");

    }

}
