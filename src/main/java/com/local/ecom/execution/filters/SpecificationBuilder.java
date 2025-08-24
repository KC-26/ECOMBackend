package com.local.ecom.execution.filters;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class SpecificationBuilder<T> {

    public Specification<T> createSpecification(Node node) {
        if(node instanceof LogicalNode logicalNode) {
            return createSpecification(logicalNode);
        }
        if(node instanceof ComparisonNode comparisonNode) {
            return createSpecification(comparisonNode);
        }
        return null;
    }

    public Specification<T> createSpecification(LogicalNode logicalNode) {
        List<Specification<T>> specifications = logicalNode
                                                .getChildren()
                                                .stream()
                                                .map(this::createSpecification)
                                                .filter(Objects::nonNull).toList();

        Specification<T> result = specifications.get(0);

        for(int specNum=1;specNum<specifications.size();specNum++) {
            result = logicalNode.getOperator() == LogicalOperator.AND ? result.and(specifications.get(specNum)) : result.or(specifications.get(specNum));
        }

        return result;
    }

    public Specification<T> createSpecification(ComparisonNode comparisonNode) {
        return (root, query, criteriaBuilder) -> {
            String selector = comparisonNode.getSelector();
            String operator = comparisonNode.getOperator().getSymbol();
            String argument = comparisonNode.getArguments().get(0);

            Path<String> path = root.get(selector);

            return switch (operator) {
                case "==" -> argument.contains("*") ? criteriaBuilder.equal(path,argument.replaceAll(Pattern.quote("*"),"%")) : criteriaBuilder.equal(path,argument);
                case "!=" -> argument.contains("*") ? criteriaBuilder.equal(path,argument.replaceAll(Pattern.quote("*"),"%")) : criteriaBuilder.notEqual(path,argument);
                case ">" -> criteriaBuilder.greaterThan(path, argument);
                case "<" -> criteriaBuilder.lessThan(path, argument);
                case ">=" -> criteriaBuilder.greaterThanOrEqualTo(path, argument);
                case "<=" -> criteriaBuilder.lessThanOrEqualTo(path, argument);
                default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
            };
        };
    }
}
